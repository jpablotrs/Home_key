package mx.homek.logic.interfaces;




import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.SQLException;

public interface iUsuarioDAO {
    public abstract int insertarUsuario(Usuario usuario) throws SQLException;
    public abstract Usuario consultarUsuarioPorNombre(String nombreUsuario) throws SQLException;
    public abstract Usuario consultarUsuarioPorId(int idUsuario) throws SQLException;
    public abstract boolean verificarUsuarioExistente(String nombreUsuario, String contrasenia) throws SQLException;
    public abstract String consultarTipoUsuarioPorNombre(String nombreUsuario) throws SQLException;
    public abstract int obtenerIDUsuarioPorNombre (String nombreUsuario) throws SQLException;
    public abstract boolean existeNombreUsuario(String nombreUsuario) throws SQLException;
}
