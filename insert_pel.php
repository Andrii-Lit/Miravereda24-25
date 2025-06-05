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
$desc = $_POST['descripcion'];
$tipo = $_POST['tipo'];
$precio = $_POST['precio'];

$check_sql = "SELECT * FROM contenido WHERE id = '$id'";
$check_query = mysqli_query($con,$check_sql);

if(mysqli_num_rows($check_query) > 0) {
    //Verificación de id existente
    echo "<script>
            alert('El ID ya está registrado. No se puede añadir el contenido. ');
            window.location.href = 'crudPelTest.php';
          </script>";
    exit();
} else {
    //Insertar contenido
    $sql = "INSERT INTO contenido (poster_path,titulo,genero,nombre_dir,duracion,actores_principales,fecha_estreno,puntuacion_media,descripcion,id,tipo,precio) VALUES ('$poster','$titulo','$genero','$director','$duracion','$actores','$estreno','$media','$desc','$id','$tipo','$precio')";
    $query = mysqli_query($con, $sql);

    if($query){
    Header("Location: crudPelTest.php");
    } else {
        echo "Error al insertar película: " . mysqli_error($con);
    }
}
?>
