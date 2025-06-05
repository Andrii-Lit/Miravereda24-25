package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
public class Pelicula extends Contenido {

    private int tarifa_id;
    private Date disponible_hasta;
    private double precio_base;

    /**
     * Constructor de la clase Pelicula, que extiende Contenido.
     *
     * @param id                Identificador único de la película.
     * @param titulo            Título de la película.
     * @param descripcion       Descripción de la película.
     * @param genero            Género al que pertenece la película.
     * @param nombre_dir        Nombre del director de la película.
     * @param duracion          Duración en minutos de la película.
     * @param actores_principales Lista con los actores principales de la película.
     * @param fecha_estreno     Fecha de estreno de la película.
     * @param puntuacion_media  Puntuación media de la película.
     * @param poster_path       Ruta o URL del póster de la película.
     * @param tarifa_id         Identificador de la tarifa asociada a la película.
     * @param disponible_hasta  Fecha hasta la cual la película está disponible.
     * @param precio_base       Precio base de la película.
     */
    public Pelicula(int id, String titulo, String descripcion, String genero,
                    String nombre_dir, int duracion, String actores_principales,
                    Date fecha_estreno, double puntuacion_media, String poster_path, int tarifa_id,
                    Date disponible_hasta, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.disponible_hasta = disponible_hasta;
        this.precio_base = precio_base;
    }

}