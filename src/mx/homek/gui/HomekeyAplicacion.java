package mx.homek.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.ConsultarPropiedadAplicacion;

import java.io.IOException;

public class HomekeyAplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ConsultarPropiedadAplicacion();
    }

    public static void main(String[] args) {
        launch();
    }
}
