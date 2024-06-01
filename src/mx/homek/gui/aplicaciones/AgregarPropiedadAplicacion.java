package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.controladores.AgregarPropiedadControlador;
import mx.homek.gui.controladores.ModicarCuentaController;

import java.io.IOException;

public class AgregarPropiedadAplicacion extends Stage {
    public AgregarPropiedadAplicacion(String nombreUsuario,String tipoUsuario) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/AgregarPropiedad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        AgregarPropiedadControlador controladorComprarPropiedad = fxmlLoader.<AgregarPropiedadControlador>getController();
        controladorComprarPropiedad.setTipoUsuario(tipoUsuario);
        controladorComprarPropiedad.setNombreUsuario(nombreUsuario);
        this.setTitle("Agregar Propiedad");
        this.setScene(scene);
        this.show();
    }
}
