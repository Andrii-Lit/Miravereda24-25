package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
public class Pelicula extends Contenido{

    private int tarifa_id;
    private Date disponible_hasta;
    private double precio_base;

    public Pelicula(int id, String titulo, String descripcion, String genero,
                    String nombre_dir, int duracion, String actores_principales,
                    Date fecha_estreno, double puntuacion_media, String poster_path,int tarifa_id,
                    Date disponible_hasta, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.disponible_hasta = disponible_hasta;
        this.precio_base = precio_base;

    }

}
