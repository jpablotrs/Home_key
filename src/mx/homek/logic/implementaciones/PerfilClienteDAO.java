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
        String consultaSQL = "insert into preferenciascliente (ciudad, numHabitaciones, numBaños, Estado, pisos, costoMax, id_Cliente) values (?,?,?,?,?,?,?)";
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

    @Override
    public int obtenerIdPreferenciasPorIdCliente(int idCliente) throws SQLException {
        String consultaSql = "select IdPreferenciasCliente from preferenciasCliente where idCliente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setInt(1, idCliente);
        ResultSet resultado = sentencia.executeQuery();

        int idPreferencia = -1;
        if(resultado.next()) {
            idPreferencia = resultado.getInt("IdPreferenciasCliente");
        }

        return idPreferencia;
    }

    @Override
    public PerfilCliente consultarPerfilPorId(int idPerfil) throws SQLException {
        String consultaSql = "select * from preferenciasCliente where IdPreferenciasCliente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setInt(1, idPerfil);
        ResultSet resultado = sentencia.executeQuery();

        PerfilCliente perfilCliente = new PerfilCliente();
        if(resultado.next()) {
            String ciudad = resultado.getString("ciudad");
            String numeroHabitaciones = resultado.getString("numHabitaciones");
            String numeroBanos = resultado.getString("numBa├▒os");
            String estado = resultado.getString("Estado");
            String pisos = resultado.getString("pisos");
            String costoMaximo = resultado.getString("costoMax");

            perfilCliente.setCiudad(ciudad);
            perfilCliente.setNumeroHabitaciones(numeroHabitaciones);
            perfilCliente.setNumeroBanos(numeroBanos);
            perfilCliente.setEstado(estado);
            perfilCliente.setPisos(pisos);
            perfilCliente.setCostoMaximo(costoMaximo);
        }

        return perfilCliente;
    }
}
