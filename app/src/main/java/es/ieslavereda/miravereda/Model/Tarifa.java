package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;

public class Tarifa {
    private Tipo tipo;
    private double porcentaje;
    private LocalDateTime changedTS;

    public Tarifa (Tipo tipo){
        this.tipo = tipo;
        this.changedTS = LocalDateTime.now();
        if (tipo == Tipo.PELICULA){
            this.porcentaje = 0.20;
        } else if (tipo == Tipo.SERIE) {
            this.porcentaje = 0.10;
        } else {
            this.porcentaje = 0.05;
        }
    }
    public Tipo getTipo() {
        return tipo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
    public void setPorcentaje(double porcentaje){
        this.porcentaje = porcentaje;
    }

    public enum Tipo{
        PELICULA("pelicula"),
        SERIE("serie"),
        CORTO("corto");

        Tipo(String tipo){
            this.tipo = tipo;
        }
        private String tipo;
        public String getTipo(){
            return tipo;
        }
    }


}
