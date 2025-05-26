package com.ieslavereda.es.miravereda.Service;

import com.ieslavereda.es.miravereda.Model.Cliente;
import com.ieslavereda.es.miravereda.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;



    public Cliente getCliente(int id) throws SQLException {
        return repository.getCliente(id);
    }



    public Cliente addCliente(Cliente cliente) throws SQLException {
        return repository.addCliente(cliente);
    }



    public boolean updateCliente(Cliente cliente) throws SQLException {
        return repository.updateCliente(cliente);
    }


    public Cliente deleteCliente(int id) throws SQLException {
        return repository.deleteCliente(id);
    }


    public List<Cliente> getAllClientes() throws SQLException {
        return repository.getAllClientes();
    }
    public Cliente login(String email, String contrasenya) throws SQLException {
        return repository.login(email, contrasenya);
    }

}
