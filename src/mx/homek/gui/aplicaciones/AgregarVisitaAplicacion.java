package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.homek.gui.HomekeyAplicacion;
import mx.homek.gui.controladores.AgregarVisitaControlador;

import java.io.IOException;
import java.sql.SQLException;

public class AgregarVisitaAplicacion extends Stage {
    public AgregarVisitaAplicacion(String nombreUsuario, String tipoUsuario) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomekeyAplicacion.class.getResource("fxml/AgregarVisita.fxml"));
        Scene SceneAgregarVisita = new Scene(fxmlLoader.load(), 600, 400);
        AgregarVisitaControlador agregarVisitaControlador = fxmlLoader.<AgregarVisitaControlador> getController();
        agregarVisitaControlador.setNombreUsuario(nombreUsuario);
        agregarVisitaControlador.setTipoUsuario(tipoUsuario);
        this.setTitle("Agregar Visita");
        this.setScene(SceneAgregarVisita);
        this.show();
    }

}
