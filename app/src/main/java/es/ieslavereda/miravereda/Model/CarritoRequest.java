package es.ieslavereda.miravereda.Model;

/**
 * Clase que representa una petición para añadir un contenido al carrito de un cliente.
 */
public class CarritoRequest {
    private int clienteId;
    private int contenidoId;

    /**
     * Constructor para crear una instancia de CarritoRequest.
     *
     * @param clienteId   Identificador del cliente que realiza la petición.
     * @param contenidoId Identificador del contenido que se quiere añadir al carrito.
     */
    public CarritoRequest(int clienteId, int contenidoId) {
        this.clienteId = clienteId;
        this.contenidoId = contenidoId;
    }

    /**
     * Obtiene el identificador del cliente.
     *
     * @return el id del cliente.
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param clienteId nuevo id del cliente.
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Obtiene el identificador del contenido.
     *
     * @return el id del contenido.
     */
    public int getContenidoId() {
        return contenidoId;
    }

    /**
     * Establece el identificador del contenido.
     *
     * @param contenidoId nuevo id del contenido.
     */
    public void setContenidoId(int contenidoId) {
        this.contenidoId = contenidoId;
    }
}