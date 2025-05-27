package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;

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

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
