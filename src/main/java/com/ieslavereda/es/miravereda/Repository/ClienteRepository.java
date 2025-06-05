package com.ieslavereda.es.miravereda.Repository;

import com.ieslavereda.es.miravereda.Config.MyDataSource;
import com.ieslavereda.es.miravereda.Model.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository implements IClienteRepository {

    /**
     * Obtiene un cliente de la base de datos a partir de su ID.
     *
     * @param id Identificador del cliente a buscar.
     * @return El objeto Cliente con el ID proporcionado, o null si no existe.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public Cliente getCliente(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Cliente.builder()
                        .id(rs.getInt("id"))
                        .contrasenya(rs.getString("contrasenya"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .domicilio(rs.getString("domicilio"))
                        .cod_postal(rs.getString("cod_postal"))
                        .email(rs.getString("email"))
                        .fecha_nac(rs.getDate("fecha_nac"))
                        .num_tarjeta(rs.getString("num_tarjeta"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Añade un nuevo cliente a la base de datos.
     *
     * @param cliente Objeto Cliente con los datos a insertar.
     * @return El Cliente insertado con el ID generado, o null si falla la inserción.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public Cliente addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (contrasenya, nombre, apellidos, domicilio, cod_postal, email, fecha_nac, num_tarjeta) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getMydataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getContrasenya());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDomicilio());
            ps.setString(5, cliente.getCod_postal());
            ps.setString(6, cliente.getEmail());
            ps.setDate(7, cliente.getFecha_nac());
            ps.setString(8, cliente.getNum_tarjeta());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int nuevoId = generatedKeys.getInt(1);
                    return Cliente.builder()
                            .id(nuevoId)
                            .contrasenya(cliente.getContrasenya())
                            .nombre(cliente.getNombre())
                            .apellidos(cliente.getApellidos())
                            .domicilio(cliente.getDomicilio())
                            .cod_postal(cliente.getCod_postal())
                            .email(cliente.getEmail())
                            .fecha_nac(cliente.getFecha_nac())
                            .num_tarjeta(cliente.getNum_tarjeta())
                            .build();
                }
            }
            return null;
        }
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     *
     * @param cliente Objeto Cliente con los datos actualizados.
     * @return El Cliente actualizado.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public Cliente updateCliente(Cliente cliente) throws SQLException {
        Cliente cliente1 = getCliente(cliente.getId());
        String sql = "UPDATE cliente SET contrasenya = ?, nombre = ?, " +
                "apellidos = ?, domicilio = ?, " +
                "cod_postal = ?, email = ?, " +
                "fecha_nac = ?, num_tarjeta = ? " +
                "WHERE id = ?";
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getContrasenya());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDomicilio());
            ps.setString(5, cliente.getCod_postal());
            ps.setString(6, cliente.getEmail());
            ps.setDate(7, cliente.getFecha_nac());
            ps.setString(8, cliente.getNum_tarjeta());
            ps.setInt(9, cliente.getId());

            ps.executeUpdate();
            cliente1 = cliente;
        }
        return cliente1;
    }

    /**
     * Elimina un cliente de la base de datos a partir de su ID.
     *
     * @param id Identificador del cliente a eliminar.
     * @return El Cliente eliminado, o null si no existe.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public Cliente deleteCliente(int id) throws SQLException {
        Cliente cliente = getCliente(id);
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return cliente;
    }

    /**
     * Obtiene la lista de todos los clientes almacenados en la base de datos.
     *
     * @return Lista de objetos Cliente.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public List<Cliente> getAllClientes() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection con = MyDataSource.getMydataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = Cliente.builder()
                        .id(rs.getInt("id"))
                        .contrasenya(rs.getString("contrasenya"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .domicilio(rs.getString("domicilio"))
                        .cod_postal(rs.getString("cod_postal"))
                        .email(rs.getString("email"))
                        .fecha_nac(rs.getDate("fecha_nac"))
                        .num_tarjeta(rs.getString("num_tarjeta"))
                        .build();
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return clientes;
    }

    /**
     * Realiza la autenticación de un cliente con email y contraseña.
     *
     * @param email      Email del cliente.
     * @param contrasenya Contraseña del cliente.
     * @return El Cliente autenticado si las credenciales son correctas, o null si no lo son.
     * @throws SQLException En caso de error con la base de datos.
     */
    @Override
    public Cliente login(String email, String contrasenya) throws SQLException {
        String sql = "{call iniciar_sesion(?,?,?)}";
        try (Connection con = MyDataSource.getMydataSource().getConnection()) {
            CallableStatement cs = con.prepareCall(sql);
            cs.registerOutParameter(1, Types.BOOLEAN);
            cs.setString(2, email);
            cs.setString(3, contrasenya);
            cs.execute();
            boolean loginOk = cs.getBoolean(1);
            if (!loginOk) {
                return null;
            }
            return getClientePorEmail(email);
        }
    }

    /**
     * Obtiene un cliente a partir de su email mediante un procedimiento almacenado.
     *
     * @param email Email del cliente a buscar.
     * @return El Cliente correspondiente al email, o null si no existe.
     * @throws SQLException En caso de error con la base de datos.
     */
    private Cliente getClientePorEmail(String email) throws SQLException {
        String sqlGetCliente = "{CALL get_cliente_por_email(?)}";

        try (Connection conn = MyDataSource.getMydataSource().getConnection();
             CallableStatement csCliente = conn.prepareCall(sqlGetCliente)) {

            csCliente.setString(1, email);

            ResultSet rs = csCliente.executeQuery();
            if (rs.next()) {
                return Cliente.builder()
                        .id(rs.getInt("id"))
                        .contrasenya(rs.getString("contrasenya"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .domicilio(rs.getString("domicilio"))
                        .cod_postal(rs.getString("cod_postal"))
                        .email(rs.getString("email"))
                        .fecha_nac(rs.getDate("fecha_nac"))
                        .num_tarjeta(rs.getString("num_tarjeta"))
                        .build();
            } else {
                return null;
            }
        }
    }
}