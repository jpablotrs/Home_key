package mx.logic.implementaciones;

import mx.dataaccess.ConexionBaseDeDatos;
import mx.logic.interfaces.IPerfilClienteDAO;

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
