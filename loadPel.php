<?php
include('auth.php');
require 'connection.php';

$con = connection();

$columns = ['id', 'titulo', 'descripcion', 'genero', 'nombre_dir', 'duracion', 'actores_principales', 'fecha_estreno', 'puntuacion_media', 'poster_path', 'tipo', 'precio'];
$columnsWhere = ['id', 'titulo', 'genero', 'nombre_dir', 'duracion', 'actores_principales', 'fecha_estreno', 'puntuacion_media', 'tipo', 'precio'];
$table = "contenido";

$campo = isset($_POST['campo']) ? $con->real_escape_string($_POST['campo']) : null;

$where = '';

if($campo != null){
    $where = "WHERE (";

    $cont = count($columnsWhere);
    for($i = 0; $i < $cont; $i++){
        $where .= $columnsWhere[$i] . " LIKE '%" . $campo . "%' OR ";
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
            $html .= '<td data-label="ID">' . $row['id'] . '</td>';
            $html .= '<td data-label="POSTER"><img src="' . $row['poster_path'] . '" alt="Poster" style="max-width: 80px;"></td>';
            $html .= '<td data-label="TÍTULO">' . $row['titulo'] . '</td>';
            $html .= '<td data-label="GÉNERO">' . $row['genero'] . '</td>';
            $html .= '<td data-label="DIRECTOR">' . $row['nombre_dir'] . '</td>';
            $html .= '<td data-label="DURACIÓN">' . $row['duracion'] . '</td>';
            $html .= '<td data-label="ACTORES">' . $row['actores_principales'] . '</td>';
            $html .= '<td data-label="ESTRENO">' . $row['fecha_estreno'] . '</td>';
            $html .= '<td data-label="PUNTUACIÓN">' . $row['puntuacion_media'] . '</td>';
            $html .= '<td data-label="TIPO">' . $row['tipo'] . '</td>';
            $html .= '<td data-label="PRECIO">' . $row['precio'] . '</td>';
            $html .= '<td><a href="update_pel.php?id=' . $row['id'] . '">Editar</a></td>';
            $html .= '<td><a href="delete_pel.php?id=' . $row['id'] . '">Eliminar</a></td>';
        $html .= '</tr>';
    }
} else {
    $html .= '<tr>';
    $html .= '<td colspan="13" data-label="Resultado">Sin resultados</td>';
    $html .= '</tr>';
}

echo json_encode($html, JSON_UNESCAPED_UNICODE);
?>