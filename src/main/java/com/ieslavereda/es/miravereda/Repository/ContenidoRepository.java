package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Config.MyDataSource;
import com.ieslavereda.es.miravereda.Model.Actor;
import com.ieslavereda.es.miravereda.Model.CompraCliente;
import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Model.Credenciales;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ContenidoRepository implements IContenidoRepository {
    @Override
    public Contenido getContenido(int id) throws SQLException {
        Contenido contenido = null;
        String sql = "SELECT * FROM contenido WHERE id = ?";
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contenido = Contenido.builder()
                        .id(rs.getInt("id"))
                        .duracion(rs.getInt("duracion"))
                        .titulo(rs.getString("titulo"))
                        .descripcion(rs.getString("descripcion"))
                        .genero(rs.getString("genero"))
                        .nombre_dir(rs.getString("nombre_dir"))
                        .actores_principales(rs.getString("actores_principales"))
                        .poster_path(rs.getString("poster_path"))
                        .fecha_estreno(rs.getDate("fecha_estreno"))
                        .puntuacion_media(rs.getDouble("puntuacion_media"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contenido;
    }


    @Override
    public Contenido addContenido(Contenido contenido) throws SQLException {
        String sql = "INSERT INTO contenido (id, duracion, titulo, descripcion, genero, nombre_dir, actores_principales, poster_path, fecha_estreno, puntuacion_media) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, contenido.getId());
            ps.setInt(2, contenido.getDuracion());
            ps.setString(3, contenido.getTitulo());
            ps.setString(4, contenido.getDescripcion());
            ps.setString(5, contenido.getGenero());
            ps.setString(6, contenido.getNombre_dir());
            ps.setString(7, contenido.getActores_principales());
            ps.setString(8, contenido.getPoster_path());
            ps.setDate(9, contenido.getFecha_estreno());
            ps.setDouble(10, contenido.getPuntuacion_media());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating contenido failed, no rows affected.");
            }

            return contenido;
        }
    }


    @Override
    public Contenido updateContenido(Contenido contenido) throws SQLException {
            String sql = "UPDATE contenido SET duracion = ?, titulo = ?, descripcion = ?, genero = ?, nombre_dir = ?,actores_principales = ?, poster_path = ?,fecha_estreno = ?, puntuacion_media = ? where id = ?";
//            Contenido contenidoActualizado = null;

            try (Connection conn = MyDataSource.getMydataSource().getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, contenido.getDuracion());
                ps.setString(2, contenido.getTitulo());
                ps.setString(3, contenido.getDescripcion());
                ps.setString(4, contenido.getGenero());
                ps.setString(5, contenido.getNombre_dir());
                ps.setString(6, contenido.getActores_principales());
                ps.setString(7, contenido.getPoster_path());
                ps.setDate(8, contenido.getFecha_estreno());
                ps.setDouble(9, contenido.getPuntuacion_media());
                ps.setInt(10, contenido.getId());

                ps.executeUpdate();

//                if (updatedRows > 0) {
//
//                    contenidoActualizado = contenido;
//                }

            }

            return contenido;
        }




        @Override
    public Contenido deleteContenido(int id) throws SQLException {
        Contenido contenido=getContenido(id);
        String sql = "DELETE FROM contenido WHERE id = ?";
        try (Connection conn = MyDataSource.getMydataSource().getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return contenido;
    }

    @Override
    public List<Contenido> getAllContenidos() throws SQLException {
        String sql = "SELECT * FROM contenido";
        List<Contenido> contenidos = new ArrayList<>();
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenido contenido = Contenido.builder()
                        .id(rs.getInt("id"))
                        .duracion(rs.getInt("duracion"))
                        .titulo(rs.getString("titulo"))
                        .descripcion(rs.getString("descripcion"))
                        .genero(rs.getString("genero"))
                        .nombre_dir(rs.getString("nombre_dir"))
                        .actores_principales(rs.getString("actores_principales"))
                        .poster_path(rs.getString("poster_path"))
                        .fecha_estreno(rs.getDate("fecha_estreno"))
                        .puntuacion_media(rs.getDouble("puntuacion_media"))
                        .build();
                contenidos.add(contenido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contenidos;
    }


    @Override
    public List<Contenido> getCarrito(Credenciales credenciales) throws SQLException {
        return List.of();
    }

    @Override
    public int addCarrito(CompraCliente compraCliente) throws SQLException {
        return 0;
    }

    @Override
    public int deleteCarrito(CompraCliente compraCliente) throws SQLException {
        return 0;
    }

    @Override
    public int pagar(Credenciales credenciales) throws SQLException {
        return 0;
    }

    @Override
    public void addActor(Actor actor) throws SQLException {

    }

    @Override
    public boolean estaAlquilada(CompraCliente compraCliente) throws SQLException {
        return false;
    }
}

