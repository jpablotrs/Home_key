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
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String consulta = "insert into Cliente (nombre,apellidoPaterno,apellidoMaterno,estadoCivil,fechaNacimiento,sexo,correo,telefono,Usuario_idUsuario) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement insercion = conexion.prepareStatement(consulta);
        insercion.setString(1,cliente.getNombre());
        insercion.setString(2,cliente.getApellidoPaterno());
        insercion.setString(3,cliente.getApellidoMaterno());
        insercion.setString(4,cliente.getEstadoCivil());
        insercion.setDate(5,new java.sql.Date(cliente.getFechaNacimiento().getTime()));
        insercion.setString(6,cliente.getSexo());
        insercion.setString(7,cliente.getCorreo());
        insercion.setString(8,cliente.getTelefono());
        insercion.setInt(9,usuarioDAO.obtenerIDUsuarioPorNombre(cliente.getUsuario().getNombreUsuario()));
        return insercion.executeUpdate();
    }

    @Override
    public int modificarCliente(Cliente cliente) throws SQLException {
        String consulta = "UPDATE cliente" +
                "SET" +
                "    nombre = ?," +
                "    apellidoPaterno = ?," +
                "    apellidoMaterno = ?," +
                "    estadoCivil = ?," +
                "    fechaNacimiento = ?," +
                "    sexo = ?," +
                "    correo = ?," +
                "    telefono = ?," +
                "WHERE" +
                "    idCliente = ?";
        PreparedStatement modificar = conexion.prepareStatement(consulta);
        modificar.setString(1,cliente.getNombre());
        modificar.setString(2,cliente.getApellidoPaterno());
        modificar.setString(3,cliente.getApellidoMaterno());
        modificar.setString(4,cliente.getEstadoCivil());
        modificar.setDate(5,new java.sql.Date(cliente.getFechaNacimiento().getTime()));
        modificar.setString(6,cliente.getSexo());
        modificar.setString(7,cliente.getCorreo());
        modificar.setString(8,cliente.getTelefono());
        modificar.setInt(9,consultarIDClientePorCorreo(cliente.getCorreo()));
        return modificar.executeUpdate();
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
            String telefono = resultado.getString("telefono");
            int idUsuarioConsultado = resultado.getInt("Usuario_idUsuario");
            UsuarioDAO gestorUsuario = new UsuarioDAO();
            Usuario usuario = gestorUsuario.consultarUsuarioPorId(idUsuarioConsultado);

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
