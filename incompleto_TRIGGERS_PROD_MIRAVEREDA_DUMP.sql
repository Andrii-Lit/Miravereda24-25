-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
-- by Cristóbal

use miravereda2425;

-- #### FUNCIONES ####
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
drop procedure if exists get_cliente_por_email;

create procedure get_cliente_por_email(
    in p_email VARCHAR(100)
)
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

-- #### TABLA LIN_FAC ####
-- PROCEDIMIENTO al que llamará el botón de añadir en la vista detallada del contenido
delimiter $$
drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int)
begin
	-- falta acceder al cliente_id de la sesión actual
	insert into lin_fac, 
end
$$
delimiter ;



