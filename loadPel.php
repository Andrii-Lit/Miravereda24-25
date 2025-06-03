<?php

require 'connection.php';

$con = connection();

$columns = ['id', 'titulo', 'descripcion', 'genero', 'nombre_dir', 'duracion', 'actores_principales', 'fecha_estreno', 'puntuacion_media', 'poster_path', 'tipo', 'precio'];
$table = "contenido";

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
            $html .= '<td>' . $row['id'] . '</td>';
            $html .= '<td><img src="' . $row['poster_path'] . '"></td>';
            $html .= '<td>' . $row['titulo'] . '</td>';
            $html .= '<td>' . $row['descripcion'] . '</td>';
            $html .= '<td>' . $row['genero'] . '</td>';
            $html .= '<td>' . $row['nombre_dir'] . '</td>';
            $html .= '<td>' . $row['duracion'] . '</td>';
            $html .= '<td>' . $row['actores_principales'] . '</td>';
            $html .= '<td>' . $row['fecha_estreno'] . '</td>';
            $html .= '<td>' . $row['puntuacion_media'] . '</td>';
            $html .= '<td>' . $row['tipo'] . '</td>';
            $html .= '<td>' . $row['precio'] . '</td>';
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