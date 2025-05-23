<?php
include('connection.php');
$con = connection();

$id = null
$name = $_POST['name'];
$lastname = $_POST['lastname'];
$password = $_POST['password'];
$domicile = $_POST['domicile'];
$postal_code = $_POST['postal_code'];
$email = $_POST['email'];
$date_of_birth = $_POST['date_of_birth'];
$creditcard = $_POST['creditcard'];

$sql = "INSERT INTO users VALUES ('$id','$name','$lastname','$password','$domicile','$postal_code','$email','$date_of_birth','$creditcard')";
$query = mysqli_query($con, $sql);

if($query){
    Header("Location: crudUsu.php");
};

?>
