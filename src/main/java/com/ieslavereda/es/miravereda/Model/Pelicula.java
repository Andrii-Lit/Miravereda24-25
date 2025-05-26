package com.ieslavereda.es.miravereda.Model;

import lombok.*;

import java.sql.Date;



@Setter

public class Pelicula extends Contenido {
    private boolean disponible_hasta;


    public Pelicula(int id, String titulo, String descripcion, String genero, String nombre_dir,
                    int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                    String poster_path, boolean disponible_hasta) {
        super(id,titulo,descripcion,genero,nombre_dir,duracion,actores_principales,fecha_estreno,puntuacion_media,poster_path);
        this.disponible_hasta = disponible_hasta;


    }

    public boolean isDisponible_hasta() {
        return disponible_hasta;
    }


}
