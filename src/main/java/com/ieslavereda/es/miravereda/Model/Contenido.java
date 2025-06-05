package com.ieslavereda.es.miravereda.Model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Contenido implements Serializable {
    private int id, duracion;
    private String titulo, descripcion, genero, nombre_dir,
            actores_principales, poster_path;
    private Date fecha_estreno;
    private double puntuacion_media, precio;

    /**
     * Constructor completo de Contenido con todos los atributos, incluyendo precio.
     *
     * @param id                Identificador único del contenido.
     * @param titulo            Título del contenido.
     * @param descripcion       Descripción del contenido.
     * @param genero            Género al que pertenece el contenido.
     * @param nombre_dir        Nombre del director del contenido.
     * @param duracion          Duración en minutos del contenido.
     * @param actores_principales Lista con los actores principales.
     * @param fecha_estreno     Fecha de estreno del contenido.
     * @param puntuacion_media  Puntuación media del contenido.
     * @param poster_path       Ruta o URL del póster del contenido.
     * @param precio            Precio base del contenido.
     */
    public Contenido(int id, String titulo, String descripcion, String genero, String nombre_dir,
                     int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                     String poster_path, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.nombre_dir = nombre_dir;
        this.duracion = duracion;
        this.actores_principales = actores_principales;
        this.fecha_estreno = fecha_estreno;
        this.puntuacion_media = puntuacion_media;
        this.poster_path = poster_path;
        this.precio = precio;
    }

    /**
     * Constructor de Contenido sin especificar precio.
     *
     * @param id                Identificador único del contenido.
     * @param titulo            Título del contenido.
     * @param descripcion       Descripción del contenido.
     * @param genero            Género al que pertenece el contenido.
     * @param nombre_dir        Nombre del director del contenido.
     * @param duracion          Duración en minutos del contenido.
     * @param actores_principales Lista con los actores principales.
     * @param fecha_estreno     Fecha de estreno del contenido.
     * @param puntuacion_media  Puntuación media del contenido.
     * @param poster_path       Ruta o URL del póster del contenido.
     */
    public Contenido(int id, String titulo, String descripcion, String genero, String nombre_dir,
                     int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                     String poster_path) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.nombre_dir = nombre_dir;
        this.duracion = duracion;
        this.actores_principales = actores_principales;
        this.fecha_estreno = fecha_estreno;
        this.puntuacion_media = puntuacion_media;
        this.poster_path = poster_path;
    }
}