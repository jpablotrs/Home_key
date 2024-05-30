package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.AgregarPropiedadAplicacion;
import mx.homek.gui.aplicaciones.ConsultarPropiedadAplicacion;
import mx.homek.gui.aplicaciones.LoginApplication;
import mx.homek.gui.aplicaciones.ModificarCuentaApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    String nombreUsuario;
    String tipoUsuario;
    @FXML
    Label LabelEtiqueta;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onMicuentaClick(){
        Scene escena = LabelEtiqueta.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        ModificarCuentaApplication modificarCuentaApplication = new ModificarCuentaApplication(nombreUsuario,tipoUsuario);
    }
    public void onMisVisitasClick(){

    }
    public void onConsultarPropiedadesClick(){
        Scene escena = LabelEtiqueta.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try {
            ConsultarPropiedadAplicacion consultarPropiedadAplicacion = new ConsultarPropiedadAplicacion(nombreUsuario,tipoUsuario);
        }
        catch (IOException ioException){

        }
    }
    public void onCerrarSesionClick(){
        Scene escena = LabelEtiqueta.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        LoginApplication loginApplication = new LoginApplication();
    }
    public void onAgregarPropiedadClick(){
        Scene escena = LabelEtiqueta.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try {
            AgregarPropiedadAplicacion agregarPropiedadAplicacion = new AgregarPropiedadAplicacion(nombreUsuario,tipoUsuario);
        }
        catch (IOException ioException){

        }
    }
}
