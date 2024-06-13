package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.AlquilarPropiedadControlador;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;

public class AlquilarPropiedaAplicacion extends Stage {
    public AlquilarPropiedaAplicacion(String nombreUsuario, String tipoUsuario, Propiedad propiedad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/AlquilarPropiedad.fxml"));
        Scene escenaAquilarPropiedad = new Scene(fxmlLoader.load(),600,800);
        AlquilarPropiedadControlador controladorAlquilarPropiedad = fxmlLoader.<AlquilarPropiedadControlador> getController();
        controladorAlquilarPropiedad.setTipoUsuario(tipoUsuario);
        controladorAlquilarPropiedad.setNombreUsuario(nombreUsuario);
        controladorAlquilarPropiedad.setPropiedad(propiedad);
        controladorAlquilarPropiedad.cargarDatosPropiedad();
        this.setTitle("Alquilar propiedad");
        this.setScene(escenaAquilarPropiedad);
        this.show();
    }
}