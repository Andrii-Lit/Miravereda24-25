package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Config.MyDataSource;
import com.ieslavereda.es.miravereda.Model.Contenido;
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
                contenido = new Contenido(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("genero"),
                        rs.getString("nombre_dir"),
                        rs.getInt("duracion"),
                        rs.getString("actores_principales"),
                        rs.getDate("fecha_estreno"),
                        rs.getDouble("puntuacion_media"),
                        rs.getString("poster_path"),
                        rs.getDouble("precio")

                );
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
            Contenido contenidoActualizado = null;

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


                    contenidoActualizado = contenido;
                }



            return contenidoActualizado;
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
                Contenido contenido = new Contenido(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("genero"),
                        rs.getString("nombre_dir"),
                        rs.getInt("duracion"),
                        rs.getString("actores_principales"),
                        rs.getDate("fecha_estreno"),
                        rs.getDouble("puntuacion_media"),
                        rs.getString("poster_path"),
                        rs.getDouble("precio")
                );
                contenidos.add(contenido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contenidos;
    }

    @Override
    public void anyadirCarrito(int p_cliente_id, int p_contenido_id) throws SQLException {
        String sql="{call anyadir_al_carrito(?,?)}";

        try(Connection conn = MyDataSource.getMydataSource().getConnection()){
            CallableStatement ps = conn.prepareCall(sql);
            ps.setInt(1, p_cliente_id);
            ps.setInt(2, p_contenido_id);
            ps.execute();

        }catch (SQLException e) {
           throw new SQLException(e.getMessage());
        }

    }


        public void comprar(int clienteId) throws SQLException {
            String sql = "{CALL comprar(?)}";

            try (Connection connection = MyDataSource.getMydataSource().getConnection();
                 CallableStatement stmt = connection.prepareCall(sql)) {

                stmt.setInt(1, clienteId);
                stmt.execute();
            }
        }
    public void votar(int clienteId, int contenidoId, int valor) throws SQLException {
        String sql = "{call votar(?, ?, ?)}";
        try (Connection con = MyDataSource.getMydataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, clienteId);
            cs.setInt(2, contenidoId);
            cs.setInt(3, valor);
            cs.execute();
        }
    }





    public List<Contenido> getAllCarrito(int clienteId) {
        String sql="{ call get_all_carrito(?)}";
        List<Contenido> contenidos = new ArrayList<>();
        try(Connection con=MyDataSource.getMydataSource().getConnection()){
            CallableStatement ps = con.prepareCall(sql);
            ps.setInt(1, clienteId);
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
                        .precio(rs.getDouble("precio"))
                        .build();
                contenidos.add(contenido);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return contenidos;
    }
    public void quitarProducto(int clienteId, int contenidoId) throws SQLException {
        String sql="{call quitar_producto(?, ?)}";
        try (Connection conn = MyDataSource.getMydataSource().getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setInt(2, contenidoId);
            stmt.execute();
        }
    }
}

