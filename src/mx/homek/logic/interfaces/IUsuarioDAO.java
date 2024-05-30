package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {

    public abstract int insertarUsuario(Usuario usuario) throws SQLException;

    Usuario consultarUsuarioPorNombre(String nombreUsuario) throws SQLException;

    Usuario consultarUsuarioPorId(int idUsuario) throws SQLException;

    boolean verificarUsuarioExistente(String nombreUsuario, String contraseñaUsuario) throws SQLException;

    String consultarTipoUsuarioPorNombre(String nombreUsuario) throws SQLException;

    int obtenerIDUsuarioPorNombre(String nombreUsuario) throws SQLException;

    boolean existeNombreUsuario(String nombreUsuario) throws SQLException;

    int convertirUsuarioAID(Usuario usuario) throws SQLException;

}
