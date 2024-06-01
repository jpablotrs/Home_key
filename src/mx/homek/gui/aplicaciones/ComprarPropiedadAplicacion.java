package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.ComprarPropiedadControlador;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;

public class ComprarPropiedadAplicacion extends Stage {
    public ComprarPropiedadAplicacion(String nombreUsuario, String tipoUsuario, Propiedad propiedad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/ComprarPropiedad.fxml"));
        Scene escenaComprarPropiedad = new Scene(fxmlLoader.load(),600,400);
        ComprarPropiedadControlador controladorComprarPropiedad = fxmlLoader.<ComprarPropiedadControlador> getController();
        controladorComprarPropiedad.setTipoUsuario(tipoUsuario);
        controladorComprarPropiedad.setNombreUsuario(nombreUsuario);
        controladorComprarPropiedad.setPropiedad(propiedad);
        controladorComprarPropiedad.cargarDatosPropiedad();
        this.setTitle("Comprar propiedad");
        this.setScene(escenaComprarPropiedad);
        this.show();
    }
}
