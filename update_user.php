<?php 
include('connection.php');
$con = connection();

$id = $_GET['id'];

$sql = "SELECT * FROM cliente WHERE id='$id'";
$query = mysqli_query($con, $sql);
$row = mysqli_fetch_array($query);
?> 

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MIRAVEREDA</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div>
            <form action="edit_user.php" method="POST">
                <h1>Editar usuario</h1>

                <input type="hidden" name="id" value="<?= $row ['id'] ?>">
                <input type="text" name="name" value="<?= $row ['nombre'] ?>">
                <input type="text" name="lastname" value="<?= $row ['apellidos'] ?>">
                <input type="text" name="password" value="<?= $row ['contrasenya'] ?>">
                <input type="text" name="domicile" value="<?= $row ['domicilio'] ?>">
                <input type="text" name="postal_code" value="<?= $row ['cod_postal'] ?>">
                <input type="text" name="email" value="<?= $row ['email'] ?>">
                <input type="date" name="date_of_birth" value="<?= $row ['fecha_nac']?>">
                <input type="text" name="creditcard" value="<?= $row ['num_tarjeta'] ?>">

                <input type="submit" value="Actualizar usuario">

            </form>
        </div>
    </body>
</html>