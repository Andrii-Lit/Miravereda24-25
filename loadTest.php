<?php

require 'connection.php';

$con = connection();

$columns = ['id', 'contrasenya', 'nombre', 'apellidos', 'domicilio', 'cod_postal', 'email', 'fecha_nac', 'num_tarjeta'];
$table = "cliente";

$campo = isset($_POST['campo']) ? $con->real_escape_string($_POST['campo']) : null;

$where = '';

if($campo != null){
    $where = "WHERE (";

    $cont = count($columns);
    for($i = 0; $i < $cont; $i++){
        $where .= $columns[$i] . " LIKE '%" . $campo . "%' OR ";
    }
    $where = substr_replace($where, "", -3);
    $where .= ")";
}

$sql = "SELECT " . implode(", ",$columns) . " FROM $table $where";

$resultado = $con->query($sql);
$num_rows = $resultado -> num_rows;

$html = ''; 

if($num_rows > 0){
    while($row = $resultado->fetch_assoc()) {
        $html .= '<tr>';
        $html .= '<td>'.$row['id'].'</td>';
        $html .= '<td>'.$row['nombre'].'</td>';
        $html .= '<td>'.$row['apellidos'].'</td>';
        $html .= '<td>'.$row['contrasenya'].'</td>';
        $html .= '<td>'.$row['domicilio'].'</td>';
        $html .= '<td>'.$row['cod_postal'].'</td>';
        $html .= '<td>'.$row['email'].'</td>';
        $html .= '<td>'.$row['fecha_nac'].'</td>';
        $html .= '<td>'.$row['num_tarjeta'].'</td>';
        $html .= '<td><a href="update_user.php?id=' . $row['id'] . '">Editar</a></td>';
        $html .= '<td><a href="delete_user.php?id=' . $row['id'] . '">Eliminar</a></td>';
        $html .= '<td></td>';
        $html .= '</tr>';
    }
} else {
    $html .= '<tr>';
    $html .= '<td colspan="11">Sin resultados</td>';
    $html .= '</tr>';
}

echo json_encode($html, JSON_UNESCAPED_UNICODE);
?>