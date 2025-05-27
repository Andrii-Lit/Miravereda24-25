    package com.ieslavereda.es.miravereda.Model;

    import lombok.*;

    import java.sql.Date;
    import java.time.LocalDate;
    import java.time.LocalDateTime;


    @Builder
    @Getter
    @Setter

    @NoArgsConstructor
    @AllArgsConstructor


    public class Contenido {
        private int id, duracion;
        private String titulo, descripcion, genero, nombre_dir,
                actores_principales, poster_path;
        private Date fecha_estreno;
        private double puntuacion_media;
        @Builder.Default
        private LocalDateTime changedTs =LocalDateTime.now();







        }




