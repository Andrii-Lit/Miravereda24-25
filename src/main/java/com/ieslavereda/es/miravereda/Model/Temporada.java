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

    /**
     * Constructor de la clase Temporada.
     *
     * @param id     Identificador único de la temporada.
     * @param numero Número de la temporada dentro de la serie.
     * @param serie  Serie a la que pertenece esta temporada.
     */
    public Temporada(int id, int numero, Serie serie) {
        this.id = id;
        this.numero = numero;
        this.serie = serie;
        this.capitulos = new ArrayList<>();
    }

    /**
     * Añade un capítulo a la lista de capítulos de la temporada.
     *
     * @param capitulo Objeto Capitulo que se desea agregar.
     */
    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }

    /**
     * Calcula el precio total de la temporada sumando el precio base de todos sus capítulos.
     *
     * @return Precio total acumulado de la temporada.
     */
    public double getPrecioTotal() {
        double total = 0.0;
        for (Capitulo c : capitulos) {
            total += c.getPrecio_base();
        }
        return total;
    }
}