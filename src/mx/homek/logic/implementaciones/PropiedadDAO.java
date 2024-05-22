package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropiedadDAO implements IPropiedadDAO {
    private ConexionBaseDeDatos administradorBaseDeDatos;
    private Connection conexion;

    public PropiedadDAO() throws SQLException {
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int consultarIDPropiedadPorClaveCatastral(String claveCatastral) throws SQLException {
        String consultaSQL = "select idPropiedad from propiedad where claveCatastral = ?";
        PreparedStatement consultarIdPropiedad = conexion.prepareStatement(consultaSQL);
        consultarIdPropiedad.setString(1, claveCatastral);
        ResultSet resultadoConsulta = consultarIdPropiedad.executeQuery();
        if(resultadoConsulta.next())
            return resultadoConsulta.getInt("idPropiedad");
        return -1;
    }

    @Override
    public int agregarPropiedad(Propiedad propiedad) throws SQLException {
        return 0;
    }

    @Override
    public Propiedad consultarPropiedad(String claveCatastral) throws SQLException {
        return null;
    }

    @Override
    public int eliminarPropiedad(Propiedad propiedad) throws SQLException {
        return 0;
    }

    @Override
    public int modificarPropiedad(Propiedad propiedad) throws SQLException {
        return 0;
    }

    @Override
    public int guardarHistorialDeBusqueta(Propiedad propiedad) throws SQLException {
        return 0;
    }
}
