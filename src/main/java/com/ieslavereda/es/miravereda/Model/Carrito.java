package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Carrito {
    private int id;
    private  int cliente_id;
    private double total;
    private boolean activo;
    private LocalDateTime changedTS;

    public Carrito(int id, int cliente_id) {
        this.id = id;
        this.cliente_id = cliente_id;
        activo = true;
        total = 0.00;
        changedTS = LocalDateTime.now();

    }

    public int getId() {
        return id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public double getTotal() {
        return total;
    }

    public boolean isActivo() {
        return activo;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
