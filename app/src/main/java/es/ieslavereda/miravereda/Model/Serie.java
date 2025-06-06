package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa una serie de contenido audiovisual que puede tener varias temporadas.
 * Extiende la clase Contenido.
 */
public class Serie extends Contenido {
    private int tarifa_id;
    private List<Temporada> temporadas;
    private boolean disponibilidad;

    /**
     * Constructor para crear una serie.
     *
     * @param id                 Identificador único de la serie.
     * @param titulo             Título de la serie.
     * @param descripcion        Descripción de la serie.
     * @param genero             Género de la serie.
     * @param nombre_dir         Nombre del director.
     * @param duracion           Duración total o aproximada.
     * @param actores_principales Actores principales de la serie.
     * @param fecha_estreno      Fecha de estreno de la serie.
     * @param puntuacion_media   Puntuación media recibida.
     * @param poster_path        Ruta o URL del póster de la serie.
     * @param precio             Precio de la serie.
     * @param tarifa_id          Identificador de la tarifa asociada.
     * @param disponibilidad     Estado de disponibilidad para la serie.
     * @param temporadas        Lista de temporadas que componen la serie.
     */
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                 double precio, int tarifa_id, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path, precio);
        this.tarifa_id = tarifa_id;
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
     * Establece el estado de disponibilidad de la serie.
     *
     * @param disponibilidad nuevo estado de disponibilidad.
     */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Obtiene la lista de temporadas de la serie.
     *
     * @return lista de temporadas.
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Establece la lista de temporadas de la serie.
     *
     * @param temporadas lista de temporadas a asignar.
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    /**
     * Obtiene el identificador de la tarifa asociada.
     *
     * @return tarifa_id.
     */
    public int getTarifa_id() {
        return tarifa_id;
    }

    /**
     * Establece el identificador de la tarifa asociada.
     *
     * @param tarifa_id nuevo identificador de tarifa.
     */
    public void setTarifa_id(int tarifa_id) {
        this.tarifa_id = tarifa_id;
    }

    /**
     * Obtiene el precio de la serie.
     *
     * @return precio.
     */
    @Override
    public double getPrecio() {
        return super.getPrecio();
    }
}