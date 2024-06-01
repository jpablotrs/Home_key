package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.MenuPrincipalController;
import mx.homek.gui.controladores.ModicarCuentaController;

import java.io.IOException;

public class ModificarCuentaApplication extends Stage {
    public ModificarCuentaApplication(String nombreUsuario,String tipoUsuario){
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/ModificarCuenta.fxml"));
        Scene escenaComprarPropiedad = new Scene(fxmlLoader.load(), 600, 400);
        ModicarCuentaController controladorComprarPropiedad = fxmlLoader.<ModicarCuentaController>getController();
        controladorComprarPropiedad.setTipoUsuario(tipoUsuario);
        controladorComprarPropiedad.setNombreUsuaeio(nombreUsuario);
        controladorComprarPropiedad.setCampos();
        this.setTitle("Menu Principal");
        this.setScene(escenaComprarPropiedad);
        this.show();
    } catch (
    IOException ioException){

    }
    }
}
