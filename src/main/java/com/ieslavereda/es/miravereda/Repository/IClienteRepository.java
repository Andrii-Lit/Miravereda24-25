package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteRepository {

        Cliente getCliente(int id) throws SQLException;
    Cliente addCliente(Cliente clienteInput) throws SQLException;
        Cliente updateCliente(Cliente clienteInput) throws SQLException;
    Cliente deleteCliente(int id) throws SQLException;
        List<Cliente> getAllClientes() throws SQLException;
    Cliente login(String email, String contrasenya) throws SQLException;
    }


