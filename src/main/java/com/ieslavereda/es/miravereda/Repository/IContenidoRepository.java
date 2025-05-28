package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Contenido;

import java.sql.SQLException;
import java.util.List;

public interface IContenidoRepository {


    Contenido getContenido(int id) throws SQLException;

    Contenido addContenido(Contenido contenido) throws SQLException;

    Contenido updateContenido(Contenido contenido) throws SQLException;

    Contenido deleteContenido(int id) throws SQLException;

    List<Contenido> getAllContenidos() throws SQLException;


}
