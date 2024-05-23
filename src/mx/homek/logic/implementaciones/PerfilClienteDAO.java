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
    public int insertarPerfilPreferenciasDelCliente(PerfilClienteDAO preferenciasCliente) throws SQLException {
        return 0;
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
