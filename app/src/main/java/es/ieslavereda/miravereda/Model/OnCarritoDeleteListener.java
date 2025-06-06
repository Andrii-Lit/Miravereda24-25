package es.ieslavereda.miravereda.Model;

/**
 * Interfaz para manejar eventos de eliminación de un elemento en el carrito.
 */
public interface OnCarritoDeleteListener {

    /**
     * Método que se llama cuando se elimina un contenido del carrito.
     *
     * @param contenido El objeto contenido que será eliminado.
     * @param position  La posición del contenido eliminado en la lista.
     */
    void onDelete(Contenido contenido, int position);
}