package mx.logic.implementaciones;

import mx.dataaccess.ConexionBaseDeDatos;
import mx.logic.interfaces.ICompraPropiedadDAO;
import mx.logic.objetoDeTransferencia.CompraPropiedad;

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
