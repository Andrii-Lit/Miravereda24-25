package com.ieslavereda.es.miravereda;

import java.time.LocalDate;

public class Tarifa {
    private int id;
    private String tipo;
    private double Porcentaje;
    private LocalDate changedTS;

    public Tarifa(int id, String tipo, double porcentaje) {
        this.id = id;
        this.tipo = tipo;
        Porcentaje = porcentaje;
        changedTS = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public LocalDate getChangedTS() {
        return changedTS;
    }
}
