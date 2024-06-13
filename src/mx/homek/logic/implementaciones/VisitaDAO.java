package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IVisitaDAO;
import mx.homek.logic.objetoDeTransferencia.Visita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String consultaSQL = "insert into Visita (idVisitante, fecha, horaEntrada, horaSalida, estado, Propiedad_idPropiedad) values (?,?,?,?,?,?)";
        PreparedStatement insertarVisita = conexion.prepareStatement(consultaSQL);
        insertarVisita.setInt(1, visita.getCliente().getIdCliente());
        insertarVisita.setDate(2, fecha);
        insertarVisita.setString(3, visita.getHoraEntrada());
        insertarVisita.setString(4, visita.getHoraSalida());
        insertarVisita.setInt(5, visita.getEstado());
        insertarVisita.setInt(6, visita.getPropiedad().getIdPropiedad());

        return insertarVisita.executeUpdate();
    }

    @Override
    public List<Visita> consultarVisitas(int idVisitante) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String consultaSQL = "select * from visita where idVisitante = ? and estado = 1";
        PreparedStatement consultarId = conexion.prepareStatement(consultaSQL);
        consultarId.setInt(1, idVisitante);

        ResultSet resultadoConsulta = consultarId.executeQuery();
        List<Visita> listaVisitas = new ArrayList<>();
        while(resultadoConsulta.next()){
            Visita visita = new Visita();
            visita.setIdVisita(resultadoConsulta.getInt("idVisita"));
            visita.setEstado(resultadoConsulta.getInt("estado"));
            visita.setPropiedad(propiedadDAO.consultarPropiedad(resultadoConsulta.getInt("Propiedad_idPropiedad")));
            visita.setHoraSalida(resultadoConsulta.getString("horaSalida"));
            visita.setHoraEntrada(resultadoConsulta.getString("horaEntrada"));
            visita.setFecha(resultadoConsulta.getDate("fecha"));
            visita.setCliente(clienteDAO.consultarClientePorIdUsuario(resultadoConsulta.getInt("Propiedad_idPropiedad")));
            listaVisitas.add(visita);
        }
        return listaVisitas;
    }

    @Override
    public int consultarIDVisitaPorDatos(Visita visita) throws SQLException {
        java.sql.Date fecha = (java.sql.Date) visita.getFecha();
        ClienteDAO clienteDAO = new ClienteDAO();
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String consultaSQL = "select idVisita from visita where Propiedad_idPropiedad = ? and idVisitante = ? and fecha = ? and horaEntrada = ?";
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
        String consultaSQL = "UPDATE visita SET estado = 2 WHERE idVisita = ?";
        PreparedStatement cancelarVisita = conexion.prepareStatement(consultaSQL);
        cancelarVisita.setInt(1, visita.getIdVisita());
        return cancelarVisita.executeUpdate();
    }

    @Override
    public int reagendarVisita(Visita visita) throws SQLException {
        String consultaSQL = "UPDATE visita SET fecha = ?, horaEntrada = ?, horaSalida = ? WHERE idVisita = ?";
        PreparedStatement reagendarVisita = conexion.prepareStatement(consultaSQL);
        reagendarVisita.setDate(1, (java.sql.Date) visita.getFecha());
        reagendarVisita.setString(2, visita.getHoraEntrada());
        reagendarVisita.setString(3, visita.getHoraSalida());
        reagendarVisita.setInt(4, visita.getIdVisita());
        return reagendarVisita.executeUpdate();
    }
}
