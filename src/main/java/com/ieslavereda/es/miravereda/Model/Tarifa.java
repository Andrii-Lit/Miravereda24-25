package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Tarifa {
    private Tipo tipo;
    private double porcentaje;
    private LocalDateTime changedTS;

    public Tarifa (Tipo tipo){
        this.tipo = tipo;
        this.changedTS = LocalDateTime.now();
        if (tipo == Tipo.PELICULA){
            this.porcentaje = 0.20;
        } else if (tipo == Tipo.SERIE) {
            this.porcentaje = 0.10;
        } else {
            this.porcentaje = 0.05;
        }
    }

    @Getter
    public enum Tipo{
        PELICULA("pelicula"),
        SERIE("serie"),
        CORTO("corto");

        Tipo(String tipo){
            this.tipo = tipo;
        }
        private String tipo;
    }
}
