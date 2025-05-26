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

    /**
     * Metodo que sirve para llamar al repositorio para dar respuesta a esta peticion de eliminar al cliente
     * @param id número identificador que nos servira para eliminar al cliente correspondiente
     * @return devolvera la respuesta del metodo que está en el repositorio
     * @throws SQLException exepcion que se enviara en el caso de que la id correspondiente no este dentro de la base de datos
     */
    public Cliente deleteUsuario(int id) throws SQLException {
        return repository.deleteCliente(id);
    }


    public List<Cliente> getAllUsuarios() throws SQLException {
        return repository.getAllClientes();
    }

}
