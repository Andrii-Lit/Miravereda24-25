package com.ieslavereda.es.miravereda.Service;

import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Contenido getContenido(int id) throws SQLException {
        return repository.getContenido(id);
    }

    /**
     *
     * @param contenido
     * @return
     * @throws SQLException
     */
    public Contenido addContenido(Contenido contenido) throws SQLException {
        return repository.addContenido(contenido);
    }

    /**
     *
     * @param contenido
     * @return
     * @throws SQLException
     */
    public Contenido updateContenido(Contenido contenido) throws SQLException {
        return repository.updateContenido(contenido);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Contenido deleteContenido(int id) throws SQLException {
        return repository.deleteContenido(id);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Contenido> getAllContenidos() throws SQLException {
        return repository.getAllContenidos();
    }

    /**
     *
     * @param clienteId
     * @param contenidoId
     * @throws SQLException
     */
    public void anyadirCarrito(int clienteId, int contenidoId) throws SQLException {
        repository.anyadirCarrito(clienteId, contenidoId);
    }

    /**
     *
     * @param clienteId
     * @return repository.getAllCarrito(clienteId)
     * @throws SQLException
     */
    public List<Contenido> getAllCarrito(int clienteId) throws SQLException {
        return repository.getAllCarrito(clienteId);
    }

    /**
     *
     * @param clienteId
     * @throws SQLException
     */
    public void comprar(int clienteId) throws SQLException {
        repository.comprar(clienteId);
    }

    /**
     *
     * @param clienteId
     * @param contenidoId
     * @param valor
     * @throws SQLException
     */
    public void votar(int clienteId, int contenidoId, int valor) throws SQLException {
        repository.votar(clienteId, contenidoId, valor);
    }

    /**
     *
     * @param clienteId
     * @param contenidoId
     * @throws SQLException
     */
    public void quitarProducto(int clienteId, int contenidoId) throws SQLException {
        repository.quitarProducto(clienteId, contenidoId);
    }

}





