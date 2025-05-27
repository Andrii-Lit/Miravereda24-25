-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
-- by Cristóbal

use miravereda2425;

-- #### FUNCIONES ####
-- PROCEDIMIENTO para iniciar sesión que devuelva un booleano
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
end
$$
delimiter ;

-- #### TABLA CLIENTE ####
-- TRIGGER al hacer INSERT en la tabla CLIENTE
-- se crea un CARRITO asignado a este
delimiter $$
drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id)values (new.id);
end
$$
delimiter ;

-- PROCEDIMIENTO para obtener CLIENTE por email
delimiter $$ 
drop procedure if exists get_cliente_por_email$$

create procedure get_cliente_por_email(in p_email VARCHAR(100))
begin
    select * from cliente where email = p_email limit 1;
END
$$
DELIMITER ;



-- #### TABLA VALORACION ####
-- TRIGGER al hacer INSERT en VALORACION para que la nota no sea mayor a 10 ni menor que 0
delimiter $$
drop trigger if exists trigger_valoracion_insert$$
create trigger trigger_valoracion_insert
before insert on valoracion for each row
begin
	if new.valor >10 then set new.valor = 10;
	elseif new.valor < 0 then set new.valor = 0;
	end if;
end
$$
delimiter ;

-- PROCEDIMIENTO para actualizar la PUNTUACION_MEDIA de CONTENIDO 
-- (Se llamará desde TRIGGER_ACTUALIZAR_NOTA)
delimiter $$
drop procedure if exists actualizar_nota$$
create procedure actualizar_nota(IN p_cliente_id int)
begin
	declare puntos_totales decimal(10,2);
	declare cantidad int;
	declare media decimal(10,2);
	
	select sum(valor) into puntos_totales from valoracion where contenido_id = p_cliente_id;
	select count(*) into cantidad from valoracion where contenido_id = p_cliente_id;
	
	if cantidad > 0 then set media = puntos_totales/cantidad;
	else set media = 0;
	end if;
	
	update contenido set puntuacion_media = media;
end
$$
delimiter ;

-- TRIGGER que llama a ACTUALIZAR_NOTA al hacer INSERT en VALORACION
delimiter $$
drop trigger if exists trigger_actualizar_nota$$
create trigger trigger_actualizar_nota
after insert on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end
$$
delimiter ;

-- #### TABLA FACTURA ####
-- PROCEDIMIENTO al que llamará el botón COMPRAR en carrito
delimiter $$
drop procedure if exists comprar$$
create procedure comprar(IN p_cliente_id int)
begin
    declare carrito_total decimal(10,2);
    declare iva_rate decimal(10,2);
    declare total_con_iva decimal(10,2);

	set total_con_iva = carrito_total + (carrito_total * iva_rate);

	-- inserta en factura el id, total sin y con
	insert into factura(cliente_id, total, total_con_iva)
	values (p_cliente_id, carrito_total, total_con_iva);

	-- desactiva el carrito
	update carrito set activo = false where cliente_id = p_cliente_id and activo = true;

    -- al final crea un nuevo carrito
	insert into carrito(cliente_id) values (p_cliente_id);
    
end
$$
delimiter ;

-- #### TABLA CARRITO ####
-- PROCEDIMIENTO que actualiza el precio del carrito activo según las líneas de factura
-- le pasamos el cliente_id para identificar el carrito activo
delimiter $$
drop procedure if exists actualizar_precio_carrito$$
create procedure actualizar_precio_carrito(in p_cliente_id int)
begin
	declare carrito_activo_id int;
	declare precio_total decimal(10,2);
	-- sacamos el carrito activo
	select id into carrito_activo_id from carrito where cliente_id = p_cliente_id and activo = true;
	-- sacamos la suma de las lin_fac asociadas al carrito activo
	select sum(precio) into precio_total from lin_fac where carrito_id = carrito_activo_id;

	update carrito set total = precio_total where id = carrito_activo_id;

end
$$
delimiter;

-- #### TABLA LIN_FAC ####
-- PROCEDIMIENTO al que llamará el botón de añadir al carrito en la vista detallada del contenido
-- le pasamos el cliente_id porque buscará luego el carrito activo del cliente
delimiter $$
drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int, in p_contenido_id int)
begin
	declare carrito_activo_id int;
	declare contenido_precio decimal(10,2);
	-- variable que recoge el id del carrito activo del cliente
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;
	-- variable que recoge el precio según el tipo de contenido
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
end
$$
delimiter ;



