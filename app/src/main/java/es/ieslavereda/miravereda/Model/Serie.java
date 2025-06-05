package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Serie extends Contenido {
    private int tarifa_id;
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
     * @param tarifa_id
     * @param disponibilidad
     * @param temporadas
     */
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                 int tarifa_id, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
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
     * @param disponibilidad
     */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     *
     * @return
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     *
     * @param temporadas
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }



}
