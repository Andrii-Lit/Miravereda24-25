package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Cliente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para el repositorio de clientes que define los métodos CRUD y autenticación.
 */
public interface IClienteRepository {

    /**
     * Obtiene un cliente dado su identificador único.
     *
     * @param id Identificador del cliente a buscar.
     * @return El cliente correspondiente al ID o null si no existe.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    Cliente getCliente(int id) throws SQLException;

    /**
     * Añade un nuevo cliente a la base de datos.
     *
     * @param clienteInput Objeto cliente con los datos a insertar (sin ID).
     * @return El cliente insertado con su ID generado.
     * @throws SQLException Si ocurre un error durante la inserción en la base de datos.
     */
    Cliente addCliente(Cliente clienteInput) throws SQLException;

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param clienteInput Objeto cliente con los datos actualizados (incluyendo ID).
     * @return El cliente actualizado.
     * @throws SQLException Si ocurre un error durante la actualización en la base de datos.
     */
    Cliente updateCliente(Cliente clienteInput) throws SQLException;

    /**
     * Elimina un cliente de la base de datos dado su ID.
     *
     * @param id Identificador del cliente a eliminar.
     * @return El cliente eliminado o null si no existía.
     * @throws SQLException Si ocurre un error durante la eliminación en la base de datos.
     */
    Cliente deleteCliente(int id) throws SQLException;

    /**
     * Obtiene la lista de todos los clientes almacenados.
     *
     * @return Lista con todos los clientes.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    List<Cliente> getAllClientes() throws SQLException;

    /**
     * Realiza el proceso de login verificando email y contraseña.
     *
     * @param email      Email del cliente que intenta acceder.
     * @param contrasenya Contraseña asociada al email.
     * @return El cliente autenticado si las credenciales son correctas, o null si fallan.
     * @throws SQLException Si ocurre un error durante el proceso de autenticación.
     */
    Cliente login(String email, String contrasenya) throws SQLException;
}