package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;
@Getter
@Setter
public class Serie extends Contenido {
    private List<Temporada> temporadas;
    private boolean disponibilidad;


    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.disponibilidad = disponibilidad;
        this.temporadas = temporadas;

    }

    public boolean isDisponible() {
        return disponibilidad;
    }

    public double getPrecioTotal() {
        double total = 0.0;
        if (temporadas != null) {
            for (Temporada temporada : temporadas) {
                total += temporada.getPrecioTotal();
            }
        }
        return total;
    }

}

