package com.ieslavereda.es.miravereda.Model;

public class CarritoRequest {
    private int clienteId;
    private int contenidoId;

    /**
     *
     * @param clienteId
     * @param contenidoId
     */
    public CarritoRequest(int clienteId, int contenidoId) {
        this.clienteId = clienteId;
        this.contenidoId = contenidoId;
    }

    /**
     *
     * @return
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     *
     * @param clienteId
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     *
     * @return
     */
    public int getContenidoId() {
        return contenidoId;
    }

    /**
     *
     * @param contenidoId
     */
    public void setContenidoId(int contenidoId) {
        this.contenidoId = contenidoId;
    }
}
