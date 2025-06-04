<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>CrudUsu</title>
</head>
<body>
    <a href="inicio.php" class="btn-back">Volver atrás</a>
    <div>
        <form action="insert_user.php" method="POST">
            <h1>Creación de usuarios</h1>

            

            <input type="text" name="name" placeholder="Nombre">
            <input type="text" name="lastname" placeholder="Apellidos">
            <input type="text" name="password" placeholder="Contraseña">
            <input type="text" name="domicile" placeholder="Domicilio">
            <input type="text" name="postal_code" placeholder="Código Postal">
            <input type="text" name="email" placeholder="Email">
            <input type="date" name="date_of_birth" placeholder="Fecha Nacimiento">
            <input type="text" name="creditcard" placeholder="Número de tarjeta">
            <input type="submit" value="Añadir usuario">

        </form>
    <div>
    <h2>CLIENTES</h2>
    <form action="" method="post">
        <label for="campo"> Buscador: </label>
        <input type="text" name="campo" id="campo">

        <p></p>

        <table>
            <thead>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>APELLIDOS</th>
                <th>CONTRASEÑA</th>   
                <th>DOMICILIO</th>
                <th>CÓDIGO POSTAL</th>
                <th>EMAIL</th>
                <th>FECHA NACIMIENTO</th>
                <th>TARJETA</th>
                <th></th>
                <th></th>
            </thead>
            <tbody id="content">

            </tbody>
        </table>

        <script>
            getData()

            document.getElementById("campo").addEventListener("keyup", getData)

            function getData(){
                let input = document.getElementById("campo").value
                let content = document.getElementById("content")
                let url = "loadTest.php";
                let formaData = new FormData()
                formaData.append('campo', input)

                fetch(url, { 
                    method: "POST",
                    body: formaData
                }).then(response => response.json())
                .then(data => {
                    content.innerHTML = data
                }).catch(err => console.log(err))
            }

        </script>

    </form>
</body>
</html>
