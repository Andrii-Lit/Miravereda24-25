package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteRepository implements IClienteRepository{


    @Override
    public Cliente getCliente(int id) throws SQLException {
        return null;
    }

    @Override
    public Cliente addCliente(Cliente clienteInput) throws SQLException {
        return null;
    }

    @Override
    public boolean updateCliente(Cliente clienteInput) throws SQLException {
        return false;
    }

    @Override
    public Cliente deleteCliente(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Cliente> getAllClientes() throws SQLException {
        return List.of();
    }

    @Override
    public Cliente login(String email, String contrasenya) throws SQLException {
        return null;
    }
}
