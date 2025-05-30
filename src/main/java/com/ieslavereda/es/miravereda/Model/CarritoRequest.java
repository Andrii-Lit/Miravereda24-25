package com.ieslavereda.es.miravereda.Model;

public class CarritoRequest {
    private int clienteId;
    private int contenidoId;

    public CarritoRequest(int clienteId, int contenidoId) {
        this.clienteId = clienteId;
        this.contenidoId = contenidoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(int contenidoId) {
        this.contenidoId = contenidoId;
    }
}
