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
 8.7, 
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
 8.2,
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
     8.1, 
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
    7.2, 
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
    7.9, 
    -- poster path
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette.wikia.nocookie.net%2Fdoblaje%2Fimages%2Ff%2Ffc%2FEL_VIAJE_DE_CHIHIRO.jpg%2Frevision%2Flatest%3Fcb%3D20170506234618%26path-prefix%3Des&f=1&nofb=1&ipt=35584f7d9b75353a9be8a57427215252259a31cb90e1669b0828ef0b922668c5');


insert into pelicula(contenido_id, tarifa_id, disponible_hasta, precio_base) values
(3001, 1, '2025-12-31', 5.00),
(3002, 1, '2025-12-31', 4.50),
(3003, 1, '2025-12-31', 6.00);
