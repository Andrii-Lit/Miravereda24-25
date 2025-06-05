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
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @return
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     *
     * @return
     */
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    /**
     *
     * @param capitulos
     */
    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    /**
     *
     * @param capitulo
     */
    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }



}
