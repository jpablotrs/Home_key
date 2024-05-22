package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.ICompraPropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;

import java.sql.Connection;
import java.sql.SQLException;

public class CompraPropiedadDAO implements ICompraPropiedadDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public CompraPropiedadDAO() throws SQLException{
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int insertarCompraPropiedad(CompraPropiedad compraPropiedad) throws SQLException {
        return 0;
    }

    @Override
    public void ofertarPropiedad() throws SQLException {

    }
}
