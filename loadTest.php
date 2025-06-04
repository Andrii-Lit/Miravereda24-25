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
        $html .= '<td data-label="ID">'.$row['id'].'</td>';
        $html .= '<td data-label="Nombre">'.$row['nombre'].'</td>';
        $html .= '<td data-label="Apellidos">'.$row['apellidos'].'</td>';
        $html .= '<td data-label="Contraseña">'.$row['contrasenya'].'</td>';
        $html .= '<td data-label="Domicilio">'.$row['domicilio'].'</td>';
        $html .= '<td data-label="Cód. Postal">'.$row['cod_postal'].'</td>';
        $html .= '<td data-label="Email">'.$row['email'].'</td>';
        $html .= '<td data-label="Nacimiento">'.$row['fecha_nac'].'</td>';
        $html .= '<td data-label="Tarjeta">'.$row['num_tarjeta'].'</td>';
        $html .= '<td><a href="update_user.php?id=' . $row['id'] . '">Editar</a></td>';
        $html .= '<td><a href="delete_user.php?id=' . $row['id'] . '">Eliminar</a></td>';
        $html .= '</tr>';
    }
} else {
    $html .= '<tr>';
    $html .= '<td colspan="11" data-label="Resultado">Sin resultados</td>';
    $html .= '</tr>';
}

echo json_encode($html, JSON_UNESCAPED_UNICODE);
?>