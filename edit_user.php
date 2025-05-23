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

$sql = "UPDATE USERS SET name='$name',lastname='$lastname',password='$password',domicile='$domicile',postal_code='$postal_code', email='$email', date_of_birth='$date_of_birth',creditcard='$creditcard' WHERE id = '$id'";
$query = mysqli_query($con, $sql);

if($query){
    Header("Location: crudUsu.php");
};

?>