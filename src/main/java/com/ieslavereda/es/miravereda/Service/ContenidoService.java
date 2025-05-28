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




}

