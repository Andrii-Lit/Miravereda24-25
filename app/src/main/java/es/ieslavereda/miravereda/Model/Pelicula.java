package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;

/**
 * Representa una película que extiende la clase Contenido.
 */
public class Pelicula extends Contenido {
    private int tarifa_id;
    private LocalDate disponible_hasta;
    private double precio_base;

    /**
     * Constructor para crear una película.
     *
     * @param id                 Identificador único de la película.
     * @param titulo             Título de la película.
     * @param descripcion        Descripción de la película.
     * @param genero             Género de la película.
     * @param nombre_dir         Nombre del director.
     * @param duracion           Duración en minutos.
     * @param actores_principales Actores principales de la película.
     * @param fecha_estreno      Fecha de estreno.
     * @param puntuacion_media   Puntuación media recibida.
     * @param poster_path        Ruta o URL del póster.
     * @param tarifa_id          Identificador de la tarifa asociada.
     * @param disponible_hasta   Fecha hasta la cual la película está disponible.
     * @param precio_base        Precio base de la película.
     */
    public Pelicula(int id, String titulo, String descripcion, String genero,
                    String nombre_dir, int duracion, String actores_principales,
                    LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                    int tarifa_id, LocalDate disponible_hasta, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.disponible_hasta = disponible_hasta;
        this.precio_base = precio_base;
    }

    /**
     * Obtiene la fecha hasta la cual la película está disponible.
     *
     * @return fecha de disponibilidad.
     */
    public LocalDate getDisponible_hasta() {
        return disponible_hasta;
    }

    /**
     * Establece la fecha hasta la cual la película estará disponible.
     *
     * @param disponible_hasta nueva fecha de disponibilidad.
     */
    public void setDisponible_hasta(LocalDate disponible_hasta) {
        this.disponible_hasta = disponible_hasta;
    }

    /**
     * Obtiene el precio base de la película.
     *
     * @return precio base.
     */
    public double getPrecio_base() {
        return precio_base;
    }

    /**
     * Establece el precio base de la película.
     *
     * @param precio_base nuevo precio base.
     */
    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    /**
     * Obtiene el identificador de la tarifa asociada.
     *
     * @return tarifa_id.
     */
    public int getTarifa_id() {
        return tarifa_id;
    }
}