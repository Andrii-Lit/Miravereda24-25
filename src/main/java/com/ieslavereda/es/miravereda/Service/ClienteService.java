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


    /**
     *
     * @param id
     * @return repository.getCliente(id)
     * @throws SQLException
     */
    public Cliente getCliente(int id) throws SQLException {
        return repository.getCliente(id);
    }

    /**
     *
     * @param cliente
     * @return repository.addCliente(cliente)
     * @throws SQLException
     */
    public Cliente addCliente(Cliente cliente) throws SQLException {
        return repository.addCliente(cliente);
    }


    /**
     *
     * @param cliente
     * @return repository.updateCliente(cliente)
     * @throws SQLException
     */
    public Cliente updateCliente(Cliente cliente) throws SQLException {
        return repository.updateCliente(cliente);
    }

    /**
     *
     * @param id
     * @return repository.deleteCliente(id)
     * @throws SQLException
     */
    public Cliente deleteCliente(int id) throws SQLException {
        return repository.deleteCliente(id);
    }

    /**
     *
     * @return repository.getAllClientes()
     * @throws SQLException
     */
    public List<Cliente> getAllClientes() throws SQLException {
        return repository.getAllClientes();
    }

    /**
     *
     * @param email
     * @param contrasenya
     * @return repository.login(email, contrasenya)
     * @throws SQLException
     */
    public Cliente login(String email, String contrasenya) throws SQLException {
        return repository.login(email, contrasenya);
    }

}
