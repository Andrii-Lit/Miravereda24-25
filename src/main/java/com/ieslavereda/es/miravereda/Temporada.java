package com.ieslavereda.es.miravereda;

import java.time.LocalDate;

public class Temporada {
    private int id;
    private int serie_id;
    private int numero;
    private LocalDate changedTS;

    public Temporada(int id, int serie_id, int numero) {
        this.id = id;
        this.serie_id = serie_id;
        this.numero = numero;
        changedTS = LocalDate.now();

    }

    public int getSerie_id() {
        return serie_id;
    }

    public int getNumero() {
        return numero;
    }

    public int getId() {
        return id;
    }

    public LocalDate getChangedTS() {
        return changedTS;
    }
}
