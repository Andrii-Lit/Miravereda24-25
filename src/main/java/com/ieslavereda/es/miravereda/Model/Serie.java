package com.ieslavereda.es.miravereda.Model;



import java.sql.Date;

public class Serie extends Contenido{
    private Date disponibilidad;
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, Date disponibilidad) {
        super(id,titulo,descripcion,genero,nombre_dir,duracion,actores_principales,fecha_estreno,puntuacion_media,poster_path);
        this.disponibilidad = disponibilidad;
    }

    public Date getDisponibilidad() {
        return disponibilidad;
    }
}
