package com.ieslavereda.es.miravereda.Service;

import com.ieslavereda.es.miravereda.Model.Cliente;
import com.ieslavereda.es.miravereda.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la gestión de clientes que delega operaciones al repositorio correspondiente.
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public Cliente getClientePorEmail(String email) throws SQLException {
        return repository.getClientePorEmail(email);
    }
    /**
     * Obtiene un cliente dado su identificador único.
     *
     * @param id Identificador del cliente a buscar.
     * @return El cliente encontrado o null si no existe.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public Cliente getCliente(int id) throws SQLException {
        return repository.getCliente(id);
    }

    /**
     * Añade un nuevo cliente a la base de datos.
     *
     * @param cliente Objeto cliente con los datos a insertar.
     * @return El cliente insertado con su ID asignado.
     * @throws SQLException Si ocurre un error durante la inserción en la base de datos.
     */
    public Cliente addCliente(Cliente cliente) throws SQLException {
        return repository.addCliente(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente Objeto cliente con los datos actualizados (debe incluir el ID).
     * @return El cliente actualizado.
     * @throws SQLException Si ocurre un error durante la actualización en la base de datos.
     */
    public Cliente updateCliente(Cliente cliente) throws SQLException {
        return repository.updateCliente(cliente);
    }

    /**
     * Elimina un cliente dado su ID.
     *
     * @param id Identificador del cliente a eliminar.
     * @return El cliente eliminado o null si no existía.
     * @throws SQLException Si ocurre un error durante la eliminación en la base de datos.
     */
    public Cliente deleteCliente(int id) throws SQLException {
        return repository.deleteCliente(id);
    }

    /**
     * Obtiene la lista de todos los clientes almacenados.
     *
     * @return Lista con todos los clientes.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    public List<Cliente> getAllClientes() throws SQLException {
        return repository.getAllClientes();
    }

    /**
     * Realiza el login de un cliente comprobando su correo electrónico y contraseña.
     *
     * @param email      Correo electrónico del cliente.
     * @param contrasenya Contraseña del cliente.
     * @return El cliente correspondiente si las credenciales son válidas, o null si no.
     * @throws SQLException Si ocurre un error durante la consulta en la base de datos.
     */
    public Cliente login(String email, String contrasenya) throws SQLException {
        return repository.login(email, contrasenya);
    }

}