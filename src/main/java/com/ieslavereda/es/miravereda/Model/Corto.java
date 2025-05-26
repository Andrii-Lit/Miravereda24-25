package com.ieslavereda.es.miravereda.Model;

import java.sql.Date;

public class Corto extends Contenido{
    private boolean disponibilidad;
    public Corto(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponible_hasta) {
        super(id,titulo,descripcion,genero,nombre_dir,duracion,actores_principales,fecha_estreno,puntuacion_media,poster_path);
        this.disponibilidad=true;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }
}
