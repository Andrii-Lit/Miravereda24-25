package com.ieslavereda.es.miravereda.Service;

import com.ieslavereda.es.miravereda.Model.Actor;
import com.ieslavereda.es.miravereda.Model.CompraCliente;
import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Model.Credenciales;
import com.ieslavereda.es.miravereda.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    public Contenido getContenido(int id) throws SQLException {
        return repository.getContenido(id);
    }

    public Contenido addContenido(Contenido contenido) throws SQLException {
        return repository.addContenido(contenido);
    }

    public Contenido updateContenido(Contenido contenido) throws SQLException {
        return repository.updateContenido(contenido);
    }

    public Contenido deleteContenido(int id) throws SQLException {
        return repository.deleteContenido(id);
    }

    public List<Contenido> getAllContenidos() throws SQLException {
        return repository.getAllContenidos();
    }

    public List<Contenido> getCarrito(Credenciales credenciales) throws SQLException {
        return repository.getCarrito(credenciales);
    }

    public int addCarrito(CompraCliente compraCliente) throws SQLException {
        return repository.addCarrito(compraCliente);
    }

    public int deleteCarrito(CompraCliente compraCliente) throws SQLException {
        return repository.deleteCarrito(compraCliente);
    }

    public int pagar(Credenciales credenciales) throws SQLException {
        return repository.pagar(credenciales);
    }

    public void addActor(Actor actor) throws SQLException {
        repository.addActor(actor);
    }

    public boolean estaAlquilada(CompraCliente compraCliente) throws SQLException {
        return repository.estaAlquilada(compraCliente);
    }
}

