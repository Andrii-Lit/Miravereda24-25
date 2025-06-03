<?php 
include('connection.php');
$con = connection();

$id = $_POST['id'];
$name = $_POST['name'];
$lastname = $_POST['lastname'];
$password = $_POST['password'];
$domicile = $_POST['domicile'];
$postal_code = $_POST['postal_code'];
$email = $_POST['email'];
$date_of_birth = $_POST['date_of_birth'];
$creditcard = $_POST['creditcard'];

$sql = "UPDATE cliente SET nombre='$name',apellidos='$lastname',contrasenya='$password',domicilio='$domicile',cod_postal='$postal_code', email='$email', fecha_nac='$date_of_birth',num_tarjeta='$creditcard' WHERE id = '$id'";
$query = mysqli_query($con, $sql);

if($query){
    Header("Location: crudUsuTest.php");
};

?>