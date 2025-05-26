package com.ieslavereda.es.miravereda;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Builder
public class Pelicula extends Contenido {
    private Date disponible_hasta;

    public Pelicula(int contenido_id, int tarifa_id, String titulo, String descripcion, Genero genero, String nombre_dir, int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media) {
        super(contenido_id, tarifa_id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media);
        disponible_hasta = null;
    }

    public Date getDisponible_hasta() {
        return disponible_hasta;
    }
}
