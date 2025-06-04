package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Corto extends Contenido {
    private int tarifa_id;
    private boolean disponibilidad;
    private double precio_base;

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
     * @param precio_base
     */
    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                 int tarifa_id, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.precio_base = precio_base;

    }

    /**
     *
     * @return
     */
    public double getPrecio_base() {
        return precio_base;
    }

    /**
     *
     * @param precio_base
     */
    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    /**
     *
     * @return
     */
    public int getTarifa_id() {
        return tarifa_id;
    }
}
