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

            //$password = md5($password);

            $sql = "SELECT * FROM users WHERE name = '$usuario' AND password = '$password'";
            $result = mysqli_query($con, $sql);

            if(mysqli_num_rows($result) === 1){
                $row = mysqli_fetch_assoc($result);
                if($row['name'] === $usuario && $row['password'] === $password){
                    $_SESSION['name'] = $row['name'];
                    $_SESSION['id'] = $row['id'];
                    $_SESSION['lastname'] = $row['lastname'];   
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
