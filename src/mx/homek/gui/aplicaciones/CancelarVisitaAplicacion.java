package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.CancelarVisitaControlador;
import mx.homek.gui.controladores.ReagendarVisitaControlador;

import java.io.IOException;

public class CancelarVisitaAplicacion extends Stage {
    public CancelarVisitaAplicacion(String nombreUsuario, String tipoUsuario){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/CancelarVisita.fxml"));
            Scene escenaRegistrarVisita = new Scene(fxmlLoader.load(), 600, 400);
            CancelarVisitaControlador controladorCancelarVisita = fxmlLoader.<CancelarVisitaControlador>getController();
            controladorCancelarVisita.setTipoUsuario(tipoUsuario);
            controladorCancelarVisita.setNombreUsuario(nombreUsuario);
            controladorCancelarVisita.setCampos();
            this.setTitle("Cancelar Visita");
            this.setScene(escenaRegistrarVisita);
            this.show();
        } catch (IOException ioException){

        }
    }
}
