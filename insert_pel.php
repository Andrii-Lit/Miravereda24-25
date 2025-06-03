<?php
include('connection.php');
$con = connection();

$id = $_POST['id'];
$poster = $_POST['poster'];
$titulo = $_POST['titulo'];
$genero = $_POST['genero'];
$director = $_POST['nombre_dir'];
$duracion = $_POST['duracion'];
$actores = $_POST['actores_principales'];
$estreno = $_POST['fecha_estreno'];
$media = $_POST['puntuacion_media'];
$desc = $_POST['descripcion'];
$tipo = $_POST['tipo'];
$precio = $_POST['precio'];

$sql = "INSERT INTO contenido (poster_path,titulo,genero,nombre_dir,duracion,actores_principales,fecha_estreno,puntuacion_media,descripcion,id,tipo,precio) VALUES ('$poster','$titulo','$genero','$director','$duracion','$actores','$estreno','$media','$desc','$id','$tipo','$precio')";
$query = mysqli_query($con, $sql);



if($query){
    Header("Location: crudPelTest.php");
};

?>
