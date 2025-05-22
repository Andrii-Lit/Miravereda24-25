-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
-- by Cristóbal

use miravereda2425;


-- #### TABLA CLIENTE ####
-- PROCEDIMIENTO y TRIGGERS para actualizar el changedTS de CLIENTE
delimiter $$
drop procedure if exists actualizar_changedTS_cliente$$
create procedure actualizar_changedTS_cliente(in cliente_id int)
begin
	update cliente set changedTS = now() where id = cliente_id;
end
$$
delimiter ;

-- TRIGGER INSERT
delimiter $$
drop trigger if exists trigger_cliente_insert$$
create trigger trigger_cliente_insert
after insert on cliente for each row
begin
	call actualizar_changedTS_cliente(new.id);
end
$$
delimiter ;

-- TRIGGER UPDATE
delimiter $$
drop trigger if exists trigger_cliente_update$$
create trigger trigger_cliente_update
after update on cliente for each row
begin
	call actualizar_changedTS_cliente;
end
$$
delimiter ;

-- TRIGGER al hacer INSERT en la tabla CLIENTE
-- se crea un CARRITO asignado a este
delimiter $$
drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id, total, changedTS)
	values (new.id, 0, now());
end
$$
delimiter ;


-- #### TABLA VALORACION ####
-- TRIGGER al hacer INSERT en VALORACION para que la nota no sea mayor a 10 ni menor que 0, también el changedTS es now()
delimiter $$
drop trigger if exists trigger_valoracion_insert$$
create trigger trigger_valoracion_insert
before insert on valoracion for each row
begin
	if new.valor >10 then set new.valor = 10;
	elseif new.valor < 0 then set new.valor = 0;
	end if;
	set new.changedTS = now();
end
delimiter ;

-- PROCEDIMIENTO para actualizar la PUNTUACION_MEDIA de CONTENIDO 
-- (Se llamará desde TRIGGER_ACTUALIZAR_NOTA)
delimiter $$
drop procedure if exists actualizar_nota$$
create procedure if exists actualizar_nota(IN id int)
begin
	declare puntos_totales decimal(10,2);
	declare cantidad int;
	declare media decimal(10,2)
	
	select sum(valor) into puntos_totales from valoracion where contenido_id = id;
	select count(*) into cantidad from valoracion where contenido_id = id;
	
	if cantidad > 0 then set media = puntos_totales/cantidad
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

-- #### TABLA CONTENIDO ####
-- PROCEDIMIENTO actualizar_changedTS_contenido
delimiter $$
drop procedure if exists actualizar_changedTS_contenido$$
create procedure actualizar_changedTS_contenido(in contenido_id int)
begin
	update contenido set changedTS = now() where id = contenido_id;
end
$$
delimiter ;
-- TRIGGERS para changedTS
delimiter $$
drop trigger if exists trigger_contenido_insert$$
create trigger trigger_contenido_insert
after insert on contenido for each row
begin
	call actualizar_changedTS_contenido(new.id)
end
$$
delimiter ;





