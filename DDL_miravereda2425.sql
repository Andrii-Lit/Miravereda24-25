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
    email varchar(100) unique,----------
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
 puntuacion_media decimal(3,1),
 poster_path varchar(255),
 changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

#-----------
#-- TARIFA
#-----------
create table tarifa(
    id int primary key,
    tipo varchar(50),
    porcentaje decimal(5,4),
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
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
    foreign key (tarifa_id) references tarifa(id)
);

#----------
#-- SERIE
#----------
create table serie(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
    foreign key (tarifa_id) references tarifa(id)
);

#----------
#-- CORTO
#----------
create table corto(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
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
    foreign key (serie_id) references serie(contenido_id),
    unique (serie_id, numero)
);

#-------------
#-- CAPITULO
#-------------
create table capitulo(
    id int auto_increment primary key,
    temporada_id int,
    titulo varchar(255),
    precio_base decimal(10,2),
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (temporada_id) references temporada(id)
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
    foreign key (contenido_id) references contenido(id)

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
('concha505', 'Concha', 'Jiménez', 'Desengaño 21, 2ºB', '28004', 'concha.jimenez@miravereda.es', '1938-01-27', '8901234589012345');

#-----------------------------------------------
#-- INSERTAMOS ALGUNOS CONTENIDOS AUDIOVISUALES
#-----------------------------------------------

#-----------------------------------------------
#-- PELICULA
#-----------------------------------------------
-- Contenido base
insert into contenido (id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path)
values (1, 'Inception', 'Un ladrón que roba secretos corporativos a través del uso de la tecnología de sueños compartidos.', 'Ciencia Ficción', 'Christopher Nolan', 148, 'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page', '2010-07-16', 8.8, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fflxt.tmsimg.com%2Fassets%2Fp7825626_p_v8_af.jpg&f=1&nofb=1&ipt=43b10321849c53c4bf641c883af9e87240c3a714164beb0b0845580bc31549fc');

-- Película asociada
insert into pelicula (contenido_id, tarifa_id, disponible_hasta, precio_base)
values (1, 1, '2025-12-31', 12.99);

#-----------------------------------------------
#-- SERIE
#-----------------------------------------------
-- Contenido base
insert into contenido (id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path)
values (2, 'Chernobyl', 'Miniserie sobre el desastre nuclear de Chernóbil en 1986.', 'Drama histórico', 'Johan Renck', 330, 'Jared Harris, Stellan Skarsgård, Emily Watson', '2019-05-06', 9.4, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.themoviedb.org%2Ft%2Fp%2Foriginal%2F4cPTQbF2GL59j2YGeUaBg3yvzJw.jpg&f=1&nofb=1&ipt=6c3ac1761bf53d9f25cc472e1f9aa7449f7d78ddfa9bd9dde942c3afde839238');

-- Serie asociada
insert into serie (contenido_id, tarifa_id, disponibilidad, precio_base)
values (2, 2, true, 19.99);

-- Temporada
insert into temporada (serie_id, numero)
values (2, 1);

-- Capítulos (ejemplo con 5)
insert into capitulo (temporada_id, titulo, precio_base, precio_base)
values
(1, '1.1 - 1:23:45', 3.00, 3.30),
(1, '1.2 - Please Remain Calm', 3.00, 3.30),
(1, '1.3 - Open Wide, O Earth', 3.00, 3.30),
(1, '1.4 - The Happiness of All Mankind', 3.00, 3.30),
(1, '1.5 - Vichnaya Pamyat', 3.00, 3.30);

#-----------------------------------------------
#-- CORTO
#-----------------------------------------------
-- Contenido base
insert into contenido (id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path)
values (3, 'Piper', 'Un pequeño pájaro de la orilla del mar aprende a encontrar su alimento en las olas.', 'Animación', 'Alan Barillaro', 6, '—', '2016-06-17', 8.4, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fflxt.tmsimg.com%2Fassets%2Fp12993868_p_v10_aa.jpg&f=1&nofb=1&ipt=1044bca340e9034079917c1f9ef5279dfa49ecd5bad317c2335c731fa69f37fd');

-- Corto asociado
insert into corto (contenido_id, tarifa_id, precio_base)
values (3, 3, 1.99);
