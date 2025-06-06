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
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette.wikia.nocookie.net%2Fdoblaje%2Fimages%2Ff%2Ffc%2FEL_VIAJE_DE_CHIHIRO.jpg%2Frevision%2Flatest%3Fcb%3D20170506234618%26path-prefix%3Des&f=1&nofb=1&ipt=35584f7d9b75353a9be8a57427215252259a31cb90e1669b0828ef0b922668c5'),
    
    
    -- id
    (1,
    -- titulo 
    'El Padrino',
    -- descripcion 
    'América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York. Tiene cuatro hijos: Connie (Talia Shire), el impulsivo Sonny (James Caan), el pusilánime Fredo (John Cazale) y Michael (Al Pacino), que no quiere saber nada de los negocios de su padre. Cuando Corleone, en contra de los consejos de Il consigliere Tom Hagen (Robert Duvall), se niega a participar en el negocio de las drogas, el jefe de otra banda ordena su asesinato. Empieza entonces una violenta y cruenta guerra entre las familias mafiosas.',
    -- genero
    'Drama / Crimen',
    -- nombre_dir
    'Francis Ford Coppola',
    -- duracion
    175,
    -- actores principales
    'Marlon Brando, Al Pacino, James Caan',
    -- fecha estreno
    '1972-03-24',
    -- puntuacion media
    9.3,
    -- poster path
    'https://pics.filmaffinity.com/El_padrino-993414333-large.jpg'),

    (2,
    'Forrest Gump',
    'Forrest Gump (Tom Hanks) sufre desde pequeño un cierto retraso mental. A pesar de todo, gracias a su tenacidad y a su buen corazón será protagonista de acontecimientos cruciales de su país durante varias décadas. Mientras pasan por su vida multitud de cosas en su mente siempre está presente la bella Jenny (Robin Wright), su gran amor desde la infancia, que junto a su madre será la persona más importante en su vida.',
    'Drama / Romance',
    'Robert Zemeckis',
    142,
    'Tom Hanks, Robin Wright, Gary Sinise',
    '1994-07-06',
    8.8,
    'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fpics.filmaffinity.com%2FForrest_Gump-212765827-large.jpg&f=1&nofb=1&ipt=555d631609a0b30ce2ca30977ef98e9d8fb987efc8cd4c39d6cfb4aba31129e1'),

    (3,
    'Inception',
    'Dom Cobb (DiCaprio) es un experto en el arte de apropiarse, durante el sueño, de los secretos del subconsciente ajeno. La extraña habilidad de Cobb le ha convertido en un hombre muy cotizado en el mundo del espionaje, pero también lo ha condenado a ser un fugitivo y, por consiguiente, a renunciar a llevar una vida normal. Su única oportunidad para cambiar de vida será hacer exactamente lo contrario de lo que ha hecho siempre: la incepción, que consiste en implantar una idea en el subconsciente en lugar de sustraerla.Sin embargo, su plan se complica debido a la intervención de alguien que parece predecir cada uno de sus movimientos, alguien a quien sólo Cobb podrá descubrir.',
    'Ciencia ficción / Thriller',
    'Christopher Nolan',
    148,
    'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page',
    '2010-07-16',
    8.7,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fflxt.tmsimg.com%2Fassets%2Fp7825626_p_v8_af.jpg&f=1&nofb=1&ipt=fece9982d47cd73c45d51e9ca363001c895c77ce7ee9d731e012133672df68b7'),

    (4,
    'El Señor de los Anillos: La Comunidad del Anillo',
    'En la Tierra Media, el Señor Oscuro Sauron ordenó a los Elfos que forjaran los Grandes Anillos de Poder. Tres para los reyes Elfos, siete para los Señores Enanos, y nueve para los Hombres Mortales. Pero Saurón también forjó, en secreto, el Anillo Único, que tiene el poder de esclavizar toda la Tierra Media. Con la ayuda de sus amigos y de valientes aliados, el joven hobbit Frodo emprende un peligroso viaje con la misión de destruir el Anillo Único. Pero el malvado Sauron ordena la persecución del grupo, compuesto por Frodo y sus leales amigos hobbits, un mago, un hombre, un elfo y un enano. La misión es casi suicida pero necesaria, pues si Sauron con su ejército de orcos lograra recuperar el Anillo, sería el final de la Tierra Media.',
    'Fantasía / Aventura',
    'Peter Jackson',
    178,
    'Elijah Wood, Ian McKellen, Orlando Bloom',
    '2001-12-19',
    8.8,
    'https://m.media-amazon.com/images/I/51Qvs9i5a%2BL._AC_.jpg'),

    (5,
    'Pulp Fiction',
    'Jules y Vincent, dos asesinos a sueldo con no demasiadas luces, trabajan para el gángster Marsellus Wallace. Vincent le confiesa a Jules que Marsellus le ha pedido que cuide de Mia, su atractiva mujer. Jules le recomienda prudencia porque es muy peligroso sobrepasarse con la novia del jefe. Cuando llega la hora de trabajar, ambos deben ponerse "manos a la obra". Su misión: recuperar un misterioso maletín.',
    'Crimen / Drama',
    'Quentin Tarantino',
    154,
    'John Travolta, Uma Thurman, Samuel L. Jackson',
    '1994-10-14',
    8.9,
    'https://m.media-amazon.com/images/I/71c05lTE03L._AC_SY679_.jpg'),

    (6,
    'La La Land',
    'Mia (Emma Stone), una joven aspirante a actriz que trabaja como camarera mientras acude a castings, y Sebastian (Ryan Gosling), un pianista de jazz que se gana la vida tocando en sórdidos tugurios, se enamoran, pero su gran ambición por llegar a la cima en sus carreras artísticas amenaza con separarlos.',
    'Musical / Romance',
    'Damien Chazelle',
    128,
    'Ryan Gosling, Emma Stone',
    '2016-12-09',
    8.0,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimage.tmdb.org%2Ft%2Fp%2Foriginal%2FlcYwCSTzCHv0Z2QRpfztt75JyYj.jpg&f=1&nofb=1&ipt=9fab3de2701902be0b8801a67bd18774a262b70ca66b2142bc62200dd73c3e7b'),

    (7,
    'Gladiador',
    'En el año 180, el Imperio Romano domina todo el mundo conocido. Tras una gran victoria sobre los bárbaros del norte, el anciano emperador Marco Aurelio (Richard Harris) decide transferir el poder a Máximo (Russell Crowe), bravo general de sus ejércitos y hombre de inquebrantable lealtad al imperio. Pero su hijo Cómodo (Joaquin Phoenix), que aspiraba al trono, no lo acepta y trata de asesinar a Máximo.',
    'Acción / Drama',
    'Ridley Scott',
    155,
    'Russell Crowe, Joaquin Phoenix',
    '2000-05-05',
    8.5,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F1.bp.blogspot.com%2F-akwiv2qJ-9o%2FXupQNCHDiGI%2FAAAAAAAADLE%2FBQsBxNrwrl8MSvlpAgDuI4TlgAs1UKJ-gCLcBGAsYHQ%2Fs1600%2Fgladiator-522d2bd7f14a4.jpg&f=1&nofb=1&ipt=8214f092b4f2cd80b02a7a4c97b7b5186f1'),

    (8,
    'Matrix',
    'Thomas Anderson es un brillante programador de una respetable compañía de software. Pero fuera del trabajo es Neo, un hacker que un día recibe una misteriosa visita...',
    'Ciencia ficción / Acción',
    'Lana Wachowski, Lilly Wachowski',
    136,
    'Keanu Reeves, Laurence Fishburne',
    '1999-03-31',
    8.7,
    'https://m.media-amazon.com/images/I/51vpnbwFHrL._AC_.jpg'),

    (9,
    'Titanic',
    'Jack (DiCaprio), un joven artista, gana en una partida de cartas un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido. A bordo conoce a Rose (Kate Winslet), una joven de una buena familia venida a menos que va a contraer un matrimonio de conveniencia con Cal (Billy Zane), un millonario engreído a quien sólo interesa el prestigioso apellido de su prometida. Jack y Rose se enamoran, pero el prometido y la madre de ella ponen todo tipo de trabas a su relación. Mientras, el gigantesco y lujoso transatlántico se aproxima hacia un inmenso iceberg.',
    'Drama / Romance',
    'James Cameron',
    195,
    'Leonardo DiCaprio, Kate Winslet',
    '1997-12-19',
    7.8,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Foriginalvintagemovieposters.com%2Fwp-content%2Fuploads%2F2020%2F02%2FTITANIC-8567-scaled.jpg&f=1&nofb=1&ipt=7ad0fe22c4e727d7623e54745d3e1642f9550db05639ee39e3caebef4ff5c73d'),

    (10,
    'El Club de la lucha',
    'Un joven hastiado de su gris y monótona vida lucha contra el insomnio. En un viaje en avión conoce a un carismático vendedor de jabón que sostiene una teoría muy particular: el perfeccionismo es cosa de gentes débiles; sólo la autodestrucción hace que la vida merezca la pena. Ambos deciden entonces fundar un club secreto de lucha, donde poder descargar sus frustaciones y su ira, que tendrá un éxito arrollador.',
    'Drama / Thriller',
    'David Fincher',
    139,
    'Brad Pitt, Edward Norton',
    '1999-10-15',
    8.8,
    'https://m.media-amazon.com/images/I/51v5ZpFyaFL._AC_SY679_.jpg'),

    (11,
    'Interstellar',
    'Al ver que la vida en la Tierra está llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper (McConaughey) y la científica Amelia (Hathaway) emprende una misión que puede ser la más importante de la historia de la humanidad: viajar más allá de nuestra galaxia para descubrir algún planeta en otra que pueda garantizar el futuro de la raza humana.',
    'Ciencia ficción / Drama',
    'Christopher Nolan',
    169,
    'Matthew McConaughey, Anne Hathaway',
    '2014-11-07',
    8.6,
    'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Ffc04.deviantart.net%2Ffs71%2Ff%2F2014%2F045%2Fa%2F2%2Finterstellar_by_visuasys-d6ibj30.jpg&f=1&nofb=1&ipt=40e450f22815f8d425ff00557720b273075838a00b1297914b8bc9ba64e51b6e'),

    (12,
    'El Gran Lebowski',
    'El Nota (Jeff Bridges), un holgazán que vive en Los Angeles, es confundido un día por un par de matones con el millonario Jeffrey Lebowski, con quien sólo comparte nombre y apellido. Después de que orinen en su alfombra, el Nota inicia la búsqueda de "El gran Lebowski". De su encuentro surgirá un trato: el Nota recibirá una recompensa si consigue encontrar a la mujer del magnate.',
    'Comedia / Crimen',
    'Joel Coen, Ethan Coen',
    117,
    'Jeff Bridges, John Goodman',
    '1998-03-06',
    8.1,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages.justwatch.com%2Fposter%2F76802182%2Fs718&f=1&nofb=1&ipt=a2d925e102bfa3d5ed63e66eff80c0fd6f40db0b5b872320d3a6993e61ff4346'),

    (13,
    'Toy Story',
    'Los juguetes de Andy, un niño de 6 años, temen que haya llegado su hora y que un nuevo regalo de cumpleaños les sustituya en el corazón de su dueño. Woody, un vaquero que ha sido hasta ahora el juguete favorito de Andy, trata de tranquilizarlos hasta que aparece Buzz Lightyear, un héroe espacial dotado de todo tipo de avances tecnológicos. Woody es relegado a un segundo plano. Su constante rivalidad se transformará en una gran amistad cuando ambos se pierden en la ciudad sin saber cómo volver a casa.',
    'Animación / Aventura',
    'John Lasseter',
    81,
    'Tom Hanks, Tim Allen',
    '1995-11-22',
    8.3,
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fpics.filmaffinity.com%2Ftoy_story-626273371-large.jpg&f=1&nofb=1&ipt=e125d41601314a3de0de0ca1c8af31e44893772cbebd0f93e2ed6e1a8efb3858');


insert into pelicula(contenido_id, tarifa_id, disponible_hasta, precio_base) values
(3001, 1, '2025-12-31', 5.00),
(3002, 1, '2025-12-31', 4.50),
(3003, 1, '2025-12-31', 6.00),
(1, 1, '2025-12-31', 5.00),
(2, 1, '2025-12-31', 4.50),
(3, 1, '2025-12-31', 6.00),
(4, 1, '2025-12-31', 5.50),
(5, 1, '2025-12-31', 5.00),
(6, 1, '2025-12-31', 5.00),
(7, 1, '2025-12-31', 5.00),
(8, 1, '2025-12-31', 5.00),
(9, 1, '2025-12-31', 5.50),
(10, 1, '2025-12-31', 5.00),
(11, 1, '2025-12-31', 6.00),
(12, 1, '2025-12-31', 4.50),
(13, 1, '2025-12-31', 4.50);
