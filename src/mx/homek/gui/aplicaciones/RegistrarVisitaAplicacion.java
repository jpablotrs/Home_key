package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.AgregarVisitaControlador;
import mx.homek.gui.controladores.ModicarCuentaController;

import java.io.IOException;

public class RegistrarVisitaAplicacion extends Stage {
    public RegistrarVisitaAplicacion(String nombreUsuario, String tipoUsuario){
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/AgregarVisita.fxml"));
        Scene escenaRegistrarVisita = new Scene(fxmlLoader.load(), 600, 400);
        AgregarVisitaControlador controladorRegistrarVisita = fxmlLoader.<AgregarVisitaControlador>getController();
        controladorRegistrarVisita.setTipoUsuario(tipoUsuario);
        controladorRegistrarVisita.setNombreUsuario(nombreUsuario);
        controladorRegistrarVisita.setCampos();
        this.setTitle("Registrar Visita");
        this.setScene(escenaRegistrarVisita);
        this.show();
    } catch (
    IOException ioException){

    }
    }
}
