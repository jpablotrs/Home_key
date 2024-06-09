package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPropiedadAlquiladaDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

    @Override
    public ArrayList<PropiedadAlquilada> consultarPropiedadesAlquiladasPorPropiedad(Propiedad propiedad) throws SQLException {
        String consltaSql = "select * from alquilarPropiedad where Propiedad_idPropiedad = ?";

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        int idPropiedad = gestorPropiedad.consultarIDPropiedadPorClaveCatastral(propiedad.getClaveCatastral());

        PreparedStatement sentencia = conexion.prepareStatement(consltaSql);
        sentencia.setInt(1, idPropiedad);

        ResultSet resultado = sentencia.executeQuery();
        ArrayList<PropiedadAlquilada> propiedadesAlquiladas = new ArrayList<PropiedadAlquilada>();

        while(resultado.next()) {
            Date fechaAlquiler = resultado.getDate("fechaAlquiler");
            Date fechaInicio = resultado.getDate("fechaLlegada");
            Date fechaFin = resultado.getDate("fechaSalida");
            int idCliente = resultado.getInt("Cliente_idCliente");

            ClienteDAO gestorCliente = new ClienteDAO();
            Cliente clienteConsultado = gestorCliente.consultarClientePorIdCliente(idCliente);

            PropiedadAlquilada propiedadAlquiladaConsultada = new PropiedadAlquilada();
            propiedadAlquiladaConsultada.setFechaAlquiler(fechaAlquiler);
            propiedadAlquiladaConsultada.setFechaEntrada(fechaInicio);
            propiedadAlquiladaConsultada.setFechaSalida(fechaFin);
            propiedadAlquiladaConsultada.setPropiedad(propiedad);
            propiedadAlquiladaConsultada.setCliente(clienteConsultado);

            propiedadesAlquiladas.add(propiedadAlquiladaConsultada);
        }

        return propiedadesAlquiladas;
    }
}
