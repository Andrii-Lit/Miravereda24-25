<?php
include('connection.php');
$con = connection();

$id = $_GET['id'];

$sql = "DELETE FROM contenido WHERE id='$id'";

$query = mysqli_query($con, $sql);

if($query){
    Header("Location: crudPel.php");
};
?>
