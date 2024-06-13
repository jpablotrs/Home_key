package mx.homek.logic.implementaciones;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.iMunicipioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipioDAO implements iMunicipioDAO {
    ConexionBaseDeDatos conexionBaseDeDatos;
    Connection connection;
    public MunicipioDAO(){
        try{
            conexionBaseDeDatos = new ConexionBaseDeDatos();
            connection = conexionBaseDeDatos.obtenerConexion();
        }
        catch (Exception sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
    @Override
    public ObservableList<String> obtenerMunicipioPorEstado(int id) {
        ObservableList listaMunicipios = FXCollections.observableArrayList();
        try {
            String consulta = "Select * from municipio where CTLG_ESTADO_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String municipio = resultSet.getString("CTLG_MUNICIPIO_DESC");
                listaMunicipios.add(municipio);
            }
            return listaMunicipios;
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return listaMunicipios;
    }
}
