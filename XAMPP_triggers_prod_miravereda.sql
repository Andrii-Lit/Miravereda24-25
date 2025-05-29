#---------------------------------------
#-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
#-- by Cristóbal
#---------------------------------------

use miravereda2425;

delimiter $$

#--------------------------------------------
#-- FUNCIONALIDADES DE LA APLICACIÓN CLIENTE
#--------------------------------------------

#-----------------------------------------------------------------------
#-- PROCEDIMIENTO para el botón INICIAR SESIÓN que devuelva un booleano
#-----------------------------------------------------------------------
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

#------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón COMPRAR en carrito
#------------------------------------------------------------
drop procedure if exists comprar$$
create procedure comprar(IN p_cliente_id int)
begin
    declare carrito_total decimal(10,2);
    declare iva_rate decimal(10,2);
    declare total_con_iva decimal(10,2);

	set total_con_iva = carrito_total + (carrito_total * iva_rate);

	insert into factura(cliente_id, total, total_con_iva)
	values (p_cliente_id, carrito_total, total_con_iva);

	update carrito set activo = false where cliente_id = p_cliente_id and activo = true;

	insert into carrito(cliente_id) values (p_cliente_id);
end$$

#---------------------------------------------------------------------
#-- PROCEDIMIENTO para el botón de VOTAR para insertar una VALORACION
#---------------------------------------------------------------------
drop procedure if exists votar$$
create procedure votar(in p_cliente_id, p_contenido_id, p_valor)
begin
	insert into valoracion (cliente_id, contenido_id, valor)
	values(p_cliente_id, p_contenido_id, p_valor);
end$$

#--------------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón de AÑADIR AL CARRITO en la vista detallada del contenido
#--------------------------------------------------------------------------------------------------
drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int, in p_contenido_id int)
begin
	declare carrito_activo_id int;
	declare contenido_precio decimal(10,2);
	
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;
	
	if p_contenido_id in (select contenido_id from pelicula)
		then set contenido_precio = (select precio from pelicula where contenido_id = p_contenido_id);
	 elseif p_contenido_id in (select contenido_id from serie)
	 	then set contenido_precio = (select precio from serie where contenido_id = p_contenido_id);
	 elseif p_contenido_id in (select contenido_id from corto)
	 	then set contenido_precio = (select precio from corto where contenido_id = p_contenido_id);
	else set contenido_precio = 0.00;
	end if; 	
	insert into lin_fac(carrito_id, contenido_id, precio) 
	values (carrito_activo_id, p_contenido_id, contenido_precio);

	call actualizar_precio_carrito(p_cliente_id);
end$$

#------------------------------------------
#-- PROCEDIMIENTOS Y TRIGGERS para la BBDD
#------------------------------------------

#----------------------------------------------------------------------------------
#-- TRIGGER al hacer INSERT en la tabla CLIENTE se crea un CARRITO asignado a este
#----------------------------------------------------------------------------------
drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id) values (new.id);
end$$

#-------------------------------------------------
#-- PROCEDIMIENTO para obtener CLIENTE por email
#-------------------------------------------------
drop procedure if exists get_cliente_por_email$$
create procedure get_cliente_por_email(in p_email VARCHAR(100))
begin
    select * from cliente where email = p_email limit 1;
end$$

#-------------------------------------------------------------------------------------------
#-- TRIGGER al hacer INSERT en VALORACION para que la nota no sea mayor a 10 ni menor que 0
#-------------------------------------------------------------------------------------------
drop trigger if exists trigger_valoracion_insert$$
create trigger trigger_valoracion_insert
before insert on valoracion for each row
begin
	if new.valor >10 then set new.valor = 10;
	elseif new.valor < 0 then set new.valor = 0;
	end if;
end$$

#-------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar la PUNTUACION_MEDIA de CONTENIDO 
#-------------------------------------------------------------------
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
	
	update contenido set puntuacion_media = media;
end$$

#----------------------------------------------------------------------
#-- TRIGGER que llama a ACTUALIZAR_NOTA al hacer INSERT en VALORACION
#----------------------------------------------------------------------
drop trigger if exists trigger_actualizar_nota$$
create trigger trigger_actualizar_nota
after insert on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end$$

#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que actualiza el precio del carrito activo según las líneas de factura
#-----------------------------------------------------------------------------------------
drop procedure if exists actualizar_precio_carrito$$
create procedure actualizar_precio_carrito(in p_cliente_id int)
begin
	declare carrito_activo_id int;
	declare precio_total decimal(10,2);

	select id into carrito_activo_id from carrito where cliente_id = p_cliente_id and activo = true;
	select sum(precio) into precio_total from lin_fac where carrito_id = carrito_activo_id;

	update carrito set total = precio_total where id = carrito_activo_id;
end$$

#-----------------------------------------------------------------------------------------
#-- TRIGGER para definir el precio en PELICULA
#-----------------------------------------------------------------------------------------
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

#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar el precio en SERIE al insertar en CAPITULO
#-----------------------------------------------------------------------------------------
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
    from contenido c
    join tarifa t on c.tarifa_id = t.id
    where c.id = p_serie_id;

    if precio_total is null then
        set precio_total = 0.00;
    end if;

    update serie set precio_base = precio_total where contenido_id = p_serie_id;
	update serie set precio = (precio_base * porcentaje);
end$$

#-----------------------------------------------------------------------------------------
#-- TRIGGER que actualiza el precio de SERIE al insertar en CAPITULO
#-----------------------------------------------------------------------------------------
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
