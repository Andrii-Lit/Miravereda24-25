<?php

    function connection(){
        
        $host = "localhost";
        $user = "root";
        $pass = "";
        
        //$host = "http://10.249.158.47:8080/api/";
        //$user = "proyecto2425";
        //$pass = "1111";

        $bd = "miravereda2425";

        $connect = mysqli_connect($host, $user, $pass);

        mysqli_select_db($connect, $bd);



        return $connect;

        

    };

?>
