<?php   
    session_start();
    include('connection.php');
    $con = connection();

    if(isset($_POST['usuario']) && isset($_POST['password'])){

        function validate($data){
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }
        
        $usuario = validate($_POST['usuario']);
        $password = validate($_POST['password']);

        if (empty($usuario)){
            header("Location: index.php?error=Campo usuario vacio.");
            exit();
        }elseif (empty($password)){
            header("Location: index.php?error=Campo contraseña vacio.");
            exit();
        }else{

        

            $sql = "SELECT * FROM cliente WHERE email = '$usuario' AND contrasenya = '$password'";
            $result = mysqli_query($con, $sql);

            if(mysqli_num_rows($result) === 1){
                $row = mysqli_fetch_assoc($result);
                if($row['email'] === $usuario && $row['contrasenya'] === $password){
                    $_SESSION['email'] = $row['email'];
                    $_SESSION['id'] = $row['id'];
                    $_SESSION['apellidos'] = $row['apellidos'];   
                    header("Location: inicio.php");
                    exit();
                }else{
                    header("Location: index.php?error=El usuario o la contraseña son incorrectos");
                    exit();
                }
            }else{
                header("Location: index.php?error=El usuario o la contraseña son incorrectos");
                exit();
            }
        }
    }else{
        header("Location: index.php");
        exit();
    }

?>
