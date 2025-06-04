<!DOCTYPE html> 
<html>
	<head>
		<meta charset = "utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>MIRAVEREDA</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>Miravereda</h1>
        
        <div class="container">
            <form action="iniciarSesion.php" method="POST">
                <h2>Inicio de sesión</h2>
				<hr>
				<?php
					if(isset($_GET['error'])){
						?>
						<p>
							<?php
								echo $_GET['error'];
							?>
						</p>
						<?php
					}
				?>
				<hr>
                <label>Usuario</label>
                <input type="text" name="usuario" placeholder="Nombre de Usuario">
                <label>Contraseña</label>
                <input type="password" name="password" placeholder="Contraseña">
                <button type="submit">Iniciar sesión</button>
            </form>
        </div>
	</body>
</html>
