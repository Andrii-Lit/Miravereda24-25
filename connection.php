<?php

    function connection(){
        $host = "localhost";
        $user = "root";
        $pass = "1234";

        $bd = "web_test";

        $connect = mysqli_connect($host, $user, $pass);

        mysqli_select_db($connect, $bd);

        return $connect;
    };

?>
