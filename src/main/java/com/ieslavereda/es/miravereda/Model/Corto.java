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
     * Constructor de la clase Corto, que extiende Contenido.
     *
     * @param id                Identificador único del corto.
     * @param titulo            Título del corto.
     * @param descripcion       Descripción del corto.
     * @param genero            Género al que pertenece el corto.
     * @param nombre_dir        Nombre del director del corto.
     * @param duracion          Duración en minutos del corto.
     * @param actores_principales Lista con los actores principales del corto.
     * @param fecha_estreno     Fecha de estreno del corto.
     * @param puntuacion_media  Puntuación media del corto.
     * @param poster_path       Ruta o URL del póster del corto.
     * @param precio_base       Precio base del corto.
     */
    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 Date fecha_estreno, double puntuacion_media, String poster_path,
                 double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.precio_base = precio_base;
    }
}