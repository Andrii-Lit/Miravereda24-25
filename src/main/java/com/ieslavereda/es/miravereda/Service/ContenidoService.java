package com.ieslavereda.es.miravereda.Service;

import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la gestión de contenidos, que delega operaciones al repositorio correspondiente.
 */
@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    /**
     * Obtiene un contenido por su identificador único.
     *
     * @param id Identificador del contenido.
     * @return El contenido correspondiente o null si no existe.
     * @throws SQLException Si ocurre un error en la consulta a la base de datos.
     */
    public Contenido getContenido(int id) throws SQLException {
        return repository.getContenido(id);
    }

    /**
     * Añade un nuevo contenido a la base de datos.
     *
     * @param contenido Objeto contenido con los datos a insertar.
     * @return El contenido insertado.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    public Contenido addContenido(Contenido contenido) throws SQLException {
        return repository.addContenido(contenido);
    }

    /**
     * Actualiza un contenido existente.
     *
     * @param contenido Objeto contenido con datos actualizados.
     * @return El contenido actualizado.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    public Contenido updateContenido(Contenido contenido) throws SQLException {
        return repository.updateContenido(contenido);
    }

    /**
     * Elimina un contenido dado su identificador.
     *
     * @param id Identificador del contenido a eliminar.
     * @return El contenido eliminado o null si no existía.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
    public Contenido deleteContenido(int id) throws SQLException {
        return repository.deleteContenido(id);
    }

    /**
     * Obtiene la lista completa de contenidos almacenados.
     *
     * @return Lista de todos los contenidos.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    public List<Contenido> getAllContenidos() throws SQLException {
        return repository.getAllContenidos();
    }

    /**
     * Añade un contenido al carrito de un cliente.
     *
     * @param clienteId   Identificador del cliente.
     * @param contenidoId Identificador del contenido a añadir.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void anyadirCarrito(int clienteId, int contenidoId) throws SQLException {
        repository.anyadirCarrito(clienteId, contenidoId);
    }

    /**
     * Obtiene todos los contenidos presentes en el carrito de un cliente.
     *
     * @param clienteId Identificador del cliente.
     * @return Lista de contenidos en el carrito.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    public List<Contenido> getAllCarrito(int clienteId) throws SQLException {
        return repository.getAllCarrito(clienteId);
    }

    /**
     * Realiza la compra de los contenidos del carrito de un cliente.
     *
     * @param clienteId Identificador del cliente que realiza la compra.
     * @throws SQLException Si ocurre un error durante la operación de compra.
     */
    public void comprar(int clienteId) throws SQLException {
        repository.comprar(clienteId);
    }

    /**
     * Permite a un cliente votar un contenido con un valor determinado.
     *
     * @param clienteId   Identificador del cliente que vota.
     * @param contenidoId Identificador del contenido votado.
     * @param valor       Valor del voto (por ejemplo, puntuación).
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void votar(int clienteId, int contenidoId, int valor) throws SQLException {
        repository.votar(clienteId, contenidoId, valor);
    }

    /**
     * Elimina un contenido del carrito de un cliente.
     *
     * @param clienteId   Identificador del cliente.
     * @param contenidoId Identificador del contenido a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void quitarProducto(int clienteId, int contenidoId) throws SQLException {
        repository.quitarProducto(clienteId, contenidoId);
    }
}