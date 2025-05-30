delimiter $$


drop procedure if exists iniciar_sesion$$
create procedure iniciar_sesion(out resultado boolean, in p_email varchar(100), in p_contrasenya varchar(255))
begin
	declare contra_correcta varchar(255);
	select contrasenya into contra_correcta from cliente where email = p_email limit 1;
	if contra_correcta is null 
		then set resultado = false;
	elseif p_contrasenya = contra_correcta 
		then set resultado = true;
	else set resultado = false;
	end if;
end$$

drop procedure if exists comprar$$
create procedure comprar(IN p_cliente_id int)
begin
    declare carrito_total decimal(10,2);
    declare iva decimal(10,2);
    declare total_con_iva decimal(10,2);
    declare cant_lineas int;
    declare carrito_id int;

    select id, total into carrito_id, carrito_total
    from carrito 
    where cliente_id = p_cliente_id and activo = true;

    if carrito_total is null then
        signal sqlstate '45000' set message_text = 'No hay carrito activo para este cliente, prueba a añadir algo para crearlo';
    end if;

    set iva = 0.21;
    set total_con_iva = carrito_total + (carrito_total * iva);

    select count(*) into cant_lineas 
    from lin_fac 
    where carrito_id = carrito_id;

    if cant_lineas = 0 then
        signal sqlstate '45000' set message_text = 'El carrito está vacío. Añade algunos productos antes de comprar.';
    end if;

    insert into factura(cliente_id, total, total_con_iva)
    values (p_cliente_id, carrito_total, total_con_iva);

    update carrito set activo = false where id = carrito_id;

    insert into carrito(cliente_id) values (p_cliente_id);
end$$

drop procedure if exists votar$$
create procedure votar(in p_cliente_id, p_contenido_id, p_valor)
begin
	insert into valoracion (cliente_id, contenido_id, valor)
	values(p_cliente_id, p_contenido_id, p_valor)
	on duplicate key update valor = p_valor;
end$$

drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int, in p_contenido_id int)
begin
	declare carrito_activo_id int;
	declare contenido_precio decimal(10,2);
	declare tipo_contenido varchar(50);

	select tipo into tipo_contenido from contenido where id = p_contenido_id;
	
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;

	if carrito_activo_id is null then
		insert into carrito(cliente_id) values (p_cliente_id);
		select id into carrito_activo_id from carrito
		where cliente_id = p_cliente_id and activo = true;
	end if;

	if tipo_contenido = 'pelicula' then
        select precio into contenido_precio from pelicula where contenido_id = p_contenido_id;
    elseif tipo_contenido = 'serie' then
        select precio into contenido_precio from serie where contenido_id = p_contenido_id;
    elseif tipo_contenido = 'corto' then
        select precio into contenido_precio from corto where contenido_id = p_contenido_id;
    else
        set contenido_precio = 0.00;
	end if;

	insert into lin_fac(carrito_id, contenido_id, precio) 
	values (carrito_activo_id, p_contenido_id, contenido_precio)
	on duplicate key update precio = values(precio);

	call actualizar_precio_carrito(p_cliente_id);
end$$

drop procedure if exists quitar_producto$$
create procedure quitar_producto(in p_cliente_id int, in p_contenido_id int)
begin
    declare carrito_activo_id int;

    select id into carrito_activo_id 
    from carrito 
    where cliente_id = p_cliente_id and activo = true;

    if carrito_activo_id is null then
        signal sqlstate '45000' set message_text = 'No hay carrito activo para este cliente.';
    end if;

    delete from lin_fac 
    where carrito_id = carrito_activo_id and contenido_id = p_contenido_id;

    call actualizar_precio_carrito(p_cliente_id);
end$$

drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id) values (new.id);
end$$

drop procedure if exists get_cliente_por_email$$
create procedure get_cliente_por_email(in p_email VARCHAR(100))
begin
    select * from cliente where email = p_email;
END$$

drop trigger if exists trigger_valoracion_insert$$
create trigger trigger_valoracion_insert
before insert on valoracion for each row
begin
	if new.valor >10 then set new.valor = 10;
	elseif new.valor < 0 then set new.valor = 0;
	end if;
end$$

drop procedure if exists actualizar_nota$$
create procedure actualizar_nota(IN p_contenido_id int)
begin
	declare puntos_totales decimal(10,2);
	declare cantidad int;
	declare media decimal(10,2);

	select sum(valor) into puntos_totales from valoracion where contenido_id = p_contenido_id;
	select count(*) into cantidad from valoracion where contenido_id = p_contenido_id;
	
	if cantidad > 0 then set media = puntos_totales/cantidad;
	else set media = 0;
	end if;
	
	update contenido set puntuacion_media = media where id = p_contenido_id;
end$$

drop trigger if exists trigger_actualizar_nota$$
create trigger trigger_actualizar_nota
after insert on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end$$

drop procedure if exists actualizar_precio_carrito$$
create procedure actualizar_precio_carrito(in p_cliente_id int)
begin
	declare carrito_activo_id int;
	declare precio_total decimal(10,2);

	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;

	if carrito_activo_id is null then
		insert into carrito(cliente_id) values (p_cliente_id);
		select id into carrito_activo_id from carrito
		where cliente_id = p_cliente_id and activo = true;
	end if;
	
	select sum(precio) into precio_total from lin_fac where carrito_id = carrito_activo_id;
	if precio_total is null then
		set precio_total = 0.00;
	end if;

	update carrito set total = precio_total where id = carrito_activo_id;

end$$

drop procedure if exists get_all_carrito$$
create procedure get_all_carrito(in p_cliente_id int)
begin
    declare carrito_id int;

    select id into carrito_id from carrito where cliente_id = p_cliente_id and activo = true;

    select c.* from contenido c
    join lin_fac l on l.contenido_id = c.id
    where l.carrito_id = carrito_id;
end$$

drop trigger if exists trigger_pelicula_precio$$
create trigger trigger_pelicula_precio
before insert on pelicula
for each row
begin
    declare porcentaje decimal(5,4);

    select t.porcentaje into porcentaje
    from contenido c
    join tarifa t on c.tarifa_id = t.id
    where c.id = new.contenido_id;

    set new.precio = new.precio_base + (new.precio_base * porcentaje);
end$$

drop procedure if exists actualizar_precio_serie$$
create procedure actualizar_precio_serie(in p_serie_id int)
begin
    declare precio_total decimal(10,2);
	declare porcentaje decimal(5,4);
	
    select sum(precio) into precio_total
    from capitulo c
    join temporada t on c.temporada_id = t.id
    where t.serie_id = p_serie_id;
	
	select t.porcentaje into porcentaje
	from serie s
	join tarifa t on s.tarifa_id = t.id
	where s.contenido_id = p_serie_id;

	update serie set precio_base = precio_total,
			precio = precio_total * porcentaje 
	where contenido_id = p_serie_id;

end$$

drop trigger if exists trigger_actualizar_precio_serie_after_insert_capitulo$$
create trigger trigger_actualizar_precio_serie_after_insert_capitulo
after insert on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$

drop trigger if exists trigger_actualizar_precio_serie_after_update_capitulo$$
create trigger trigger_actualizar_precio_serie_after_update_capitulo
after update on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$

drop trigger if exists trigger_actualizar_precio_serie_after_delete_capitulo$$
create trigger trigger_actualizar_precio_serie_after_delete_capitulo
after delete on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$

drop procedure if exists set_precio_contenido$$  
create procedure set_precio_contenido(in p_contenido_id int)  
begin  
    declare tipocontenido varchar(10);  
    declare preciofinal decimal(10,2);  

    select tipo into tipocontenido from contenido where id =  p_contenido_id;  

    if tipocontenido = 'pelicula' then  
        select p.precio into preciofinal from pelicula p where p.contenido_id =  p_contenido_id;  

    elseif tipocontenido = 'corto' then  
        select c.precio into preciofinal from corto c where c.contenido_id =  p_contenido_id;  

    elseif tipocontenido = 'serie' then  
        select s.precio into preciofinal from serie s where s.contenido_id =  p_contenido_id;  
    else  
        set preciofinal = 0.0;  
    end if;  

    update contenido set precio = preciofinal where id = p_contenido_id;  
end$$  

drop trigger if exists trg_set_tipo_precio_pelicula$$
create trigger trg_set_tipo_precio_pelicula
after insert on pelicula for each row
begin
    update contenido set tipo = 'pelicula' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);
end$$

drop trigger if exists trg_set_tipo_precio_serie$$
create trigger trg_set_tipo_precio_serie
after insert on serie for each row
begin
    update contenido set tipo = 'serie' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);
end$$

drop trigger if exists trg_set_tipo_precio_corto$$
create trigger trg_set_tipo_precio_corto
after insert on corto for each row
begin
    update contenido set tipo = 'corto' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);
end$$

delimiter ;
