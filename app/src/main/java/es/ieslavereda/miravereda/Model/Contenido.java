package es.ieslavereda.miravereda.Model;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un contenido multimedia genérico.
 * Implementa Serializable para permitir su paso entre componentes Android
 * e implementa Comparable para poder ordenar contenidos por título.
 */
public class Contenido implements Serializable, Comparable<Contenido> {

    private int id, duracion;
    private String titulo, descripcion, genero, nombre_dir,
            actores_principales, poster_path;
    private LocalDate fecha_estreno;
    private double puntuacion_media;
    private double precio;

    /**
     * Constructor por defecto.
     */
    public Contenido() {
    }

    /**
     * Constructor completo con todos los parámetros, incluido precio.
     *
     * @param id                Identificador único del contenido.
     * @param titulo            Título del contenido.
     * @param descripcion       Descripción del contenido.
     * @param genero            Género del contenido.
     * @param nombre_dir        Nombre del director.
     * @param duracion          Duración en minutos.
     * @param actores_principales Actores principales.
     * @param fecha_estreno     Fecha de estreno.
     * @param puntuacion_media  Puntuación media.
     * @param poster_path       Ruta o URL del póster.
     * @param precio            Precio del contenido.
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
     * Constructor sin precio.
     *
     * @param id                Identificador único del contenido.
     * @param titulo            Título del contenido.
     * @param descripcion       Descripción del contenido.
     * @param genero            Género del contenido.
     * @param nombre_dir        Nombre del director.
     * @param duracion          Duración en minutos.
     * @param actores_principales Actores principales.
     * @param fecha_estreno     Fecha de estreno.
     * @param puntuacion_media  Puntuación media.
     * @param poster_path       Ruta o URL del póster.
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
     * Obtiene el precio del contenido.
     *
     * @return Precio.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene el título del contenido.
     *
     * @return Título.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la descripción del contenido.
     *
     * @return Descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el género del contenido.
     *
     * @return Género.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Obtiene el nombre del director.
     *
     * @return Nombre del director.
     */
    public String getNombre_dir() {
        return nombre_dir;
    }

    /**
     * Obtiene los actores principales.
     *
     * @return Actores principales.
     */
    public String getActores_principales() {
        return actores_principales;
    }

    /**
     * Obtiene la ruta o URL del póster.
     *
     * @return Ruta del póster.
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     * Obtiene la duración en minutos.
     *
     * @return Duración.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene la fecha de estreno en formato "yyyy-MM-dd".
     *
     * @return Fecha de estreno formateada o cadena vacía si no es compatible la versión.
     */
    public String getFecha_estreno() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return fecha_estreno.format(formatter);
        }
        return "";
    }

    /**
     * Obtiene la puntuación media.
     *
     * @return Puntuación media.
     */
    public double getPuntuacion_media() {
        return puntuacion_media;
    }

    /**
     * Obtiene el identificador único del contenido.
     *
     * @return Id del contenido.
     */
    public int getId() {
        return id;
    }

    /**
     * Compara dos contenidos por título, ignorando mayúsculas/minúsculas.
     *
     * @param contenido Objeto a comparar.
     * @return Resultado de la comparación lexicográfica.
     */
    @Override
    public int compareTo(Contenido contenido) {
        return titulo.compareToIgnoreCase(contenido.getTitulo());
    }
}