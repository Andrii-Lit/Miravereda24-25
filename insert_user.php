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




// Verificar si el email ya existe
$check_sql = "SELECT * FROM cliente WHERE email = '$email'";
$check_query = mysqli_query($con, $check_sql);

if (mysqli_num_rows($check_query) > 0) {
    // El email ya existe
    echo "<script>
            alert('El correo ya está registrado.');
            window.location.href = 'crudUsuTest.php';
          </script>";
    exit();
} else {
    // Insertar el usuario
    $sql = "INSERT INTO cliente (nombre, apellidos, domicilio, cod_postal, email, fecha_nac, num_tarjeta, contrasenya)
            VALUES ('$name', '$lastname', '$domicile', '$postal_code', '$email', '$date_of_birth', '$creditcard', '$password')";
    $query = mysqli_query($con, $sql);

    if ($query) {
        header("Location: crudUsuTest.php");
        exit();
    } else {
        echo "Error al insertar usuario: " . mysqli_error($con);
    }
}
?>
