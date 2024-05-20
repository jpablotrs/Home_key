package mx.dataaccess;

import java.sql.*;

public class ConexionBaseDeDatos {
    private Connection conexion = null;
    private final String NOMBRE_BD = "jdbc:mysql://localhost:3306/coilvic12/05/24";
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
        }
        catch (SQLException errorSql) {
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