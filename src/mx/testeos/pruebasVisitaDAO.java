package mx.testeos;

import mx.homek.logic.implementaciones.VisitaDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Visita;
import org.junit.Test;

import java.sql.SQLException;

public class pruebasVisitaDAO {
    @Test
    public void pruebaInsertarVisitaBDSatisfactorio() throws SQLException {
        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = new Visita();
        Cliente cliente = new Cliente();
    }
}
