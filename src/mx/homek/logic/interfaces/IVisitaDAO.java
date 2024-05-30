package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Visita;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IVisitaDAO {
    public abstract int insertarVisita(Visita visita) throws SQLException;

    List<Visita> consultarVisitas(int idVisitante) throws SQLException;

    public abstract int consultarIDVisitaPorDatos(Visita visita) throws SQLException;
    public abstract int cancelarVisita(Visita visita) throws SQLException;
    public abstract int reagendarVisita(Visita visita) throws SQLException;
}
