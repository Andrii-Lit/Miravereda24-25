package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Actor;
import com.ieslavereda.es.miravereda.Model.CompraCliente;
import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Model.Credenciales;

import java.sql.SQLException;
import java.util.List;

public interface IContenidoRepository {


    Contenido getContenido(int id) throws SQLException;

    Contenido addContenido(Contenido contenido) throws SQLException;

    Contenido updateContenido(Contenido contenido) throws SQLException;

    Contenido deleteContenido(int id) throws SQLException;

    List<Contenido> getAllContenidos() throws SQLException;

    List<Contenido> getCarrito(Credenciales credenciales) throws SQLException;

    int addCarrito(CompraCliente compraCliente) throws SQLException;

    int deleteCarrito(CompraCliente compraCliente) throws SQLException;

    int pagar(Credenciales credenciales) throws SQLException;

    void addActor(Actor actor) throws SQLException;

    boolean estaAlquilada(CompraCliente compraCliente) throws SQLException;
}
