package mx.homek.gui.controladores;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    String nombreUsuario;
    String tipoUsuario;

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

    }
    public void onMisVisitasClick(){

    }
    public void onConsultarPropiedadesClick(){

    }
    public void onCerrarSesionClick(){

    }
    public void onAgregarPropiedadClick(){

    }
}
