package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Date;
@Getter
@Setter
public class Corto extends Contenido {
 
    private boolean disponibilidad;
    private double precio_base;
    private LocalDateTime changedTS;

    /**
     *
     * @param id
     * @param titulo
     * @param descripcion
     * @param genero
     * @param nombre_dir
     * @param duracion
     * @param actores_principales
     * @param fecha_estreno
     * @param puntuacion_media
     * @param poster_path
     * @param precio_base
     */
    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 Date fecha_estreno, double puntuacion_media, String poster_path,
                 double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.precio_base = precio_base;

    }
}
