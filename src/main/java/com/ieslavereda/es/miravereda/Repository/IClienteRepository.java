package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteRepository {
    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    Cliente getClientePorEmail(String email) throws SQLException;
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    Cliente getCliente(int id) throws SQLException;

    /**
     *
     * @param clienteInput
     * @return
     * @throws SQLException
     */
    Cliente addCliente(Cliente clienteInput) throws SQLException;

    /**
     *
     * @param clienteInput
     * @return
     * @throws SQLException
     */
    Cliente updateCliente(Cliente clienteInput) throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    Cliente deleteCliente(int id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    List<Cliente> getAllClientes() throws SQLException;

    /**
     *
     * @param email
     * @param contrasenya
     * @return
     * @throws SQLException
     */
    Cliente login(String email, String contrasenya) throws SQLException;
}


