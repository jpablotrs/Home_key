package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IVisitaDAO;
import mx.homek.logic.objetoDeTransferencia.Visita;

import java.sql.*;

public class VisitaDAO implements IVisitaDAO {
    private ConexionBaseDeDatos administradorBaseDeDatos;
    private Connection conexion;

    public VisitaDAO() throws SQLException {
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int insertarVisita(Visita visita) throws SQLException {
        java.sql.Date fecha = (java.sql.Date) visita.getFecha();
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        String consultaSQL = "insert into Visita (idVisitante, fecha, horaEntrada, horaSalida, estado, claveCatastral, Propiedad_idPropiedad) values (?,?,?,?,?,?,?)";
        PreparedStatement insertarVisita = conexion.prepareStatement(consultaSQL);
        insertarVisita.setInt(1, clienteDAO.consultarIDClientePorCorreo(visita.getCliente().getCorreo()));
        insertarVisita.setDate(2, fecha);
        insertarVisita.setString(3, visita.getHoraEntrada());
        insertarVisita.setString(4, visita.getHoraSalida());
        insertarVisita.setString(5, visita.getEstado());
        insertarVisita.setString(6, visita.getPropiedad().getClaveCatastral());
        insertarVisita.setInt(7, propiedadDAO.consultarIDPropiedadPorClaveCatastral(visita.getPropiedad().getClaveCatastral()));

        return insertarVisita.executeUpdate();
    }

    @Override
    public int consultarIDVisitaPorDatos(Visita visita) throws SQLException {
        java.sql.Date fecha = (java.sql.Date) visita.getFecha();
        ClienteDAO clienteDAO = new ClienteDAO();
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String consultaSQL = "select idVisita from visita where idPropiedad = ? and idCliente = ? and fecha = ? and horaEntrada = ?";
        PreparedStatement consultarId = conexion.prepareStatement(consultaSQL);
        consultarId.setInt(1, propiedadDAO.consultarIDPropiedadPorClaveCatastral(visita.getPropiedad().getClaveCatastral()));
        consultarId.setInt(2, clienteDAO.consultarIDClientePorCorreo(visita.getCliente().getCorreo()));
        consultarId.setDate(3, fecha);
        consultarId.setString(4, visita.getHoraEntrada());
        ResultSet resultadoConsulta = consultarId.executeQuery();
        if(resultadoConsulta.next())
            return resultadoConsulta.getInt("idVisita");
        return -1;
    }

    @Override
    public int cancelarVisita(Visita visita) throws SQLException {
        String consultaSQL = "UPDATE visita SET estado = 'cancelado' WHERE idVisita = ?";
        PreparedStatement cancelarVisita = conexion.prepareStatement(consultaSQL);
        cancelarVisita.setInt(1, consultarIDVisitaPorDatos(visita));
        return cancelarVisita.executeUpdate();
    }

    @Override
    public int reagendarVisita(Visita visita, Date fecha, String horaEntrada, String horaSalida) throws SQLException {
        String consultaSQL = "UPDATE visita SET fecha = ?, horaEntrada = ?, horaSalida = ? WHERE idVisita = ?";
        PreparedStatement reagendarVisita = conexion.prepareStatement(consultaSQL);
        reagendarVisita.setDate(1, fecha);
        reagendarVisita.setString(2, horaEntrada);
        reagendarVisita.setString(3, horaSalida);
        reagendarVisita.setInt(4, consultarIDVisitaPorDatos(visita));
        return reagendarVisita.executeUpdate();
    }
}
