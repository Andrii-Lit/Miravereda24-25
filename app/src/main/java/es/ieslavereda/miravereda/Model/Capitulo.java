package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;

/**
 * Representa un capítulo de una temporada de una serie o programa.
 */
public class Capitulo {
    private int id;
    private String titulo;
    private double precio;
    private Temporada temporada;

    /**
     * Constructor de la clase Capitulo.
     *
     * @param id        Identificador único del capítulo.
     * @param titulo    Título del capítulo.
     * @param precio    Precio asociado al capítulo.
     * @param temporada Temporada a la que pertenece el capítulo.
     */
    public Capitulo(int id, String titulo, double precio, Temporada temporada) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.temporada = temporada;
    }

    /**
     * Obtiene el identificador del capítulo.
     *
     * @return el id del capítulo.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el título del capítulo.
     *
     * @return el título del capítulo.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el precio del capítulo.
     *
     * @return el precio del capítulo.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del capítulo.
     *
     * @param precio nuevo precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la temporada a la que pertenece el capítulo.
     *
     * @return la temporada asociada.
     */
    public Temporada getTemporada() {
        return temporada;
    }
}