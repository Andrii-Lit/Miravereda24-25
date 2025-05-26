package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Capitulo{
    private int id;
    private int temporada_id;
    private String titulo;
    private  double precio;
    private LocalDateTime changedTS;

    public int getId() {
        return id;
    }

    public int getTemporada_id() {
        return temporada_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
