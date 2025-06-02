#---------------------------------------
#-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
#-- by Cristóbal
#---------------------------------------

use miravereda2425;

#-----------------------------------------------------------------------
#-- FUNCIONALIDADES DE LA APLICACIÓN CLIENTE
#-----------------------------------------------------------------------
#-- PROCEDIMIENTO para el botón INICIAR SESIÓN que devuelva un booleano
#-----------------------------------------------------------------------
delimiter $$
drop procedure if exists iniciar_sesion$$
create procedure iniciar_sesion(out resultado boolean, in p_email varchar(100), in p_contrasenya varchar(255))
begin
	declare contra_correcta varchar(255);

	-- Sacamos la contraseña asociada al email por parámetro en la bbdd
	select contrasenya into contra_correcta from cliente where email = p_email limit 1;
	
	-- y si coincide con la que le pasamos por parámetro devuelve true
	if contra_correcta is null 
		then set resultado = false;
	elseif p_contrasenya = contra_correcta 
		then set resultado = true;
	else set resultado = false;
	end if;
end$$
delimiter ;

#------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón COMPRAR en CARRITO
#------------------------------------------------------------
delimiter $$
drop procedure if exists comprar$$
create procedure comprar(IN p_cliente_id int)
begin
    declare carrito_total decimal(10,2);
    declare iva decimal(10,2);
    declare total_con_iva decimal(10,2);
    declare cant_lineas int;
    declare carrito_activo_id int;

    -- Recogemos el CARRITO_id activo
    select id, total into carrito_activo_id, carrito_total
    from carrito 
    where cliente_id = p_cliente_id and activo = true limit 1;

    -- Recogemos el total del CARRITO activo
    -- (ya se obtuvo junto al carrito_id)

    -- mensaje de error en caso de no haber carritos activos
    if carrito_total is null then
        signal sqlstate '45000' set message_text = 'No hay carrito activo para este cliente, prueba a añadir algo para crearlo';
    end if;

    -- Seteamos el iva y el total con iva
    set iva = 0.21;
    set total_con_iva = carrito_total + (carrito_total * iva);

    -- Contamos cuántas líneas tiene el carrito activo
    -- para no comprar un carrito vacío
    select count(*) into cant_lineas 
    from lin_fac 
    where carrito_id = carrito_activo_id;

    if cant_lineas = 0 then
        signal sqlstate '45000' set message_text = 'El carrito está vacío. Añade algunos productos antes de comprar.';
    elseif cant_lineas is null then
        signal sqlstate '45000' set message_text = 'Error interno, revisa el procedimiento.';
    end if;

    -- Hace INSERT en FACTURA el cliente_id, total sin y con el IVA
    insert into factura(cliente_id, total, total_con_iva)
    values (p_cliente_id, carrito_total, total_con_iva);

    -- Desactiva el CARRITO
    update carrito set activo = false where id = carrito_activo_id;

    -- Al final crea un nuevo CARRITO
    insert into carrito(cliente_id) values (p_cliente_id);
end$$
delimiter ;


#---------------------------------------------------------------------
#-- PROCEDIMIENTO para el botón de VOTAR para insertar una VALORACION
#---------------------------------------------------------------------
delimiter $$
drop procedure if exists votar$$
create procedure votar(in p_cliente_id int, in p_contenido_id int, in p_valor int)
begin
	insert into valoracion (cliente_id, contenido_id, valor)
	values(p_cliente_id, p_contenido_id, p_valor)
	on duplicate key update valor = p_valor;
	-- on duplicate actualiza el valor en caso de votar otra vez
end$$
delimiter ;

#--------------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón de AÑADIR AL CARRITO en la vista detallada del contenido
#-- le pasamos el cliente_id porque buscará luego el carrito activo del cliente
#--------------------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int, in p_contenido_id int)
begin
	declare carrito_activo_id int;
	declare contenido_precio decimal(10,2);
	declare tipo_contenido varchar(50);

	-- Recogemos el tipo de contenido
	select tipo into tipo_contenido from contenido where id = p_contenido_id;
	
	-- Recogemos el id del carrito activo del cliente
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;

	-- En caso de que no haya carrito asignado al cliente
	if carrito_activo_id is null then
		insert into carrito(cliente_id) values (p_cliente_id);
		select id into carrito_activo_id from carrito
		where cliente_id = p_cliente_id and activo = true;
	end if;

	-- Recogemos el precio según el tipo de contenido
	if tipo_contenido = 'pelicula' then
        select precio into contenido_precio from pelicula where contenido_id = p_contenido_id;
    elseif tipo_contenido = 'serie' then
        select precio into contenido_precio from serie where contenido_id = p_contenido_id;
    elseif tipo_contenido = 'corto' then
        select precio into contenido_precio from corto where contenido_id = p_contenido_id;
    else
        set contenido_precio = 0.00;
	end if;

	-- Añadimos la LIN_FAC (el producto al carrito)
	-- Si añadimos el mismo producto actualiza el precio
	insert into lin_fac(carrito_id, contenido_id, precio) 
	values (carrito_activo_id, p_contenido_id, contenido_precio)
	on duplicate key update precio = values(precio);

	-- Actualizamos el precio del carrito
	call actualizar_precio_carrito(p_cliente_id);
end$$
delimiter ;

#----------------------------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón para QUITAR UN PRODUCTO del CARRITO
#----------------------------------------------------------------------------------
delimiter $$
drop procedure if exists quitar_producto$$
create procedure quitar_producto(in p_cliente_id int, in p_contenido_id int)
begin
    declare carrito_activo_id int;

    -- Recogemos el carrito activo
    select id into carrito_activo_id 
    from carrito 
    where cliente_id = p_cliente_id and activo = true;

	-- En caso de ser nulo, error
    if carrito_activo_id is null then
        signal sqlstate '45000' set message_text = 'No hay carrito activo para este cliente.';
    end if;

    -- Borramos el LIN_FAC de la bbdd
    delete from lin_fac 
    where carrito_id = carrito_activo_id and contenido_id = p_contenido_id;

    -- Actualizamos el total del carrito
    call actualizar_precio_carrito(p_cliente_id);
end$$
delimiter ;


#----------------------------------------------------------------------------------
#-- PROCEDIMIENTOS Y TRIGGERS para la BBDD
#----------------------------------------------------------------------------------
#-- TABLA CLIENTE
#----------------------------------------------------------------------------------
#-- TRIGGER que al hacer INSERT en la tabla CLIENTE se crea un CARRITO asignado a este
#-- De momento no añadimos la opción de cambiar la información del cliente
#-- Los que tienen el cliente_id como clave ajena tienen on delete cascade
#----------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id) values (new.id);
end$$
delimiter ;

#-------------------------------------------------
#-- PROCEDIMIENTO para obtener CLIENTE por email
#-- Hecho por Iván
#-------------------------------------------------
delimiter $$ 
drop procedure if exists get_cliente_por_email$$
create procedure get_cliente_por_email(in p_email VARCHAR(100))
begin
    select * from cliente where email = p_email;
END$$
DELIMITER ;

#-------------------------------------------------------------------------------------------
#-- TABLA VALORACION 
#-------------------------------------------------------------------------------------------
#-- TRIGGER al hacer INSERT en VALORACION para que la nota no sea mayor a 10 ni menor que 0
#-------------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trigger_valoracion_insert$$

create trigger trigger_valoracion_insert
before insert on valoracion for each row
begin
	if new.valor >10 then set new.valor = 10;
	elseif new.valor < 0 then set new.valor = 0;
	end if;
end$$
delimiter ;
#-------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar la PUNTUACION_MEDIA de CONTENIDO 
#-- Se llamará desde TRIGGER_ACTUALIZAR_NOTA
#-------------------------------------------------------------------
delimiter $$
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
delimiter ;
#----------------------------------------------------------------------
#-- TRIGGER que actualiza la nota al hacer INSERT en VALORACION
#----------------------------------------------------------------------
delimiter $$
drop trigger if exists trigger_actualizar_nota$$
create trigger trigger_actualizar_nota
after insert on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end$$
delimiter ;

#-----------------------------------------------------------------------------------------
#-- TABLA CARRITO 
#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que actualiza el precio del carrito activo según las líneas de factura
#-- le pasamos el cliente_id para identificar el carrito activo
#-----------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists actualizar_precio_carrito$$
create procedure actualizar_precio_carrito(in p_cliente_id int)
begin
	declare carrito_activo_id int;
	declare precio_total decimal(10,2);
	-- Recogemos el id del carrito activo del cliente
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;

	-- En caso de que no haya carrito asignado al cliente
	if carrito_activo_id is null then
		insert into carrito(cliente_id) values (p_cliente_id);
		select id into carrito_activo_id from carrito
		where cliente_id = p_cliente_id and activo = true;
	end if;
	
	-- Sacamos la suma de las lin_fac asociadas al carrito activo
	select sum(precio) into precio_total from lin_fac where carrito_id = carrito_activo_id;
	if precio_total is null then
		set precio_total = 0.00;
	end if;

	update carrito set total = precio_total where id = carrito_activo_id;

end$$
delimiter;
#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO para obtener todos los productos del carrito
#-----------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists get_all_carrito$$
create procedure get_all_carrito(in p_cliente_id int)
begin
    declare carrito_id int;

    -- Recogemos el carrito activo
    select id into carrito_id from carrito where cliente_id = p_cliente_id and activo = true;

    -- Seleccionar * de contenido asociado al carrito activo
    select c.* from contenido c
    join lin_fac l on l.contenido_id = c.id
    where l.carrito_id = carrito_id;
end$$
delimiter ;

#-----------------------------------------------------------------------------------------
#-- TABLAS DE CONTENIDO
#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar el precio de CONTENIDO
#-- Se llamará desde los TRIGGERS de PELICULA y CORTO
#-----------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists actualizar_precio_contenido$$
create procedure actualizar_precio_contenido(in p_contenido_id int, in p_precio decimal(10,2))
begin
    update contenido set precio = p_precio where id = p_contenido_id;
end$$
delimiter ;

#----------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el precio de PELICULA
#----------------------------------------------------------------------------------------
#-- INSERT
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_pelicula_insert_actualizar_precio$$
create trigger trg_pelicula_insert_actualizar_precio
after insert on pelicula for each row
begin
    call actualizar_precio_contenido(new.contenido_id, new.precio);
end$$
delimiter;
#----------------------------------------------------------------------------------------
#-- UPDATE
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_pelicula_update_actualizar_precio$$
create trigger trg_pelicula_update_actualizar_precio
after update on pelicula for each row
begin
    call actualizar_precio_contenido(new.contenido_id, new.precio);
end$$
delimiter;

#----------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el precio de CORTO
#----------------------------------------------------------------------------------------
#-- INSERT
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_corto_insert_actualizar_precio$$
create trigger trg_corto_insert_actualizar_precio
after insert on corto for each row
begin
    call actualizar_precio_contenido(new.contenido_id, new.precio);
end$$
delimiter;
#----------------------------------------------------------------------------------------
#-- UPDATE
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_corto_update_actualizar_precio$$
create trigger trg_corto_update_actualizar_precio
after update on corto for each row
begin
    call actualizar_precio_contenido(new.contenido_id, new.precio);
end$$
delimiter;

#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar el precio en SERIE al insertar en CAPITULO
#-- se llamará desde los TRIGGERS en CAPITULO
#-----------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists actualizar_precio_serie$$
create procedure actualizar_precio_serie(in p_serie_id int)
begin
    declare precio_total decimal(10,2);
	declare porcentaje decimal(5,2);
	
	-- El precio_total es la suma de los capitulos
    select sum(precio) into precio_total
    from capitulo c
    join temporada t on c.temporada_id = t.id
    where t.serie_id = p_serie_id;
	
	-- Recogemos el porcentaje de la TARIFA de la serie
	select t.porcentaje into porcentaje
	from serie s
	join tarifa t on s.tarifa_id = t.id
	where s.contenido_id = p_serie_id;

	-- Actualizamos el precio_base de la serie (la suma del precio de los capitulos)
	-- y el precio de la serie (el precio_base aplicándole el porcentaje de la TARIFA)
	update serie set precio_base = precio_total,
			precio = precio_total * porcentaje 
	where contenido_id = p_serie_id;

end$$
delimiter ;

#----------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el precio de SERIE al hacer INSERT/UPDATE/DELETE en CAPITULO
#----------------------------------------------------------------------------------------
#-- EXPLICACIÓN:
#-- Recogemos el serie_id de la TEMPORADA a la que pertenece el CAPITULO insertado
#-- y llamamos al método con el serie_id por parámetro
#----------------------------------------------------------------------------------------
#-- INSERT
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trigger_actualizar_precio_serie_after_insert_capitulo$$
create trigger trigger_actualizar_precio_serie_after_insert_capitulo
after insert on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$
delimiter ;
#----------------------------------------------------------------------------------------
#-- UPDATE
#----------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trigger_actualizar_precio_serie_after_update_capitulo$$
create trigger trigger_actualizar_precio_serie_after_update_capitulo
after update on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$
delimiter ;
delimiter $$
#----------------------------------------------------------------------------------------
#-- DELETE
#----------------------------------------------------------------------------------------
drop trigger if exists trigger_actualizar_precio_serie_after_delete_capitulo$$
create trigger trigger_actualizar_precio_serie_after_delete_capitulo
after delete on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = old.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$
delimiter ;

#----------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que setea el precio calculado de PELICULA/SERIE/CAPITULO en CONTENIDO
#----------------------------------------------------------------------------------------------
delimiter $$  
drop procedure if exists set_precio_contenido$$
create procedure set_precio_contenido(in p_contenido_id int)  
begin  
    declare tipocontenido varchar(10);  
    declare preciofinal decimal(10,2);  

    -- Recogemos el tipo de contenido  
    select tipo into tipocontenido from contenido where id =  p_contenido_id;  

	-- Según el tipo de CONTENIDO recogemos el precio ya calculado
    if tipocontenido = 'pelicula' then  
        select p.precio into preciofinal from pelicula p where p.contenido_id =  p_contenido_id;  

    elseif tipocontenido = 'corto' then  
        select c.precio into preciofinal from corto c where c.contenido_id =  p_contenido_id;  

    elseif tipocontenido = 'serie' then  
        select s.precio into preciofinal from serie s where s.contenido_id =  p_contenido_id;  
    else  
        set preciofinal = 0.0;  
    end if;  

    -- seteamos el precio de CONTENIDO con el de PELICULA/SERIE/CORTO
    update contenido set precio = preciofinal where id = p_contenido_id;  
end$$  
delimiter ;


#----------------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el tipo y precio de CONTENIDO al insertar en los campos derivantes 
#----------------------------------------------------------------------------------------------
#-- PELICULA
#----------------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_set_tipo_precio_pelicula$$
create trigger trg_set_tipo_precio_pelicula
after insert on pelicula for each row
begin
    update contenido set tipo = 'pelicula' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);

end$$
delimiter ;
#----------------------------------------------------------------------------------------------
#-- SERIE
#----------------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_set_tipo_precio_serie$$
create trigger trg_set_tipo_precio_serie
after insert on serie for each row
begin
    update contenido set tipo = 'serie' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);
end$$
delimiter ;
#----------------------------------------------------------------------------------------------
#-- CORTO
#----------------------------------------------------------------------------------------------
delimiter $$
drop trigger if exists trg_set_tipo_precio_corto$$
create trigger trg_set_tipo_precio_corto
after insert on corto for each row
begin
    update contenido set tipo = 'corto' where id = new.contenido_id;
	call set_precio_contenido(new.contenido_id);
end$$
delimiter ; 
#----------------------------------------------------------------------------------------------
#-- TABLA TARIFA
#----------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que modifica TODAS las TARIFAS de golpe
#-- Se le pasará el valor por el que sumar o restar el porcentaje (positivo o negativo) 
#----------------------------------------------------------------------------------------------
delimiter $$
drop procedure if exists alter_all_tarifas$$
create procedure alter_all_tarifas(in p_valor decimal(5,2))
begin
    if p_valor != 0 then 
        update tarifa set porcentaje = (porcentaje + p_valor);
    else
        signal sqlstate '45000' set message_text = 'Ingresa un valor diferente a 0.';
    end if;
end
delimiter ;
