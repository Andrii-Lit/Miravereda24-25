<?php
session_start();

include('connection.php');

$con = connection();

$sql = "SELECT * FROM users";
$query = mysqli_query($con, $sql)

?>

<DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Miravereda</title>
    </head>
    <body>
        <div>
            <form action="insert_user.php" method="POST"    >
                <h1>Creación de usuarios</h1>

                <input type="test" name="name" placeholder="Nombre">
                <input type="test" name="name" placeholder="Apellidos">
                <input type="test" name="name" placeholder="Domicilio">
                <input type="test" name="name" placeholder="Código Postal">
                <input type="test" name="name" placeholder="Email">
                <input type="day" name="name" placeholder="Fecha Nacimiento">
                <input type="test" name="name" placeholder="Número de tarjeta">

                <input type="submit" value="Añadir usuario">

            </form>
        <div>


        <div class="user_crud">
            <h2>Crud de usuarios</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NOMBRE</th>
                        <th>APELLIDOS</th>
                        <th>CONTRASEÑA</th>
                        <th>DOMICILIO</th>
                        <th>CÓDIGO POSTAL</th>
                        <th>EMAIl</th>
                        <th>FECHA NACIMIENTO</th>
                        <th>TARJETA</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <?php while($row = mysqli_fetch_array($query)): ?>
                    <tr>
                        <th> <?- $row['id'] ?> </th>
                        <th> <?- $row['name'] ?> </th>
                        <th> <?- $row['lastname'] ?> </th>
                        <th> <?- $row['password'] ?> </th>
                        <th> <?- $row['domicile'] ?> </th>
                        <th> <?- $row['postal_code'] ?> </th>
                        <th> <?- $row['email'] ?> </th>
                        <th> <?- $row['date_of_birth'] ?> </th>
                        <th> <?- $row['creditcard'] ?> </th>

                        <th><a href="">Editar</th>
                        <th><a href="">Eliminar</th>
                    </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        </div>
    </body>
</html>
