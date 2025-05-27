package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Serie extends Contenido{
    private int tarifa_id;
    private boolean disponibilidad;
    private double precio;
    private LocalDateTime changedTS;
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponibilidad, double precio) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.disponibilidad = disponibilidad;
        this.precio = precio;
        this.changedTS = LocalDateTime.now();
    }
}
