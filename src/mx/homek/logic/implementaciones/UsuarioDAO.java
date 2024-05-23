package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IUsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IUsuarioDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public UsuarioDAO() throws SQLException {
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int insertarUsuario(Usuario usuario) throws SQLException {
        String consultaSQL = "insert into Usuario (nombreUsuario, tipoUsuario, contraseña) values (?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, usuario.getNombreUsuario());
        sentencia.setString(2, usuario.getTipoUsuario());
        sentencia.setString(3, usuario.getContrasena());
        return sentencia.executeUpdate();
    }

    @Override
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        String consultaSQL = "Select idUsuario from usuario where nombreUsuario = ?";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, nombreUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();
        if (resultadoConsulta.next()){
            return true;
        }
        return false;
    }

    @Override
    public int convertirUsuarioAID(Usuario usuario) throws SQLException {
        String consultaSQL = "Select idUsuario from usuario where nombreUsuario = ?";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, usuario.getNombreUsuario());
        ResultSet resultadoConsulta = sentencia.executeQuery();
        int idUsuario = -1;
        if (resultadoConsulta.next()) {
            idUsuario = resultadoConsulta.getInt("idUsuario");
        }
        return idUsuario;
    }
}
