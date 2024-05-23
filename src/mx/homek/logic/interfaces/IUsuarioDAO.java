package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {
    public abstract int insertarUsuario(Usuario usuario) throws SQLException;
    public abstract boolean existeNombreUsuario(String nombreUsuario) throws SQLException;
    public abstract int convertirUsuarioAID(Usuario usuario) throws SQLException;
}
