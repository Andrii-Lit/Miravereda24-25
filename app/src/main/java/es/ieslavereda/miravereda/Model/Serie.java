package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Serie extends Contenido {
    private int tarifa_id;
    private List<Temporada> temporadas;
    private boolean disponibilidad;
    private LocalDateTime changedTS;

    public Serie(int id, String titulo, String descripcion, String genero, String nombre_dir,
                 int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media, String poster_path,
                 int tarifa_id, boolean disponibilidad, List<Temporada> temporadas) {
        super(id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales,
                fecha_estreno, puntuacion_media, poster_path);
        this.tarifa_id = tarifa_id;
        this.disponibilidad = disponibilidad;
        this.temporadas = temporadas;
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
        return total;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
