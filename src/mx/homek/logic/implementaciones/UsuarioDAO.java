package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.interfaces.IUsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO implements IUsuarioDAO {
    private Connection conexion;
    private ConexionBaseDeDatos administradorBaseDatos;
    ValidadorDeReglas validadorDeReglas;

    public UsuarioDAO() {
        administradorBaseDatos = new ConexionBaseDeDatos();
        validadorDeReglas = new ValidadorDeReglas();
    }

    @Override
    public int insertarUsuario(Usuario usuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Insert into Usuario (nombreUsuario, contraseña, tipoUsuario) values (?, ?, ?)";
        validadorDeReglas.hashearContraseña(usuario.getContrasena());
        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);

        sentencia.setString(1, usuario.getNombreUsuario());
        sentencia.setString(2, validadorDeReglas.hashearContraseña(usuario.getContrasena()));
        sentencia.setString(3, usuario.getTipoUsuario());

        int resultado = sentencia.executeUpdate();
        conexion.close();

        return resultado;
    }

    @Override
    public Usuario consultarUsuarioPorNombre(String nombreUsuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Select idUsuario, nombreUsuario, contraseña, tipoUsuario from Usuario where nombreUsuario = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setString(1, nombreUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        Usuario usuarioConsultado = new Usuario();

        if(resultadoConsulta.next()) {
            String nombreDeUsuario = resultadoConsulta.getString("nombreUsuario");
            String contraseña = resultadoConsulta.getString("contraseña");
            String tipoUsuario = resultadoConsulta.getString("tipoUsuario");
            int id = resultadoConsulta.getInt("idUsuario");

            usuarioConsultado.setId(id);
            usuarioConsultado.setNombreUsuario(nombreDeUsuario);
            usuarioConsultado.setContrasena(contraseña);
            usuarioConsultado.setTipoUsuario(tipoUsuario);
        }
        conexion.close();
        return usuarioConsultado;
    }

    @Override
    public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Select nombreUsuario, contraseña, tipoUsuario from Usuario where idUsuario = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setInt(1, idUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        Usuario usuarioConsultado = new Usuario();

        if(resultadoConsulta.next()) {
            String nombreDeUsuario = resultadoConsulta.getString("nombreUsuario");
            String contraseña = resultadoConsulta.getString("contraseña");
            String tipoUsuario = resultadoConsulta.getString("tipoUsuario");

            usuarioConsultado.setNombreUsuario(nombreDeUsuario);
            usuarioConsultado.setContrasena(contraseña);
            usuarioConsultado.setTipoUsuario(tipoUsuario);
        }
        conexion.close();
        return usuarioConsultado;
    }

    @Override
    public boolean verificarUsuarioExistente(String nombreUsuario, String contraseñaUsuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        if(conexion != null){
            String consultaSql = "Select nombreUsuario, contraseña, tipoUsuario from Usuario where nombreUsuario = ? and contraseña = ?";
            PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
            sentencia.setString(1, nombreUsuario);
            sentencia.setString(2, validadorDeReglas.hashearContraseña(contraseñaUsuario));
            ResultSet resultadoConsulta = sentencia.executeQuery();

            boolean existeUsuario = false;
            if (resultadoConsulta.next()) {
                existeUsuario = true;
            }

            conexion.close();
            return existeUsuario;
        }
        else {
            return false;
        }
    }

    @Override
    public String consultarTipoUsuarioPorNombre(String nombreUsuario) throws SQLException{
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Select tipoUsuario from Usuario where nombreUsuario = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setString(1, nombreUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        String tipoUsuario = null;

        if(resultadoConsulta.next()) {
            tipoUsuario = resultadoConsulta.getString("tipoUsuario");
        }
        conexion.close();
        return tipoUsuario;
    }

    @Override
    public int obtenerIDUsuarioPorNombre(String nombreUsuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Select idUsuario from Usuario where nombreUsuario = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setString(1, nombreUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        int idUsuario = -1;

        if(resultadoConsulta.next()) {
            idUsuario = resultadoConsulta.getInt("idUsuario");
        }
        conexion.close();
        return idUsuario;
    }

    @Override
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "Select idUsuario from Usuario where nombreUsuario = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setString(1, nombreUsuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();
        boolean existencia = false;
        if(resultadoConsulta.next()){
            existencia = true;
        }
        conexion.close();
        return existencia;
    }

    @Override
    public int convertirUsuarioAID(Usuario usuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSQL = "Select idUsuario from usuario where nombreUsuario = ?";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, usuario.getNombreUsuario());
        ResultSet resultadoConsulta = sentencia.executeQuery();
        int idUsuario = -1;
        if (resultadoConsulta.next()) {
            idUsuario = resultadoConsulta.getInt("idUsuario");
        }
        conexion.close();
        return idUsuario;
    }

    @Override
    public int modificarUsuario(Usuario usuario) throws SQLException {
        conexion = administradorBaseDatos.obtenerConexion();
        String consultaSql = "UPDATE usuario SET contraseña = ? WHERE idUsuario = ?;";
        validadorDeReglas.hashearContraseña(usuario.getContrasena());
        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setString(1, validadorDeReglas.hashearContraseña(usuario.getContrasena()));
        sentencia.setInt(2,obtenerIDUsuarioPorNombre(usuario.getNombreUsuario()));
        int resultado = sentencia.executeUpdate();
        conexion.close();

        return resultado;
    }
}
