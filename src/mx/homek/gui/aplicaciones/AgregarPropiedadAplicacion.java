package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AgregarPropiedadAplicacion extends Stage {
    public AgregarPropiedadAplicacion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/AgregarPropiedad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        this.setTitle("Agregar Propiedad");
        this.setScene(scene);
        this.show();
    }
}
