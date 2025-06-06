package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;

/**
 * Clase que representa un corto dentro del catálogo de contenidos.
 * Hereda de la clase Contenido.
 */
public class Corto extends Contenido {
    private int tarifa_id;
    private boolean disponibilidad;
    private double precio_base;

    /**
     * Constructor para crear un objeto Corto.
     *
     * @param id                Identificador único del corto.
     * @param titulo            Título del corto.
     * @param descripcion       Descripción del corto.
     * @param genero            Género al que pertenece el corto.
     * @param nombre_dir        Nombre del director.
     * @param duracion          Duración en minutos.
     * @param actores_principales Actores principales del corto.
     * @param fecha_estreno     Fecha de estreno.
     * @param puntuacion_media  Puntuación media del corto.
     * @param poster_path       Ruta o URL del póster.
     * @param tarifa_id         Identificador de la tarifa asociada.
     * @param precio_base       Precio base del corto.
     */
    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                 int tarifa_id, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.precio_base = precio_base;
    }

    /**
     * Obtiene el precio base del corto.
     *
     * @return Precio base.
     */
    public double getPrecio_base() {
        return precio_base;
    }

    /**
     * Establece el precio base del corto.
     *
     * @param precio_base Nuevo precio base.
     */
    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    /**
     * Obtiene el identificador de la tarifa asociada.
     *
     * @return ID de la tarifa.
     */
    public int getTarifa_id() {
        return tarifa_id;
    }
}