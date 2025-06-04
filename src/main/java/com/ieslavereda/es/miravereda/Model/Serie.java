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
     * @param disponibilidad
     * @param temporadas
     */
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.disponibilidad = disponibilidad;
        this.temporadas = temporadas;

    }

    /**
     *
     * @return
     */
    public boolean isDisponible() {
        return disponibilidad;
    }

    /**
     *
     * @return
     */
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

