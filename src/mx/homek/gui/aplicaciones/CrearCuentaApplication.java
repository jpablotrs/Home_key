package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.ComprarPropiedadControlador;

import java.io.IOException;

public class CrearCuentaApplication extends Stage {
    public CrearCuentaApplication () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/CrearCuenta.fxml"));
            Scene escenaComprarPropiedad = new Scene(fxmlLoader.load(), 600, 400);
            this.setTitle("Comprar propiedad");
            this.setScene(escenaComprarPropiedad);
            this.show();
        }
        catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }
}
