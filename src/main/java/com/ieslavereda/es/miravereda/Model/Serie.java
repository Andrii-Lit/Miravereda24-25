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
     * Constructor de la clase Serie, que extiende Contenido.
     *
     * @param id                Identificador único de la serie.
     * @param titulo            Título de la serie.
     * @param descripcion       Descripción de la serie.
     * @param genero            Género de la serie.
     * @param nombre_dir        Nombre del director de la serie.
     * @param duracion          Duración total o aproximada en minutos.
     * @param actores_principales Lista con los actores principales.
     * @param fecha_estreno     Fecha de estreno de la serie.
     * @param puntuacion_media  Puntuación media de la serie.
     * @param poster_path       Ruta o URL del póster de la serie.
     * @param disponibilidad    Indica si la serie está disponible o no.
     * @param temporadas        Lista de temporadas que componen la serie.
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
     * Indica si la serie está disponible.
     *
     * @return true si está disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return disponibilidad;
    }

    /**
     * Calcula el precio total de la serie sumando el precio total de todas sus temporadas.
     *
     * @return precio total de la serie.
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