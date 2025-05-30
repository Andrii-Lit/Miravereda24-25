package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Pelicula extends Contenido {
    private int tarifa_id;
    private LocalDate disponible_hasta;
    private double precio_base;

    public Pelicula(int id, String titulo, String descripcion, String genero,
                    String nombre_dir, int duracion, String actores_principales,
                    LocalDate fecha_estreno, double puntuacion_media, String poster_path,
                    int tarifa_id, LocalDate disponible_hasta, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.disponible_hasta = disponible_hasta;
        this.precio_base = precio_base;

    }

    public LocalDate getDisponible_hasta() {
        return disponible_hasta;
    }

    public double getPrecio_base() {
        return precio_base;
    }

    public void setDisponible_hasta(LocalDate disponible_hasta) {
        this.disponible_hasta = disponible_hasta;
    }

    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    public int getTarifa_id() {
        return tarifa_id;
    }
}
