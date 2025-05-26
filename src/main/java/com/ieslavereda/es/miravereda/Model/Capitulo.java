package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;

public class Capitulo{
    private int id;
    private int temporada_id;
    private String titulo;
    private  double precio;
    private LocalDate changedTS;

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

    public LocalDate getChangedTS() {
        return changedTS;
    }
}
