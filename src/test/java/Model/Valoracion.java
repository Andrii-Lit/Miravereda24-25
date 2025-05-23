package Model;

import java.time.LocalDate;

public class Valoracion {
    private int cliente_id;
    private int contenido_id;
    private int valor;
    private LocalDate changedTS;

    public Valoracion(int cliente_id, int contenido_id, int valor) {
        this.cliente_id = cliente_id;
        this.contenido_id = contenido_id;
        this.valor = valor;
        changedTS = LocalDate.now();

    }

    public int getCliente_id() {
        return cliente_id;
    }

    public int getContenido_id() {
        return contenido_id;
    }

    public int getValor() {
        return valor;
    }

    public LocalDate getChangedTS() {
        return changedTS;
    }


}
