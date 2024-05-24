package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ConsultarPropiedadAplicacion extends Stage {
    public ConsultarPropiedadAplicacion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/ConsultarPropiedad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        this.setTitle("Consultar Propiedad");
        this.setScene(scene);
        this.show();
    }
}
