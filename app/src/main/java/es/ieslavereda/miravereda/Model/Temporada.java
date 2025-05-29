package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Temporada {
    private int id;
    private int numero;
    private Serie serie;
    private List<Capitulo> capitulos;


    public Temporada(int id, int numero, Serie serie) {
        this.id = id;
        this.numero = numero;
        this.serie = serie;
        this.capitulos = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public Serie getSerie() {
        return serie;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }

    public double getPrecioTotal_base() {
        double total = 0.0;
        for (Capitulo c : capitulos) {
            total += c.getPrecio();
        }
        return total;
    }


}
