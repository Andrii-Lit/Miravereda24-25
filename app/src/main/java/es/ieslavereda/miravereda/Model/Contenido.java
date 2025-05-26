package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.util.Date;

public class Contenido {
    private int id, duracion;
    private String titulo, descripcion, genero, nombre_dir,
            actores_principales, poster_path;
    private Date fecha_estreno;
    private double puntuacion_media;
    private LocalDate changedTS;



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
        this.changedTS = LocalDate.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombre_dir() {
        return nombre_dir;
    }

    public String getActores_principales() {
        return actores_principales;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getDuracion() {
        return duracion;
    }

    public Date getFecha_estreno() {
        return fecha_estreno;
    }

    public double getPuntuacion_media() {
        return puntuacion_media;
    }

    public int getId() {
        return id;
    }
}
