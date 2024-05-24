package mx.homek.logic.Validadores;

import javafx.scene.control.Alert;

public class CreadorAlertas {
    public  void crearAlertaDeInformacion(String encabezado, String texto, String cuerpoMensaje){
        Alert alertaInformacion = new Alert(Alert.AlertType.INFORMATION);
        alertaInformacion.setTitle(encabezado);
        alertaInformacion.setHeaderText(texto);
        alertaInformacion.setContentText(cuerpoMensaje);
        alertaInformacion.showAndWait();
    }
    public  void crearAlertaDeError(String encabezado, String texto, String cuerpoMensaje){
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle(encabezado);
        alertaError.setHeaderText(texto);
        alertaError.setContentText(cuerpoMensaje);
        alertaError.showAndWait();
    }
    public  void crearAlertaDeAdvertencia(String encabezado, String texto, String cuerpoMensaje){
        Alert alertaAdvertencia = new Alert(Alert.AlertType.WARNING);
        alertaAdvertencia.setTitle(encabezado);
        alertaAdvertencia.setHeaderText(texto);
        alertaAdvertencia.setContentText(cuerpoMensaje);
        alertaAdvertencia.showAndWait();
    }
}
