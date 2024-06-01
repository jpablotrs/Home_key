package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.AgregarVisitaControlador;
import mx.homek.gui.controladores.ReagendarVisitaControlador;

import java.io.IOException;

public class ReagendarVisitaAplicacion extends Stage {
    public ReagendarVisitaAplicacion(String nombreUsuario, String tipoUsuario){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/ReagendarVisita.fxml"));
            Scene escenaRegistrarVisita = new Scene(fxmlLoader.load(), 600, 400);
            ReagendarVisitaControlador controladorReagendarVisita = fxmlLoader.<ReagendarVisitaControlador>getController();
            controladorReagendarVisita.setTipoUsuario(tipoUsuario);
            controladorReagendarVisita.setNombreUsuario(nombreUsuario);
            controladorReagendarVisita.setCampos();
            this.setTitle("Reagendar Visita");
            this.setScene(escenaRegistrarVisita);
            this.show();
        } catch (IOException ioException){

        }
    }
}
