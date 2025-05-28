package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Corto extends Contenido{
    private int tarifa_id;
    private boolean disponibilidad;
    private double precio_base;
    private LocalDateTime changedTS;
    public Corto(int id, String titulo, String descripcion, String genero,
                 String nombre_dir, int duracion, String actores_principales,
                 Date fecha_estreno, double puntuacion_media, String poster_path,
                 int tarifa_id, double precio_base) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.precio_base = precio_base;
        this.changedTS = LocalDateTime.now();
    }
    public double getPrecio_base() {
        return precio_base;
    }
    public LocalDateTime getChangedTS() {
        return changedTS;
    }
    public void setPrecio_base(double precio_base){
        this.precio_base = precio_base;
    }
    public int getTarifa_id() {
        return tarifa_id;
    }
}
