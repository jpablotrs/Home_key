package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.controladores.AgregarPropiedadControlador;
import mx.homek.gui.controladores.ConsultarPropiedadControlador;

import java.io.IOException;

public class ConsultarPropiedadAplicacion extends Stage {
    public ConsultarPropiedadAplicacion(String nombreUsuario,String tipoUsuario) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/ConsultarPropiedad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        ConsultarPropiedadControlador controladorComprarPropiedad = fxmlLoader.<ConsultarPropiedadControlador>getController();
        controladorComprarPropiedad.setTipoUsuario(tipoUsuario);
        controladorComprarPropiedad.setNombreUsuario(nombreUsuario);
        controladorComprarPropiedad.actualizarTabla();
        this.setTitle("Consultar Propiedad");
        this.setScene(scene);
        this.show();
    }
}
