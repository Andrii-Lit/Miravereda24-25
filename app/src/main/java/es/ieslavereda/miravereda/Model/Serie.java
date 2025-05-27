package es.ieslavereda.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Serie extends Contenido{
    private static final Tarifa tarifa = new Tarifa(Tarifa.Tipo.SERIE);
    private List<Temporada> temporadas;
    private boolean disponibilidad;
    private double precio;
    private LocalDateTime changedTS;
    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponibilidad, double precio) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.disponibilidad = disponibilidad;
        this.precio = getPrecio() * tarifa.getPorcentaje();
        this.changedTS = LocalDateTime.now();
    }
    public boolean isDisponible() {
        return disponibilidad;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
    public void setDisponibilidad(boolean disponibilidad){
        this.disponibilidad = disponibilidad;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
}
