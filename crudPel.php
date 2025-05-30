<?php
session_start();

    include('connection.php');
    $con = connection();

$sql = "SELECT * FROM contenido";
$query = mysqli_query($con, $sql);

?>

<DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Miravereda</title>
    </head>
    <body>
        <div>
            <form action="insert_pel.php" method="POST">
                <h1>Añadir contenido</h1>

                <input type="text" name="id" placeholder="ID del contenido">
                <input type="text" name="poster" placeholder="URL del poster">
                <input type="text" name="titulo" placeholder="Título">
                <input type="text" name="genero" placeholder="Genero">
                <input type="text" name="nombre_dir" placeholder="Director">
                <input type="text" name="duracion" placeholder="Duración">
                <input type="text" name="actores_principales" placeholder="Actores Principales">
                <input type="date" name="fecha_estreno" placeholder="Estreno">
                <input type="text" name="puntuacion_media" placeholder="Puntuación media">
                <input type="text" name="descripcion" placeholder="Ligera descripción">

                <input type="submit" value="Añadir contenido">

            </form>
        <div>
        <div class="pel_crud">
            <h2>Crud de Contenido</h2>
            <h3>MIRAVEREDA</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>POSTER</th>
                        <th>TÍTULO</th>
                        <th>GENERO</th>
                        <th>DIRECTOR</th>
                        <th>DURACIÓN</th>
                        <th>ACTORES PRINCIPALES</th>
                        <th>ESTRENO</th>
                        <th>PUNTUACIÓN MEDIA</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>     
                    <?php while($row = mysqli_fetch_array($query)): ?>
                        <tr>
                            <th> <?= $row['id'] ?> </th>
                            <th> <img src="<?= $row['poster_path'] ?>"></th>
                            <th> <?= $row['titulo'] ?> </th>
                            <th> <?= $row['genero'] ?> </th>
                            <th> <?= $row['nombre_dir'] ?> </th>
                            <th> <?= $row['duracion'] ?> </th>
                            <th> <?= $row['actores_principales'] ?> </th>
                            <th> <?= $row['fecha_estreno'] ?> </th>
                            <th> <?= $row['puntuacion_media'] ?> </th>

                            <th><a href="update_pel.php?id=<?=$row['id']?>">Editar</th>
                            <th><a href="delete_pel.php?id=<?=$row['id']?>">Eliminar</th>
                        </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        </div>
    </body>
</html>
