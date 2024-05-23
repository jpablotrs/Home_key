package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.controladores.OfertarPropiedadControlador;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;

public class OfertarPropiedadAplicacion extends Stage {
    public OfertarPropiedadAplicacion(String nombreUsuario, String tipoUsuario, Propiedad propiedad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OfertarPropiedadAplicacion.class.getResource("fxml/OfertarPropiedad.fxml"));
        Scene escenaOfertarPropiedad = new Scene(fxmlLoader.load(),600,400);
        OfertarPropiedadControlador controladorOfertarPropiedad = fxmlLoader.<OfertarPropiedadControlador> getController();
        controladorOfertarPropiedad.setTipoUsuario(tipoUsuario);
        controladorOfertarPropiedad.setNombreUsuario(nombreUsuario);
        controladorOfertarPropiedad.setPropiedad(propiedad);
        controladorOfertarPropiedad.cargarDatosPropiedad();
        escenaOfertarPropiedad.getStylesheets().add(OfertarPropiedadAplicacion.class.getResource("css/textoblanco.css").toExternalForm());
        this.setTitle("Ofertar propiedad");
        this.setScene(escenaOfertarPropiedad);
        this.show();
    }
}
