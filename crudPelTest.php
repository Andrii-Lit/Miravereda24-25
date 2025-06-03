<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>CrudUsu</title>
</head>
<body>
        <div>
            <form action="insert_pel.php" method="POST">
                <h1>Añadir contenido</h1>

                <input type="text" name="id" placeholder="ID del contenido">
                <input type="text" name="poster" placeholder="URL del poster">
                <input type="text" name="titulo" placeholder="Título">
                <input type="text" name="genero" placeholder="Genero">
                <input type="text" name="nombre_dir" placeholder="Director">
                <input type="text" name="duracion" placeholder="Duración">
                <input type="text" name="actores_principales" placeholder="Actores Principales">
                <input type="date" name="fecha_estreno" placeholder="Estreno">
                <input type="text" name="puntuacion_media" placeholder="Puntuación media">
                <input type="text" name="descripcion" placeholder="Ligera descripción">

                <input type="submit" value="Añadir contenido">

            </form>
        <div>
    <h2>CONTENIDO</h2>
    <form action="" method="post">
        <label for="campo"> Buscador: </label>
        <input type="text" name="campo" id="campo">

        <p></p>

        <table>
            <thead>
                <th>ID</th>
                <th>POSTER</th>
                <th>TÍTULO</th>
                <th>GENERO</th>   
                <th>DIRECTOR</th>
                <th>DURACIÓN</th>
                <th>ACTORES PRINCIPALES</th>
                <th>ESTRENO</th>
                <th>PUNTUACIÓN MEDIA</th>
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
                let url = "loadPel.php";
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