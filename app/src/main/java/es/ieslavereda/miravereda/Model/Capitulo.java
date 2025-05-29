package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;

public class Capitulo {
    private int id;
    private String titulo;
    private double precio;
    private Temporada temporada;


    public Capitulo(int id, String titulo, double precio, Temporada temporada) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.temporada = temporada;

    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Temporada getTemporada() {
        return temporada;
    }


}
