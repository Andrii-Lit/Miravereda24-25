#--------------------------------------------------------
#-- SCRIPT PARA CREAR LA BASE DE DATOS MIRAVEREDA
#--------------------------------------------------------
#-- by Cristóbal
#-- Se han dejado campos sin NOT NULL para hacer pruebas
#--------------------------------------------------------

#-----------------
#-- BASE DE DATOS
#-----------------
drop database if exists miravereda2425;
create database miravereda2425;
use miravereda2425;

#------------
#-- CLIENTE
#------------
create table cliente (
    id int auto_increment primary key,
    contrasenya varchar(255),
    nombre varchar(50),
    apellidos varchar(100),
    domicilio varchar(255),
    cod_postal varchar(50),
    email varchar(100) unique,
    fecha_nac date,
    num_tarjeta varchar(100),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

#-----------------------------------
#-- TABLAS DE CONTENIDO AUDIOVISUAL 
#-----------------------------------

#--------------
#-- CONTENIDO
#--------------
create table contenido (
 id int primary key,
 titulo varchar(255),
 descripcion text,
 genero varchar(50),
 nombre_dir varchar(255),
 duracion int,
 actores_principales text,
 fecha_estreno date,
 puntuacion_media decimal(2,1),
 poster_path varchar(255),
 tipo varchar(50),
 precio decimal(10,2),
 changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

#-----------
#-- TARIFA
#-----------
create table tarifa(
    id int primary key,
    tipo varchar(50),
    porcentaje decimal(5,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP

);
#---------------
#-- VALORACION
#---------------
create table valoracion (
    cliente_id int,
    contenido_id int,
    valor int,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    primary key (cliente_id, contenido_id),
    foreign key (cliente_id) references cliente(id) on delete cascade,
    foreign key (contenido_id) references contenido(id)

);

#-------------
#-- PELICULA
#-------------
create table pelicula(
    contenido_id int primary key,
    tarifa_id int,
    disponible_hasta date,
    precio decimal(10,2) default 0.00,
    precio_base decimal(10,2) default 0.00,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id) on delete cascade,
    foreign key (tarifa_id) references tarifa(id)
);

#----------
#-- SERIE
#----------
create table serie(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2) default 0.00,
    precio_base decimal(10,2) default 0.00,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id) on delete cascade,
    foreign key (tarifa_id) references tarifa(id)
);

#----------
#-- CORTO
#----------
create table corto(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2) default 0.00,
    precio_base decimal(10,2) default 0.00,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id) on delete cascade,
    foreign key (tarifa_id) references tarifa(id)
);

#--------------
#-- TEMPORADA
#--------------
create table temporada(
    id int auto_increment primary key,
    serie_id int,
    numero int,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (serie_id) references serie(contenido_id) on delete cascade,
    unique (serie_id, numero)
);

#-------------
#-- CAPITULO
#-------------
create table capitulo(
    id int auto_increment primary key,
    temporada_id int,
    titulo varchar(255),
    precio_base decimal(10,2) default 0.00,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (temporada_id) references temporada(id) on delete cascade
);

#-----------------------
#-- TABLAS DE COMERCIO
#-----------------------

#------------
#-- CARRITO
#------------
create table carrito(
    id int auto_increment primary key,
    cliente_id int,
    total decimal(10,2) default 0.00,
    activo boolean default true,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (cliente_id) references cliente (id) on delete cascade
);

#------------
#-- LIN_FAC
#------------
create table lin_fac(
    carrito_id int,
    contenido_id int,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    primary key(carrito_id, contenido_id),
    foreign key (carrito_id) references carrito(id) on delete cascade,
    foreign key (contenido_id) references contenido(id),
    unique key unq_carrito_contenido (carrito_id, contenido_id)

);

#------------
#-- FACTURA
#------------
create table factura(
    id int primary key auto_increment,
    cliente_id int,
    total_con_iva decimal(10,2),
    total decimal(10,2),
    iva decimal default 0.21,
    fecha timestamp default CURRENT_TIMESTAMP,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (cliente_id) references cliente (id) on delete cascade
);

#----------------------
#-- INSERTAMOS TARIFAS
#----------------------
insert into tarifa(id, tipo, porcentaje)
values (1,'pelicula', 0.20), (2,'serie', 0.10), (3,'corto', 0.05);

#-------------------------------
#-- INSERTAMOS ALGUNOS CLIENTES
#-------------------------------
insert into cliente 
(contrasenya, nombre, apellidos, domicilio, cod_postal, email, fecha_nac, num_tarjeta)
values
-- Usuario por defecto para pruebas, antes de meter a media finca de Aquí no hay quien viva
('1234', 'abc', 'def', 'direccion1', '46160', 'abc@gmail.com', '2000-01-01', '1234567812345679'),

-- Juan Cuesta, presidente de la comunidad
('juan123', 'Juan', 'Cuesta', 'Desengaño 21, 2ºA', '28004', 'juan.cuesta@miravereda.es', '1965-03-14', '1234567812345678'),

-- Emilio Delgado
('emilio456', 'Emilio', 'Delgado', 'Desengaño 21, portería', '28004', 'emilio.delgado@miravereda.es', '1978-07-22', '2345678923456789'),

-- Lucía Álvarez
('lucia789', 'Lucía', 'Álvarez', 'Desengaño 21, 3ºB', '28004', 'lucia.alvarez@miravereda.es', '1980-11-05', '3456789034567890'),

-- Mauri Hidalgo
('mauri101', 'Mauri', 'Hidalgo', 'Desengaño 21, 1ºC', '28004', 'mauri.hidalgo@miravereda.es', '1972-02-10', '4567890145678901'),

-- Belén López
('belen202', 'Belén', 'López', 'Desengaño 21, 3ºA', '28004', 'belen.lopez@miravereda.es', '1979-08-30', '5678901256789012'),

-- Marisa Benito
('marisa303', 'Marisa', 'Benito', 'Desengaño 21, 1ºA', '28004', 'marisa.benito@miravereda.es', '1942-06-12', '6789012367890123'),

-- Vicenta Benito
('vicenta404', 'Vicenta', 'Benito', 'Desengaño 21, 1ºA', '28004', 'vicenta.benito@miravereda.es', '1945-09-18', '7890123478901234'),

-- Concha
('concha505', 'Concha', 'Jiménez', 'Desengaño 21, 2ºB', '28004', 'concha.jimenez@miravereda.es', '1938-01-27', '8901234589012345'),

-- Usuario de Andrii 
('andrii123', 'Andrii', 'Litvinov', 'Desengaño 21, 2ºB', '28004', 'andlit@miravereda.es', '1938-01-27', '8901234589012345');

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

#------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón COMPRAR en CARRITO
#------------------------------------------------------------
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


#---------------------------------------------------------------------
#-- PROCEDIMIENTO para el botón de VOTAR para insertar una VALORACION
#---------------------------------------------------------------------
drop procedure if exists votar$$
create procedure votar(in p_cliente_id int, in p_contenido_id int, in p_valor int)
begin
	insert into valoracion (cliente_id, contenido_id, valor)
	values(p_cliente_id, p_contenido_id, p_valor)
	on duplicate key update valor = p_valor;
	-- on duplicate actualiza el valor en caso de votar otra vez
end$$

#--------------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón de AÑADIR AL CARRITO en la vista detallada del contenido
#-- le pasamos el cliente_id porque buscará luego el carrito activo del cliente
#--------------------------------------------------------------------------------------------------
drop procedure if exists anyadir_al_carrito$$
create procedure anyadir_al_carrito(in p_cliente_id int, in p_contenido_id int)
begin
	declare carrito_activo_id int;
	declare contenido_precio decimal(10,2);

	-- Recogemos el id del carrito activo del cliente
	select id into carrito_activo_id from carrito
	where cliente_id = p_cliente_id and activo = true;

	-- En caso de que no haya carrito asignado al cliente
	if carrito_activo_id is null then
		insert into carrito(cliente_id) values (p_cliente_id);
		select id into carrito_activo_id from carrito
		where cliente_id = p_cliente_id and activo = true;
	end if;

	-- Recogemos el precio del contenido
	select precio into contenido_precio from contenido where id = p_contenido_id;

	-- Añadimos la LIN_FAC (el producto al carrito)
	-- Si añadimos el mismo producto actualiza el precio
	insert into lin_fac(carrito_id, contenido_id, precio) 
	values (carrito_activo_id, p_contenido_id, contenido_precio)
	on duplicate key update precio = values(precio);

	-- Actualizamos el precio del carrito
	call actualizar_precio_carrito(p_cliente_id);
end$$

#----------------------------------------------------------------------------------
#-- PROCEDIMIENTO al que llamará el botón para QUITAR UN PRODUCTO del CARRITO
#----------------------------------------------------------------------------------
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


#----------------------------------------------------------------------------------
#-- PROCEDIMIENTOS Y TRIGGERS para la BBDD
#----------------------------------------------------------------------------------
#-- TABLA CLIENTE
#----------------------------------------------------------------------------------
#-- TRIGGER que al hacer INSERT en la tabla CLIENTE se crea un CARRITO asignado a este
#-- De momento no añadimos la opción de cambiar la información del cliente
#-- Los que tienen el cliente_id como clave ajena tienen on delete cascade
#----------------------------------------------------------------------------------
drop trigger if exists trigger_cliente_carrito$$
create trigger trigger_cliente_carrito
after insert on cliente for each row
begin
	insert into carrito(cliente_id) values (new.id);
end$$

#-------------------------------------------------
#-- PROCEDIMIENTO para obtener CLIENTE por email
#-- Hecho por Iván
#-------------------------------------------------
drop procedure if exists get_cliente_por_email$$
create procedure get_cliente_por_email(in p_email VARCHAR(100))
begin
    select * from cliente where email = p_email;
END$$

#-------------------------------------------------------------------------------------------
#-- TABLA VALORACION 
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
#-- Se llamará desde TRIGGER_ACTUALIZAR_NOTA
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
	
	update contenido set puntuacion_media = media where id = p_contenido_id;
end$$
#----------------------------------------------------------------------
#-- TRIGGER que actualiza la nota al hacer INSERT en VALORACION
#----------------------------------------------------------------------
drop trigger if exists trigger_actualizar_nota$$
create trigger trigger_actualizar_nota
after insert on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end$$
#----------------------------------------------------------------------
#-- TRIGGER que actualiza la nota al hacer UPDATE (volver a votar) en VALORACION
#----------------------------------------------------------------------
drop trigger if exists trigger_actualizar_nota_update$$
create trigger trigger_actualizar_nota_update
after update on valoracion for each row
begin
	call actualizar_nota(new.contenido_id);
end$$

#-----------------------------------------------------------------------------------------
#-- TABLA CARRITO 
#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que actualiza el precio del carrito activo según las líneas de factura
#-- le pasamos el cliente_id para identificar el carrito activo
#-----------------------------------------------------------------------------------------
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
#-----------------------------------------------------------------------------------------
#-- PROCEDIMIENTO para obtener todos los productos del carrito
#-----------------------------------------------------------------------------------------
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

#-----------------------------------------------------------------------------
#-- TABLAS DE CONTENIDO
#-----------------------------------------------------------------------------
#-- PROCEDIMIENTO para actualizar el precio en SERIE al insertar en CAPITULO
#-- se llamará desde los TRIGGERS en CAPITULO
#-----------------------------------------------------------------------------
drop procedure if exists actualizar_precio_serie$$
create procedure actualizar_precio_serie(in p_serie_id int)
begin
    declare precio_total decimal(10,2);
	declare porcentaje decimal(5,2);
	
	-- El precio_total es la suma de los capitulos
     select ifnull(sum(c.precio_base), 0) into precio_total
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
			precio = precio_total * (1+porcentaje) 
	where contenido_id = p_serie_id;

end$$

#----------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el precio de SERIE al hacer INSERT/UPDATE/DELETE en CAPITULO
#----------------------------------------------------------------------------------------
#-- EXPLICACIÓN:
#-- Recogemos el serie_id de la TEMPORADA a la que pertenece el CAPITULO insertado
#-- y llamamos al método con el serie_id por parámetro
#----------------------------------------------------------------------------------------

#-- AFTER INSERT
drop trigger if exists trigger_actualizar_precio_serie_after_insert_capitulo$$
create trigger trigger_actualizar_precio_serie_after_insert_capitulo
after insert on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$

#-- AFTER UPDATE
drop trigger if exists trigger_actualizar_precio_serie_after_update_capitulo$$
create trigger trigger_actualizar_precio_serie_after_update_capitulo
after update on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = new.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$

#-- AFTER DELETE
drop trigger if exists trigger_actualizar_precio_serie_after_delete_capitulo$$
create trigger trigger_actualizar_precio_serie_after_delete_capitulo
after delete on capitulo
for each row
begin
    declare serie_id int;
    select serie_id into serie_id from temporada where id = old.temporada_id;
    call actualizar_precio_serie(serie_id);
end$$


#----------------------------------------------------------------------------------------------
#-- TRIGGERS que actualizan el tipo y precio de CONTENIDO al insertar en los campos derivantes 
#----------------------------------------------------------------------------------------------
#-- PELICULA
#----------------------------------------------------------------------------------------------
drop trigger if exists trg_set_tipo_y_precio_pelicula$$
create trigger trg_set_tipo_y_precio_pelicula
before insert on pelicula
for each row
begin
    declare porcentaje decimal(5,2);
    declare precio_final decimal(10,2);

    -- Obtener porcentaje de tarifa
    select t.porcentaje into porcentaje
    from tarifa t
    where t.id = new.tarifa_id;

    -- Calcular precio final (new.precio_base ya debería venir con el valor)
    set precio_final = new.precio_base * (1 + porcentaje);

    -- Asignar el precio calculado directamente a new.precio
    set new.precio = precio_final;
end$$


#----------------------------------------------------------------------------------------------
#-- SERIE
#----------------------------------------------------------------------------------------------
drop trigger if exists trg_set_tipo_precio_serie$$
create trigger trg_set_tipo_precio_serie
before insert on serie
for each row
begin
    declare porcentaje decimal(5,2);
    declare precio_base decimal(10,2);
    declare precio_total decimal(10,2);

    -- Obtener el porcentaje de la tarifa
    select t.porcentaje into porcentaje
    from tarifa t
    where t.id = new.tarifa_id;

    -- Al insertar una serie nueva sin capítulos, el precio_base es 0
    set precio_base = 0;

    -- Calcular el precio total aplicando tarifa
    set precio_total = precio_base * (1 + porcentaje);

    -- En lugar de hacer UPDATE a la tabla, asignamos directamente a los campos de NEW
    set new.precio_base = precio_base;
    set new.precio = precio_total;

    -- Para actualizar la tabla contenido, esto no puede hacerse directamente en el BEFORE INSERT del trigger de serie
    -- Lo mejor es usar un AFTER INSERT separado para actualizar contenido o manejarlo en la lógica de aplicación

end$$

#----------------------------------------------------------------------------------------------
#-- TRIGGERS para actualizar precio y tipo en CONTENIDO al insertar en PELICULA, SERIE o CORTO
#----------------------------------------------------------------------------------------------

-- PELICULA
drop trigger if exists trg_update_contenido_after_insert_pelicula$$
create trigger trg_update_contenido_after_insert_pelicula
after insert on pelicula
for each row
begin
    update contenido 
    set precio = new.precio, tipo = 'pelicula'
    where id = new.contenido_id;
end$$

-- SERIE
drop trigger if exists trg_update_contenido_after_insert_serie$$
create trigger trg_update_contenido_after_insert_serie
after insert on serie
for each row
begin
    update contenido 
    set precio = new.precio, tipo = 'serie'
    where id = new.contenido_id;
end$$

-- CORTO
drop trigger if exists trg_update_contenido_after_insert_corto$$
create trigger trg_update_contenido_after_insert_corto
after insert on corto
for each row
begin
    update contenido 
    set precio = new.precio, tipo = 'corto'
    where id = new.contenido_id;
end$$

#----------------------------------------------------------------------------------------------
#-- CORTO
#----------------------------------------------------------------------------------------------
drop trigger if exists trg_set_tipo_precio_corto$$
create trigger trg_set_tipo_precio_corto
before insert on corto
for each row
begin
    declare porcentaje decimal(5,2);
    declare precio_total decimal(10,2);

    -- Obtener el porcentaje de la tarifa
    select t.porcentaje into porcentaje
    from tarifa t
    where t.id = new.tarifa_id;

    -- Calcular precio con tarifa (precio_base ya viene en new.precio_base)
    set precio_total = new.precio_base * (1 + porcentaje);

    -- En lugar de hacer UPDATE a corto, asignamos directamente el valor a NEW
    set new.precio = precio_total;
end$$


#----------------------------------------------------------------------------------------------
#-- TABLA TARIFA
#----------------------------------------------------------------------------------------------
#-- PROCEDIMIENTO que modifica TODAS las TARIFAS de golpe
#-- Se le pasará el valor por el que sumar o restar el porcentaje (positivo o negativo) 
#----------------------------------------------------------------------------------------------
drop procedure if exists alter_all_tarifas$$
create procedure alter_all_tarifas(in p_valor decimal(5,2))
begin
    if p_valor != 0 then 
        update tarifa set porcentaje = (1 + p_valor);
    else
        signal sqlstate '45000' set message_text = 'Ingresa un valor diferente a 0.';
    end if;
end

delimiter ;
#-----------------------------------------------------------------------------------------------
#-- SCRIPT para INSERTAR CONTENIDO de muestra
#-- Se han insertado valoraciones_medias genéricas, se recalcularán al hacer una votación nueva 
#-----------------------------------------------------------------------------------------------
-- Inserción de una serie
insert into contenido(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path)
values
-- id
(1001,
-- titulo 
 'Breaking Bad',
 -- descripcion 
 'Tras cumplir 50 años, Walter White (Bryan Cranston), un profesor de química de un instituto de Albuquerque, Nuevo México, se entera de que tiene un cáncer de pulmón incurable. Casado con Skyler (Anna Gunn) y con un hijo discapacitado (RJ Mitte), la brutal noticia lo impulsa a dar un drástico cambio a su vida: decide, con la ayuda de un antiguo alumno (Aaron Paul), fabricar anfetaminas y ponerlas a la venta. Lo que pretende es liberar a su familia de problemas económicos cuando se produzca el fatal desenlace.', 
 -- genero
 'Drama', 
 -- nombre_dir
 'Vince Gilligan',
 -- duracion generica 
 45, 
 -- actores principales
 'Bryan Cranston, Aaron Paul',
 -- fecha estreno 
 '2008-09-15', 
 -- puntuacion media
 9.0, 
 -- poster path
 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fpicfiles.alphacoders.com%2F422%2Fthumb-1920-422251.jpg&f=1&nofb=1&ipt=8fb2389bbe6fbacb1da3511e95cc76d698d836cf8499113bb2d5b1e16f2cf662'
);

insert into serie(contenido_id, tarifa_id, disponibilidad) 
values 
(1001, 2, true);

insert into temporada (serie_id, numero)
values (1001, 1);

insert into capitulo (temporada_id, titulo, precio_base)
values
(1, 'Piloto', 2.50),
(1, 'El gato está en la bolsa', 2.50),
(1, 'Y el saco en el río', 2.50),
(1, 'Cancer Man', 2.50),
(1, 'Gray Matter', 2.50),
(1, 'Crazy Handful of Nothin', 2.50),
(1, 'A No-Rough-Stuff-Type Deal', 2.50);




-- Inserción de un corto
insert into contenido(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path)
values
(2003,
 'Paperman',
 'Un corto animado romántico de Disney que combina animación tradicional y digital.',
 'Animación',
 'John Kahrs',
 7,
 'Shane Prigmore, Kari Wahlgren',
 '2012-11-02',
 9.0,
 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fi2.wp.com%2Fwww.heyuguys.com%2Fimages%2F2012%2F07%2FPaperman-poster.jpg&f=1&nofb=1&ipt=272ca1f030c5f227c3357317c56ad4601b8f86d29af6cc41281db6b909a9490f'
);

insert into corto(contenido_id, tarifa_id, precio_base)
values
(2003, 3, 4.00);


-- Inserción de varias películas
insert into contenido
    (id, 
    titulo, 
    descripcion, 
    genero, 
    nombre_dir, 
    duracion, 
    actores_principales, 
    fecha_estreno, 
    puntuacion_media, 
    poster_path)
values
    -- id
    (3001,
    -- titulo 
    'Cadena Perpetua',
    -- descripcion 
    'The Shawshank Redemption es una película dramática escrita y dirigida por el estadounidense Frank Darabont, y producida por los estudios Castle Rock Entertainment, cuyo estreno se produjo en 1994. Su trama está basada en Rita Hayworth y la redención de Shawshank, una novela corta de Stephen King, y abarca una mirada optimista de la vida relatando la historia del banquero Andy, quien es condenado a cadena perpetua en la Penitenciaría Estatal de Shawshank, a pesar de haberse declarado inocente.', 
    -- genero
    'Drama', 
    -- nombre_dir
    'Miguel Ángel',
    -- duracion (en minutos) 
    110, 
    -- actores principales
    'Tim Robbins, Morgan Freeman',
    -- fecha estreno
     '1994-09-05', 
    -- puntuacion media generica
     9.0, 
    -- poster path
     'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.LpafPZQdQKzgnkBaHBbOJwHaJ6%26pid%3DApi&f=1&ipt=17b9db332fe62c58fe907ed9b5ca2a4e2f59781f89fa111f350d4ff234f97b1e&ipo=images'),
    
    
    -- id
    (3002,
    -- titulo  
    'Shutter Island', 
    -- descripcion
    'En el verano de 1954, los agentes judiciales Teddy Daniels (DiCaprio) y Chuck Aule (Ruffalo) son destinados a una remota isla del puerto de Boston para investigar la desaparición de una peligrosa asesina (Mortimer) que estaba recluida en el hospital psiquiátrico Ashecliffe, un centro penitenciario para criminales perturbados dirigido por el siniestro doctor John Cawley (Kingsley). Pronto descubrirán que el centro guarda muchos secretos y que la isla esconde algo más peligroso que los pacientes. Thriller psicológico basado en la novela homónima de Dennis Lehane (autor de "Mystic River" y "Gone Baby Gone"). (FILMAFFINITY)', 
    -- genero
    'Thriller', 
    -- nombre_dir
    'Sonia López',
    -- duracion (en minutos)  
    105, 
    -- actores principales
    'Carlos Ruiz, Marta Díaz', 
    -- fecha estreno
    '2023-08-01', 
    -- puntuacion media generica
    9.0, 
    -- poster path
    'https://pics.filmaffinity.com/shutter_island-982808260-large.jpg'),
    
    -- id
    (3003,
    -- titulo 
    'El viaje de Chihiro',
    -- descripcion 
    'Chihiro es una niña de diez años que viaja en coche con sus padres. Después de atravesar un túnel, llegan a un mundo fantástico, en el que no hay lugar para los seres humanos, sólo para los dioses de primera y segunda clase. Cuando descubre que sus padres han sido convertidos en cerdos, Chihiro se siente muy sola y asustada. (FILMAFFINITY)', 
    -- genero
    'Animación',
    -- nombre_dir 
    'Pablo Gómez',
    -- duracion 
    90, 
    -- actores principales
    'Voces: Ana Martínez, Luis Gómez',
    -- fecha estreno 
    '2023-09-01', 
    -- puntuacion media
    9.0, 
    -- poster path
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette.wikia.nocookie.net%2Fdoblaje%2Fimages%2Ff%2Ffc%2FEL_VIAJE_DE_CHIHIRO.jpg%2Frevision%2Flatest%3Fcb%3D20170506234618%26path-prefix%3Des&f=1&nofb=1&ipt=35584f7d9b75353a9be8a57427215252259a31cb90e1669b0828ef0b922668c5');


insert into pelicula(contenido_id, tarifa_id, disponible_hasta, precio_base) values
(3001, 1, '2025-12-31', 5.00),
(3002, 1, '2025-12-31', 4.50),
(3003, 1, '2025-12-31', 6.00);
