package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Valoracion {
    private int cliente_id;
    private int contenido_id;
    private int valor;
    private LocalDateTime changedTS;

    public Valoracion(int cliente_id, int contenido_id, int valor) {
        this.cliente_id = cliente_id;
        this.contenido_id = contenido_id;
        this.valor = valor;
        changedTS = LocalDateTime.now();

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

    public LocalDateTime getChangedTS() {
        return changedTS;
    }


}
