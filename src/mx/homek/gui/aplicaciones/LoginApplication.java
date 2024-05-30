package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.ComprarPropiedadControlador;

import java.io.IOException;

public class LoginApplication extends Stage {
    public LoginApplication(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/login.fxml"));
            Scene escenaComprarPropiedad = new Scene(fxmlLoader.load(), 800, 600);
            this.setTitle("Iniciar Sesión");
            this.setScene(escenaComprarPropiedad);
            this.show();
        }
        catch (IOException ioException){

        }
    }
}
