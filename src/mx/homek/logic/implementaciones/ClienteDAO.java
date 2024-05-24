package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IClienteDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.PerfilCliente;
import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
        java.sql.Date fecha = new java.sql.Date(cliente.getFechaNacimiento().getTime());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String consultaSQL = "insert into cliente(nombre,apellidoPaterno,apellidoMaterno,estadoCivil,fechaNacimiento,sexo,correo,telefono,Usuario_idUsuario) values (?,?,?,?,?,?,?,?,?)";
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

    @Override
    public Cliente consultarClientePorIdUsuario(int idUsuario) throws SQLException {
        String consultaSQL = "select * from cliente where Usuario_idUsuario = ?";

        PreparedStatement consultarId = conexion.prepareStatement(consultaSQL);
        consultarId.setInt(1, idUsuario);

        ResultSet resultado = consultarId.executeQuery();

        Cliente clienteConsultado = new Cliente();
        if(resultado.next()) {
            String nombre = resultado.getString("nombre");
            String apellidoPaterno = resultado.getString("apellidoPaterno");
            String apellidoMaterno = resultado.getString("apellidoMaterno");
            String estadoCivil = resultado.getString("estadoCivil");
            Date fechaNacimiento = resultado.getDate("fechaNacimiento");
            String sexo = resultado.getString("sexo");
            String correo = resultado.getString("correo");
            String telefono = resultado.getString("tel├®fono");
            int idUsuarioConsultado = resultado.getInt("Usuario_idUsuario");
            int idPerfilCliente = resultado.getInt("preferenciasCliente_IdPreferenciasCliente");

            UsuarioDAO gestorUsuario = new UsuarioDAO();
            Usuario usuario = gestorUsuario.consultarUsuarioPorId(idUsuarioConsultado);

            PerfilClienteDAO gestorPerfil = new PerfilClienteDAO();
            PerfilCliente perfil = gestorPerfil.consultarPerfilPorId(idPerfilCliente);

            clienteConsultado.setNombre(nombre);
            clienteConsultado.setApellidoPaterno(apellidoPaterno);
            clienteConsultado.setApellidoMaterno(apellidoMaterno);
            clienteConsultado.setEstadoCivil(estadoCivil);
            clienteConsultado.setFechaNacimiento(fechaNacimiento);
            clienteConsultado.setSexo(sexo);
            clienteConsultado.setCorreo(correo);
            clienteConsultado.setTelefono(telefono);
            clienteConsultado.setUsuario(usuario);
        }

        return clienteConsultado;
    }
}
