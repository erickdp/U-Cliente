package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDaoJDBC implements CRUD<Cliente> {

    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM cliente WHERE id_cliente=?";

    @Override
    public int create(Cliente dtoObject) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, dtoObject.getNombre());
            stmt.setString(2, dtoObject.getApellido());
            stmt.setString(3, dtoObject.getMail());
            stmt.setString(4, dtoObject.getTlf());
            stmt.setDouble(5, dtoObject.getSaldo());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return row;
    }

    @Override
    public List<Cliente> read() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) { //Se puede indicar el nombre de la columna o el indice de c/u
                clientes.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("email"), rs.getString("telefono"), rs.getDouble("saldo")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return clientes;
    }

    @Override
    public int update(Cliente dtoObject) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, dtoObject.getNombre());
            stmt.setString(2, dtoObject.getApellido());
            stmt.setString(3, dtoObject.getMail());
            stmt.setString(4, dtoObject.getTlf());
            stmt.setDouble(5, dtoObject.getSaldo());
            stmt.setInt(6, dtoObject.getId_cliente());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return row;
    }

    @Override
    public int delete(Cliente dtoObject) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, dtoObject.getId_cliente());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return row;
    }

    @Override
    public Cliente readByIdentifier(Cliente dtoObject) {
        Connection conn = null;
        Cliente clienteEncontrado = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, dtoObject.getId_cliente());
            rs = stmt.executeQuery();
//            rs.absolute(1); Nos posicionamos en el primer registro de donde se encuentre el ID segun UDEMY pero no usar este metodo pues se cae
            while (rs.next()) {
                clienteEncontrado = new Cliente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return clienteEncontrado;
    }

}
