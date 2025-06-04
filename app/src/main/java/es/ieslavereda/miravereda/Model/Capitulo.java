package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;

public class Capitulo {
    private int id;
    private String titulo;
    private double precio;
    private Temporada temporada;

    /**
     *
     * @param id
     * @param titulo
     * @param precio
     * @param temporada
     */
    public Capitulo(int id, String titulo, double precio, Temporada temporada) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.temporada = temporada;

    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public Temporada getTemporada() {
        return temporada;
    }


}
