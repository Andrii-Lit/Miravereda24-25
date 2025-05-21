package es.ieslavereda.miravereda.Model;

public class Posicion {
    private String titulo;
    private int portadaImagen;
    private String filmaffinity;
    private String tomatometer;
    private String popcornmeter;
    public Posicion(String titulo, int portadaImagen, String filmaffinity, String tomatometer, String popcornmeter){
        this.titulo=titulo;
        this.portadaImagen =portadaImagen;
        this.filmaffinity=filmaffinity;
        this.tomatometer=tomatometer;
        this.popcornmeter=popcornmeter;
    }

    public String getFilmaffinity() {
        return filmaffinity;
    }

    public String getPopcornmeter() {
        return popcornmeter;
    }

    public int getPortadaImagen() {
        return portadaImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTomatometer() {
        return tomatometer;
    }

    public void setPortadaImagen(int portadaImagen) {
        this.portadaImagen = portadaImagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}