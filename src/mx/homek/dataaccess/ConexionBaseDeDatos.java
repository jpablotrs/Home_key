package mx.homek.dataaccess;

import mx.homek.logic.Validadores.CreadorAlertas;

import java.sql.*;

public class ConexionBaseDeDatos {
    private Connection conexion = null;
    private final String NOMBRE_BD = "jdbc:mysql://localhost/inmobiliaria";
    private final String USUARIO_BD = "root";
    private final String CONTRASENABD = "root";

    public Connection obtenerConexion() throws SQLException {
        connect();
        return conexion;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection(NOMBRE_BD,USUARIO_BD,CONTRASENABD);
        }
        catch (ClassNotFoundException errorClase) {
            CreadorAlertas creadorAlertas = new CreadorAlertas();
            creadorAlertas.crearAlertaDeError("No hay conexión con la base de datos","Error de conexión","Error");
        }
        catch (SQLException errorSql) {
            CreadorAlertas creadorAlertas = new CreadorAlertas();
            creadorAlertas.crearAlertaDeError("No hay conexión con la base de datos","Error de conexión","Error");
        }
    }

    public void cerrarConexion() {
        if(conexion!=null){
            try {
                if(!conexion.isClosed()) {
                    conexion.close();
                }
            }
            catch (SQLException exception) {
            }
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}