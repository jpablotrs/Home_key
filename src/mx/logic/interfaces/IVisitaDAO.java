package mx.logic.interfaces;

import mx.logic.objetoDeTransferencia.Visita;

import java.sql.Date;
import java.sql.SQLException;

public interface IVisitaDAO {
    public abstract int insertarVisita(Visita visita) throws SQLException;
    public abstract int consultarIDVisitaPorDatos(Visita visita) throws SQLException;
    public abstract int cancelarVisita(Visita visita) throws SQLException;
    public abstract int reagendarVisita(Visita visita, Date fecha, String horaEntrada, String horaSalida) throws SQLException;
}
