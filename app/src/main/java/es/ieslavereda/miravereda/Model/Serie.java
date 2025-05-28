package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Serie extends Contenido {
    private static final Tarifa tarifa = new Tarifa(Tarifa.Tipo.SERIE);
    private List<Temporada> temporadas;
    private boolean disponibilidad;
    private LocalDateTime changedTS;

    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                 String poster_path, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.temporadas = temporadas;
        this.disponibilidad = disponibilidad;
        this.changedTS = LocalDateTime.now();
    }

    public boolean isDisponible() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public double getPrecioTotal() {
        double total = 0.0;
        if (temporadas != null) {
            for (Temporada temporada : temporadas) {
                total += temporada.getPrecioTotal();
            }
        }
        return total * tarifa.getPorcentaje();
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
