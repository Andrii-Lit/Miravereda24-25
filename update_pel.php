<?php 
include('connection.php');
$con = connection();

$id = $_GET['id'];

$sql = "SELECT * FROM contenido WHERE id='$id'";
$query = mysqli_query($con, $sql);
$row = mysqli_fetch_array($query);
?> 

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MIRAVEREDA</title>
    </head>
    <body>
        <div>
            <form action="edit_pel.php" method="POST">
                <h1>Editar Contenido</h1>

                <input type="hidden" name="id" value="<?= $row ['id'] ?>">
                <input type="text" name="poster" value="<?= $row ['poster_path'] ?>">
                <input type="text" name="titulo" value="<?= $row ['titulo'] ?>">    
                <input type="text" name="genero" value="<?= $row ['genero'] ?>">
                <input type="text" name="nombre_dir" value="<?= $row ['nombre_dir'] ?>">
                <input type="text" name="duracion" value="<?= $row ['duracion'] ?>">
                <input type="text" name="actores_principales" value="<?= $row ['actores_principales'] ?>">
                <input type="date" name="fecha_estreno" value="<?= $row ['fecha_estreno'] ?>">
                <input type="text" name="puntuacion_media" value="<?= $row ['puntuacion_media'] ?>">
                <input type="text" name="descripcion" value="<?= $row ['descripcion'] ?>">

                <input type="submit" value="Actualizar contenido">

            </form>
        </div>
    </body>
</html>