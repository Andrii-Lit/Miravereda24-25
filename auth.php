<?php
if(session_status() == PHP_SESSION_NONE) {
    session_start();
}



if (!isset($_SESSION['email']) || $_SESSION['email'] !== 'root@miravereda.es') {
    header("Location: index.php?error=Acceso denegado");
    exit();
}
?>