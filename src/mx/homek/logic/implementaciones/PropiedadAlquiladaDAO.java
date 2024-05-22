package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPropiedadAlquiladaDAO;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

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
