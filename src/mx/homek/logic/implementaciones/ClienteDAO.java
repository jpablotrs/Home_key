package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IClienteDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.sql.*;

public class ClienteDAO implements IClienteDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public ClienteDAO() throws SQLException{
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int consultarIDClientePorCorreo(String correo) throws SQLException {
        String consultaSQL = "select idCliente from cliente where correo = ?";
        PreparedStatement consultarId = conexion.prepareStatement(consultaSQL);
        consultarId.setString(1, correo);
        ResultSet resultadoConsulta = consultarId.executeQuery();
        if(resultadoConsulta.next())
            return resultadoConsulta.getInt("idCliente");
        return -1;
    }

    @Override
    public int insertarCliente(Cliente cliente) throws SQLException {
        java.sql.Date fecha = (Date) cliente.getFechaNacimiento();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PerfilClienteDAO perfilClienteDAO = new PerfilClienteDAO();
        String consultaSQL = "insert into cliente(nombre,apellidoPaterno,apellidoMaterno,estadoCivil,fechaNacimiento,sexo,correo,telefono,Usuario_idUsuario,preferenciasCliente_idPreferenciasCliente) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, cliente.getNombre());
        sentencia.setString(2, cliente.getApellidoPaterno());
        sentencia.setString(3, cliente.getApellidoMaterno());
        sentencia.setString(4, cliente.getEstadoCivil());
        sentencia.setDate(5, fecha);
        sentencia.setString(6, cliente.getSexo());
        sentencia.setString(7, cliente.getCorreo());
        sentencia.setString(8, cliente.getTelefono());
        sentencia.setInt(9, usuarioDAO.convertirUsuarioAID(cliente.getUsuario()));
        sentencia.setInt(10, perfilClienteDAO.convertirPerfilPreferenciasAID(cliente.getPerfilCliente()));
        return  sentencia.executeUpdate();
    }

    @Override
    public int modificarCliente(Cliente cliente) throws SQLException {
        return 0;
    }

    @Override
    public int eliminarCliente(Cliente cliente) throws SQLException {
        return 0;
    }
}
