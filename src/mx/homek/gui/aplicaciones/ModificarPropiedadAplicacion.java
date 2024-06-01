package mx.homek.gui.aplicaciones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ModificarPropiedadAplicacion extends Stage {
    public ModificarPropiedadAplicacion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/ModificarPropiedad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        this.setTitle("Modificar Propiedad");
        this.setScene(scene);
        this.show();
    }
}
