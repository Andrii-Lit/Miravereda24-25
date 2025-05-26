-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
-- by Cristóbal

use miravereda2425;

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

	select total into carrito_total from carrito where cliente_id = p_cliente_id and activo = true;

	select iva into iva_rate from factura limit 1;

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
create procedure anyadir_al_carrito
begin
	-- falta acceder al cliente_id de la sesión actual
	insert into lin_fac, 
end
$$
delimiter ;

-- TO DO:
-- trigger para borrar el carrito asociado a CLIENTE 

-- #### TABLA CARRITO ####
Delimiter $$
drop trigger if exists trg_del_carrito_cliente$$
create trigger trg_del_carrito_cliente
after delete on cliente for each row
begin
    delete from carrito where cliente_id=old.id;
    end;

  Delimiter $  



