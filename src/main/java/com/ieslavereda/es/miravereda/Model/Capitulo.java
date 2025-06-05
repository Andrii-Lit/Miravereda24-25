package com.ieslavereda.es.miravereda.Model;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un capítulo individual de una temporada.
 * Contiene información como el identificador, título, precio base
 * y la temporada a la que pertenece.
 *
 * Esta clase forma parte del modelo de datos de la aplicación.
 *
 * @author
 * Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
@Getter
@Setter
public class Capitulo {
    private int id;
    private String titulo;
    private double precio_base;
    private Temporada temporada;

    /**
     * Constructor para crear un capítulo con los parámetros especificados.
     *
     * @param id Identificador único del capítulo.
     * @param titulo Título del capítulo.
     * @param precio Precio base del capítulo.
     * @param temporada Temporada a la que pertenece el capítulo.
     */
    public Capitulo(int id, String titulo, double precio, Temporada temporada) {
        this.id = id;
        this.titulo = titulo;
        this.precio_base = precio;
        this.temporada = temporada;
    }
}