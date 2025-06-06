package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una temporada de una serie, compuesta por varios capítulos.
 */
public class Temporada {
    private int id;
    private int numero;
    private Serie serie;
    private List<Capitulo> capitulos;

    /**
     * Constructor para crear una temporada.
     *
     * @param id     Identificador único de la temporada.
     * @param numero Número que indica la temporada dentro de la serie.
     * @param serie  La serie a la que pertenece esta temporada.
     */
    public Temporada(int id, int numero, Serie serie) {
        this.id = id;
        this.numero = numero;
        this.serie = serie;
        this.capitulos = new ArrayList<>();
    }

    /**
     * Obtiene el identificador de la temporada.
     *
     * @return el id de la temporada.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el número de la temporada.
     *
     * @return el número de la temporada.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene la serie a la que pertenece la temporada.
     *
     * @return la serie.
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * Obtiene la lista de capítulos de la temporada.
     *
     * @return lista de capítulos.
     */
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    /**
     * Establece la lista de capítulos de la temporada.
     *
     * @param capitulos lista de capítulos.
     */
    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    /**
     * Añade un capítulo a la lista de capítulos de la temporada.
     *
     * @param capitulo capítulo a añadir.
     */
    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }
}