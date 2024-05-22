import mx.homek.dataaccess.ConexionBaseDeDatos;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class PruebaBaseDatos {
    @Test
    public void pruebaObtenerConexionSatisfactorio() throws SQLException {
        ConexionBaseDeDatos manejador = new ConexionBaseDeDatos();
        Connection resultado = manejador.obtenerConexion();
        assertNotNull(resultado);
    }
}
