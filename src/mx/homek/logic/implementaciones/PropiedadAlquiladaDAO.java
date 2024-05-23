package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPropiedadAlquiladaDAO;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String consultaSql = "insert into alquilarPropiedad (fechaAlquiler, fechaLlegada, fechaSalida, Propiedad_idPropiedad, Cliente_idCliente) " +
                "values (?, ?, ?, ?, ?)";

        java.sql.Date fechaAlquiler = new java.sql.Date(alquilarPropiedad.getFechaAlquiler().getTime());
        java.sql.Date fechaLlegada = new java.sql.Date(alquilarPropiedad.getFechaEntrada().getTime());
        java.sql.Date fechaSalida = new java.sql.Date(alquilarPropiedad.getFechaSalida().getTime());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        int idPropiedad = gestorPropiedad.consultarIDPropiedadPorClaveCatastral(alquilarPropiedad.getPropiedad().getClaveCatastral());

        ClienteDAO gestorCliente = new ClienteDAO();
        int idCliente = gestorCliente.consultarIDClientePorCorreo(alquilarPropiedad.getCliente().getCorreo());

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setDate(1,fechaAlquiler);
        sentencia.setDate(2, fechaLlegada);
        sentencia.setDate(3, fechaSalida);
        sentencia.setInt(4, idPropiedad);
        sentencia.setInt(5, idCliente);

        return sentencia.executeUpdate();
    }
}
