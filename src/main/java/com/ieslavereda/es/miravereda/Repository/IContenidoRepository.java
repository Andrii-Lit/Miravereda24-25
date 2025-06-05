package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Contenido;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para el repositorio de contenidos que define los métodos CRUD y operaciones relacionadas con el carrito.
 */
public interface IContenidoRepository {

    /**
     * Obtiene un contenido por su identificador único.
     *
     * @param id Identificador del contenido a buscar.
     * @return El contenido correspondiente al ID o null si no existe.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    Contenido getContenido(int id) throws SQLException;

    /**
     * Añade un nuevo contenido a la base de datos.
     *
     * @param contenido Objeto contenido con los datos a insertar.
     * @return El contenido insertado con su ID asignado.
     * @throws SQLException Si ocurre un error durante la inserción en la base de datos.
     */
    Contenido addContenido(Contenido contenido) throws SQLException;

    /**
     * Actualiza los datos de un contenido existente.
     *
     * @param contenido Objeto contenido con los datos actualizados (debe incluir el ID).
     * @return El contenido actualizado.
     * @throws SQLException Si ocurre un error durante la actualización en la base de datos.
     */
    Contenido updateContenido(Contenido contenido) throws SQLException;

    /**
     * Elimina un contenido de la base de datos dado su ID.
     *
     * @param id Identificador del contenido a eliminar.
     * @return El contenido eliminado o null si no existía.
     * @throws SQLException Si ocurre un error durante la eliminación en la base de datos.
     */
    Contenido deleteContenido(int id) throws SQLException;

    /**
     * Obtiene la lista de todos los contenidos almacenados.
     *
     * @return Lista con todos los contenidos.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    List<Contenido> getAllContenidos() throws SQLException;

    /**
     * Añade un contenido al carrito de un cliente.
     *
     * @param p_cliente_id   Identificador del cliente que añade el contenido.
     * @param p_contenido_id Identificador del contenido que se añade al carrito.
     * @throws SQLException Si ocurre un error durante la operación en la base de datos.
     */
    void anyadirCarrito(int p_cliente_id, int p_contenido_id) throws SQLException;
}