    package com.ieslavereda.es.miravereda.Model;

    import lombok.*;

    import java.sql.Date;
    import java.time.LocalDate;
    import java.io.Serializable;
    import java.time.LocalDateTime;
    @Builder
    @Getter
    @Setter

    @NoArgsConstructor
    @AllArgsConstructor
    public class Contenido implements Serializable {
        private int id, duracion;
        private String titulo, descripcion, genero, nombre_dir,
                actores_principales, poster_path;
        private Date fecha_estreno;
        private double puntuacion_media;
        private LocalDateTime changedTS;

        public Contenido(int id, String titulo, String descripcion, String genero, String nombre_dir,
                         int duracion, String actores_principales, Date fecha_estreno, double puntuacion_media,
                         String poster_path) {
            this.id = id;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.genero = genero;
            this.nombre_dir = nombre_dir;
            this.duracion = duracion;
            this.actores_principales = actores_principales;
            this.fecha_estreno = fecha_estreno;
            this.puntuacion_media = puntuacion_media;
            this.poster_path = poster_path;
            this.changedTS = LocalDateTime.now();
        }

    }


