package com.ieslavereda.es.miravereda;



import java.sql.Date;

public class Serie extends Contenido{
    private boolean disponibilidad;
    public Serie(int contenido_id, int tarifa_id, String titulo, String descripcion, Genero genero, String nombre_dir, int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media) {
        super(contenido_id, tarifa_id, titulo, descripcion, genero, nombre_dir, duracion, actores_principales, fecha_estreno, puntuacion_media);
        this.disponibilidad = true;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }
}
