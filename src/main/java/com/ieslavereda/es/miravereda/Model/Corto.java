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

    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 Date fecha_estreno, double puntuacion_media, String poster_path,
                 double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.precio_base = precio_base;
        this.changedTS = LocalDateTime.now();
    }
}
