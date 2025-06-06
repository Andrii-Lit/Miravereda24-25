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
     * Devuelve un cliente dado su email.
     * @param email Correo del cliente a buscar.
     * @return Cliente encontrado o null si no existe.
     * @throws SQLException
     */
    @Override
    public Cliente getClientePorEmail(String email) throws SQLException {
        return callClientePorEmail(email);
    }

    /**
     * Obtiene un cliente por su ID.
     * @param id ID del cliente.
     * @return Cliente si existe, null si no.
     * @throws SQLException
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
     * @param cliente Cliente a insertar.
     * @return Cliente insertado con ID generado, o null si falla.
     * @throws SQLException
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

            // Obtener el ID generado automáticamente por la base de datos
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
     * Actualiza los datos de un cliente existente.
     * @param cliente Cliente con datos modificados.
     * @return Cliente actualizado.
     * @throws SQLException
     */
    @Override
    public Cliente updateCliente(Cliente cliente) throws SQLException {
        Cliente cliente1 = getCliente(cliente.getId());
        String sql = "UPDATE cliente SET contrasenya = ?, nombre = ?, apellidos = ?, domicilio = ?, cod_postal = ?, email = ?, fecha_nac = ?, num_tarjeta = ? WHERE id = ?";
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
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar.
     * @return Cliente eliminado.
     * @throws SQLException
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
     * Devuelve todos los clientes de la base de datos.
     * @return Lista de clientes.
     * @throws SQLException
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
     * Intenta iniciar sesión con el email y contraseña proporcionados.
     * @param email Email del cliente.
     * @param contrasenya Contraseña del cliente.
     * @return Cliente si los datos son válidos, null si no.
     * @throws SQLException
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
            return callClientePorEmail(email);
        }
    }

    /**
     * Ejecuta el procedimiento almacenado que obtiene un cliente por su email.
     * @param email Email del cliente.
     * @return Cliente si existe, null si no.
     * @throws SQLException
     */
    private Cliente callClientePorEmail(String email) throws SQLException {
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