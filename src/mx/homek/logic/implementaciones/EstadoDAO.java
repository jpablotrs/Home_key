package mx.homek.logic.implementaciones;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.iEstadoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoDAO implements iEstadoDAO {
    Connection connection;
    ConexionBaseDeDatos conexionBaseDeDatos;
    public EstadoDAO(){
        try {
            conexionBaseDeDatos = new ConexionBaseDeDatos();
            connection = conexionBaseDeDatos.obtenerConexion();
        }
        catch (SQLException sqlException){

        }
    }
    @Override
    public ObservableList<String> consultarEstados(){
        ObservableList <String> listaEstados = FXCollections.observableArrayList();
        try {
            String consulta = "Select * from estado";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String estado = resultSet.getString("CTLG_ESTADO_DESC");
                listaEstados.add(estado);
            }
            return listaEstados;
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return listaEstados;
    }

    @Override
    public int obtenerIdEstado(String nombre) {
        int id=-1;
        try {
            String consulta = "Select * from estado where CTLG_ESTADO_DESC = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1,nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("CTLG_ESTADO_ID");
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return id;
    }
}
