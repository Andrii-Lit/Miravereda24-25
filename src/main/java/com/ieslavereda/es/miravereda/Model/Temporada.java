package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Temporada {
    private int id;
    private int serie_id;
    private int numero;
    private LocalDateTime changedTS;

    public Temporada(int id, int serie_id, int numero) {
        this.id = id;
        this.serie_id = serie_id;
        this.numero = numero;
        changedTS = LocalDateTime.now();

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

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
