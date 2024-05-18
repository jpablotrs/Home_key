package mx.logic.implementaciones;

import mx.dataaccess.ConexionBaseDeDatos;
import mx.logic.interfaces.IPropiedadAlquiladaDAO;
import mx.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.Connection;
import java.sql.SQLException;

public class PropiedadAlquiladaDAO implements IPropiedadAlquiladaDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public PropiedadAlquiladaDAO() throws SQLException {
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int alquilarPropiedad(PropiedadAlquilada alquilarPropiedad) throws SQLException {
        return 0;
    }

    @Override
    public void firmarContrato() throws SQLException {

    }
}
