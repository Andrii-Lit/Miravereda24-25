package es.ieslavereda.miravereda.Model;

import java.time.LocalDateTime;
import java.util.Locale;

public class Tarifa {
    private Tipo tipo;
    private double porcentaje;
    private LocalDateTime changedTS;

    public Tarifa (Tipo tipo, double porcentaje){
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.changedTS = LocalDateTime.now();
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
