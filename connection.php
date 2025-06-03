<?php

    function connection(){
        $host = "http://10.249.158.47:8080/api/";
        $user = "root";
        $pass = "";

        $bd = "miravereda2425";

        $connect = mysqli_connect($host, $user, $pass);

        mysqli_select_db($connect, $bd);



        return $connect;

        

    };

?>
