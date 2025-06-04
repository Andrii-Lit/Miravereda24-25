package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Model.Contenido;

import java.sql.SQLException;
import java.util.List;

public interface IContenidoRepository {

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    Contenido getContenido(int id) throws SQLException;

    /**
     *
     * @param contenido
     * @return
     * @throws SQLException
     */
    Contenido addContenido(Contenido contenido) throws SQLException;

    /**
     *
     * @param contenido
     * @return
     * @throws SQLException
     */
    Contenido updateContenido(Contenido contenido) throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    Contenido deleteContenido(int id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    List<Contenido> getAllContenidos() throws SQLException;

    /**
     *
     * @param p_cliente_id
     * @param p_contenido_id
     * @throws SQLException
     */
    void anyadirCarrito(int p_cliente_id,int p_contenido_id) throws SQLException;



}
