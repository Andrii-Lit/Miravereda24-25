package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Capitulo {
    private int id;
    private String titulo;
    private double precio;
    private Temporada temporada;
    private LocalDateTime changedTS;

    public Capitulo(int id, String titulo, double precio, Temporada temporada) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.temporada = temporada;
        this.changedTS = LocalDateTime.now();
    }

}