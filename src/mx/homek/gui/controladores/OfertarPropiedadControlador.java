package mx.homek.gui.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.CompraPropiedadDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OfertarPropiedadControlador implements Initializable {
    @FXML
    private Label LabelDireccion;
    @FXML
    private Label LabelCiudad;
    @FXML
    private Label LabelEstado;
    @FXML
    private Label LabelCP;
    @FXML
    private Label LabelHabitaciones;
    @FXML
    private Label LabelBaños;
    @FXML
    private Label LabelPisos;
    @FXML
    private Label LabelCocinas;
    @FXML
    private Label LabelMetrosCuadrados;
    @FXML
    private Label LabelNumeroDePersonas;
    @FXML
    private Label LabelElectricidad;
    @FXML
    private Label LabelAmueblado;
    @FXML
    public Label LabelPrecioOferta;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private Button ButtonOfertar;
    private String nombreUsuario;
    private String tipoUsuario;
    private Propiedad propiedad;
    private ValidadorDeReglas validadorDeReglas;

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

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cargarDatosPropiedad() {
        LabelDireccion.setText(propiedad.getDireccion());
        LabelCiudad.setText(propiedad.getCiudad());
        LabelEstado.setText(propiedad.getEstado());
        LabelCP.setText(propiedad.getCodigoPostal());
        LabelHabitaciones.setText(Integer.toString(propiedad.getNumeroHabitaciones()));
        LabelBaños.setText(Integer.toString(propiedad.getNumeroBanos()));
        LabelPisos.setText(Integer.toString(propiedad.getNumeroPisos()));
        LabelCocinas.setText(Integer.toString(propiedad.getNumeroCocina()));
        LabelMetrosCuadrados.setText(Integer.toString(propiedad.getMetrosCuadrados()));
        LabelNumeroDePersonas.setText(Integer.toString(propiedad.getNumeroPersonas()));

        if(propiedad.getElectricidad() == 1) {
            LabelElectricidad.setText("Si cuenta con servicio electrico incluido");
        }
        else {
            LabelElectricidad.setText("No cuenta con servicio electrico incluido");
        }

        if(propiedad.getAmueblado() == 1) {
            LabelAmueblado.setText("Si cuenta con amueblado");
        }
        else {
            LabelAmueblado.setText("No cuenta con amueblado");
        }

        LabelPrecioOferta.setText("$200.00");
    }

    public void onAgregarClick(ActionEvent actionEvent) {
        try {
            CompraPropiedadDAO gestorOferta = new CompraPropiedadDAO();
            PropiedadDAO gestorPropiedad = new PropiedadDAO();
            gestorOferta.ofertarPropiedad(gestorPropiedad.consultarIDPropiedadPorClaveCatastral(propiedad.getClaveCatastral()));
        }
        catch (SQLException sqlException) {

        }
    }

    public void onCancelarClick(ActionEvent actionEvent) {
//        Alert confirmacionDeSalida = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmacionDeSalida.setHeaderText("Salir del menú");
//        confirmacionDeSalida.setContentText("¿Desea Salir del menú Subir Constancia?");
//        Optional<ButtonType> botonSeleccionado = confirmacionDeSalida.showAndWait();
//        if (botonSeleccionado.isPresent() && botonSeleccionado.get() == ButtonType.OK) {
//            try {
//                Scene escena = LabelCiudad.getScene();
//                Stage stageSubirConstancia = (Stage) escena.getWindow();
//                stageSubirConstancia.close();
//                ConsultarPropiedadesAplicacion consultarPropiedadesAplicacion = new ConsultarPropiedadesAplicacion(nombreUsuario,tipoUsuario);
//            } catch (IOException ioException) {
//                Alert escenaNoEncontrada = new Alert(Alert.AlertType.ERROR);
//                escenaNoEncontrada.setTitle("Error de cambio de pantalla");
//                escenaNoEncontrada.setContentText("Error al regresar al menu principal");
//                escenaNoEncontrada.setHeaderText("Error de cambio de pantalla");
//                escenaNoEncontrada.showAndWait();
//            }
//        }
    }
}
