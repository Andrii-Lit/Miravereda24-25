package com.ieslavereda.es.miravereda.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lin_Fac {
    private int carrito_id;
    private int contenido_id;
    private double precio;
    private LocalDateTime changedTS;

    public Lin_Fac(int carrito_id, int contenido_id, double precio) {
        this.carrito_id = carrito_id;
        this.contenido_id = contenido_id;
        this.precio = precio;
        changedTS = LocalDateTime.now();

    }

    public int getCarrito_id() {
        return carrito_id;
    }

    public int getContenido_id() {
        return contenido_id;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDateTime getChangedTS() {
        return changedTS;
    }
}
