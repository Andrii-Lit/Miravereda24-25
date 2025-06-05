<?php
include('auth.php'); 
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
$descripcion = $_POST['descripcion'];
$tipo = $_POST['tipo'];
$precio = $_POST['precio'];

$sql = "UPDATE contenido SET poster_path='$poster',titulo='$titulo',genero='$genero',nombre_dir='$director',duracion='$duracion', actores_principales='$actores', fecha_estreno='$estreno',puntuacion_media='$media',descripcion='$descripcion',tipo='$tipo',precio='$precio' WHERE id = '$id'";
$query = mysqli_query($con, $sql);

if($query){
    Header("Location: crudPelTest.php");
};

?>