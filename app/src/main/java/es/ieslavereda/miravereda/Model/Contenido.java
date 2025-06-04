package es.ieslavereda.miravereda.Model;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Contenido implements Serializable, Comparable<Contenido> {
    private int id, duracion;
    private String titulo, descripcion, genero, nombre_dir,
            actores_principales, poster_path;
    private LocalDate fecha_estreno;
    private double puntuacion_media;
    private double precio;


    public Contenido() {

    }

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
     * @param precio
     */
    public Contenido(int id, String titulo, String descripcion, String genero, String nombre_dir,
                     int duracion, String actores_principales, LocalDate fecha_estreno, double puntuacion_media,
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
     */
    public Contenido(int id, String titulo, String descripcion, String genero, String nombre_dir,
                     int duracion, String actores_principales, LocalDate fecha_estreno, double puntuacion_media,
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

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     *
     * @return
     */
    public String getNombre_dir() {
        return nombre_dir;
    }

    /**
     *
     * @return
     */
    public String getActores_principales() {
        return actores_principales;
    }

    /**
     *
     * @return
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     *
     * @return
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     *
     * @return
     */
    public String getFecha_estreno() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return fecha_estreno.format(formatter);
        }
        return "";

    }

    /**
     *
     * @return
     */
    public double getPuntuacion_media() {
        return puntuacion_media;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param contenido the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Contenido contenido) {
        return titulo.compareToIgnoreCase(contenido.getTitulo());
    }
}
