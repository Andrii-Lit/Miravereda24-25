package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarifa {
    private int id;
    private String tipo;
    private double Porcentaje;
    private LocalDateTime changedTS;

    public Tarifa(int id, String tipo, double porcentaje) {
        this.id = id;
        this.tipo = tipo;
        Porcentaje = porcentaje;
        changedTS = LocalDateTime.now();
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

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
