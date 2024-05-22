package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPerfilClienteDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class PerfilCliente implements IPerfilClienteDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public PerfilCliente() throws SQLException{
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }
    @Override
    public int insertarPerfilPreferenciasDelCliente(PerfilCliente preferenciasCliente) throws SQLException {
        return 0;
    }
}
