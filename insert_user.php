<?php
include('connection.php');
$con = connection();

$name = $_POST['name'];
$lastname = $_POST['lastname'];
$password = $_POST['password'];
$domicile = $_POST['domicile'];
$postal_code = $_POST['postal_code'];
$email = $_POST['email'];
$date_of_birth = $_POST['date_of_birth'];
$creditcard = $_POST['creditcard'];

$sql = "INSERT INTO cliente (nombre,apellidos,domicilio,cod_postal,email,fecha_nac,num_tarjeta,contrasenya) VALUES ('$name','$lastname','$domicile','$postal_code','$email','$date_of_birth','$creditcard','$password')";
$query = mysqli_query($con, $sql);



if($query){
    Header("Location: crudUsu.php");
};

?>
