package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Temporada {
    private int id;
    private int numero;
    private Serie serie;
    private List<Capitulo> capitulos;
    private LocalDateTime changedTS;

    public Temporada(int id, int numero, Serie serie) {
        this.id = id;
        this.numero = numero;
        this.serie = serie;
        this.capitulos = new ArrayList<>();
        this.changedTS = LocalDateTime.now();
    }

    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }

    public double getPrecioTotal() {
        double total = 0.0;
        for (Capitulo c : capitulos) {
            total += c.getPrecio();
        }
        return total;
    }

}