-- #### SCRIPT PARA CREAR LA BASE DE DATOS MIRAVEREDA
-- by Cristóbal
-- Se han dejado campos sin NOT NULL para hacer pruebas
-- BASE DE DATOS
drop database if exists miravereda2425;
create database miravereda2425;
use miravereda2425;

-- tabla CLIENTE
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

-- #### CONTENIDO AUDIOVISUAL ####

-- tabla CONTENIDO
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

-- tabla TARIFA
create table tarifa(
    id int auto_increment primary key,
    tipo varchar(50),
    porcentaje decimal(5,4),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP

);

-- tabla VALORACION
create table valoracion (
    cliente_id int,
    contenido_id int,
    valor int,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    primary key (cliente_id, contenido_id),
    foreign key (cliente_id) references cliente(id) on delete cascade,
    foreign key (contenido_id) references contenido(id)

);

-- tabla PELICULA
create table pelicula(
    contenido_id int primary key,
    tarifa_id int,
    disponible_hasta date,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
    foreign key (tarifa_id) references tarifa(id)
);

-- tabla SERIE
create table serie(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
    foreign key (tarifa_id) references tarifa(id)
);

-- tabla CORTO
create table corto(
    contenido_id int primary key,
    tarifa_id int,
    disponibilidad boolean default true,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (contenido_id) references contenido(id),
    foreign key (tarifa_id) references tarifa(id)
);

-- tabla TEMPORADA
create table temporada(
    id int auto_increment primary key,
    serie_id int,
    numero int,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (serie_id) references serie(contenido_id),
    unique (serie_id, numero)
);

-- tabla CAPITULO
create table capitulo(
    id int auto_increment primary key,
    temporada_id int,
    titulo varchar(255),
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (temporada_id) references temporada(id)
);

-- #### COMERCIO ####
-- tabla CARRITO
create table carrito(
    id int auto_increment primary key,
    cliente_id int,
    total decimal(10,2) default 0.00,
    activo boolean default true,
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    foreign key (cliente_id) references cliente (id) on delete cascade
);

-- tabla LIN_FAC
create table lin_fac(
    carrito_id int,
    contenido_id int,
    precio decimal(10,2),
    changedTS timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    primary key(carrito_id, contenido_id),
    foreign key (carrito_id) references carrito(id) on delete cascade,
    foreign key (contenido_id) references contenido(id)

);

-- tabla FACTURA
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
