package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@Getter
@Setter
@Builder

public class Contenido {
    private int id;
    private int tarifa_id;
    private String titulo;
    private String descripcion;
    private Genero genero;
    private String nombre_dir;
    private int duracion;
    private String actores_principales;
    private Date fecha_estreno;
    private double puntuacion_media;
    private LocalDate changedTS;

    public Contenido( int id,int tarifa_id, String titulo, String descripcion, Genero genero, String nombre_dir, int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media) {
        this.id = id;
        this.tarifa_id = tarifa_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.nombre_dir = nombre_dir;
        this.duracion = duracion;
        this.actores_principales = actores_principales;
        this.fecha_estreno = fecha_estreno;
        this.puntuacion_media = puntuacion_media;
        changedTS = now();
    }

    @Getter
    public enum Genero {
        SERIE("Serie"),
        PELICULA(""),
        CORTO("Corto");
        private String tipo;

             Genero(String tipo) {
                this.tipo = tipo;
            }

        public String getTipo() {
            return tipo;
        }
    }



}
