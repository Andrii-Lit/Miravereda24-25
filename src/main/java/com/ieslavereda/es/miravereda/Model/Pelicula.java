package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
public class Pelicula extends Contenido{
    private static final Tarifa tarifa = new Tarifa(Tarifa.Tipo.PELICULA);
    private Date disponible_hasta;
    private double precio;
    private LocalDateTime changedTS;
    public Pelicula(int id, String titulo, String descripcion, String genero,
                    String nombre_dir, int duracion, String actores_principales,
                    Date fecha_estreno, double puntuacion_media, String poster_path,
                    Date disponible_hasta, double precio) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.disponible_hasta = disponible_hasta;
        this.precio = precio * tarifa.getPorcentaje();
        this.changedTS = LocalDateTime.now();
    }

}
