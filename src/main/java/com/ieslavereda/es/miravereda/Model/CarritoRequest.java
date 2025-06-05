package com.ieslavereda.es.miravereda.Model;

/**
 * Clase que representa una solicitud para añadir contenido al carrito.
 * Contiene el identificador del cliente y del contenido.
 *
 * Esta clase es utilizada en las operaciones HTTP que implican añadir
 * o gestionar contenido en el carrito de un cliente.
 *
 * @author
 * Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
public class CarritoRequest {
    private int clienteId;
    private int contenidoId;

    /**
     * Constructor que permite crear una solicitud con el ID del cliente y del contenido.
     *
     * @param clienteId Identificador del cliente que realiza la acción.
     * @param contenidoId Identificador del contenido a añadir al carrito.
     */
    public CarritoRequest(int clienteId, int contenidoId) {
        this.clienteId = clienteId;
        this.contenidoId = contenidoId;
    }

    /**
     * Devuelve el identificador del cliente.
     *
     * @return ID del cliente.
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param clienteId Nuevo ID del cliente.
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Devuelve el identificador del contenido.
     *
     * @return ID del contenido.
     */
    public int getContenidoId() {
        return contenidoId;
    }

    /**
     * Establece el identificador del contenido.
     *
     * @param contenidoId Nuevo ID del contenido.
     */
    public void setContenidoId(int contenidoId) {
        this.contenidoId = contenidoId;
    }
}