package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.ComprarPropiedadControlador;
import mx.homek.gui.controladores.MenuPrincipalController;

import java.io.IOException;

public class MenuPrincipalApplication extends Stage {
    public MenuPrincipalApplication(String nombreUsuario,String tipoUsuario){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/MenuPrincipal.fxml"));
            Scene escenaComprarPropiedad = new Scene(fxmlLoader.load(), 600, 400);
            MenuPrincipalController controladorComprarPropiedad = fxmlLoader.<MenuPrincipalController>getController();
            controladorComprarPropiedad.setTipoUsuario(tipoUsuario);
            controladorComprarPropiedad.setNombreUsuario(nombreUsuario);
            escenaComprarPropiedad.getStylesheets().add(HomekeyAplicacion.class.getResource("css/textoblanco.css").toExternalForm());
            this.setTitle("Menu Principal");
            this.setScene(escenaComprarPropiedad);
            this.show();
        } catch (IOException ioException){

        }
    }
}
