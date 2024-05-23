package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPerfilClienteDAO;
import mx.homek.logic.objetoDeTransferencia.PerfilCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilClienteDAO implements IPerfilClienteDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public PerfilClienteDAO() throws SQLException{
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }
    @Override
    public int insertarPerfilPreferenciasDelCliente(PerfilCliente preferenciasCliente) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        String consultaSQL = "insert into preferenciascliente (ciudad, numHabitaciones, numBaños, Estado, pisos, costoMax, idCliente) values (?,?,?,?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setString(1, preferenciasCliente.getCiudad());
        sentencia.setString(2, preferenciasCliente.getNumeroHabitaciones());
        sentencia.setString(3, preferenciasCliente.getNumeroBanos());
        sentencia.setString(4, preferenciasCliente.getEstado());
        sentencia.setString(5, preferenciasCliente.getPisos());
        sentencia.setString(6, preferenciasCliente.getCostoMaximo());
        sentencia.setInt(7, clienteDAO.consultarIDClientePorCorreo(preferenciasCliente.getCliente().getCorreo()));
        return sentencia.executeUpdate();
    }

    @Override
    public int convertirPerfilPreferenciasAID(PerfilCliente preferencias) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        String consultaSQL = "select idPreferenciasCliente from preferenciasCliente where idCliente = ?";
        PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
        sentencia.setInt(1, clienteDAO.consultarIDClientePorCorreo(preferencias.getCliente().getCorreo()));
        ResultSet resultadoConsulta = sentencia.executeQuery();
        int idPerfilPreferencias = -1;
        if (resultadoConsulta.next()){
            idPerfilPreferencias = resultadoConsulta.getInt("idPreferenciasCliente");
        }
        return idPerfilPreferencias;
    }
}
