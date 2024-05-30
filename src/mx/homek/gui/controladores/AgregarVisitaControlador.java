package mx.homek.gui.controladores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AgregarVisitaControlador implements Initializable {
    private String nombreUsuario;
    private String tipoUsuario;

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

    @FXML
    private ComboBox ComboBoxPropiedad;
    @FXML
    private ComboBox ComboBoxCliente;
    @FXML
    private DatePicker DatePickerFecha;
    @FXML
    private TextField TextFieldHoraEntrada;
    @FXML
    private TextField TextFieldHoraSalida;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private Button ButtonAgregar;
    @FXML
    private TextField TextFieldNombreUsuario;
    @FXML
    private PasswordField PasswordFieldContraseña;

    public void setPropiedades() {
        try{
            PropiedadDAO propiedadDAO = new PropiedadDAO();
            ObservableList<String> listaPropiedades = propiedadDAO.consultarPropiedades();
            ComboBoxPropiedad.setItems(listaPropiedades);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCliente() {
        try{
            ClienteDAO clienteDAO = new ClienteDAO();
            ObservableList<String> listaClientes = clienteDAO.consultarClientes();
            ComboBoxCliente.setItems(listaClientes);
        } catch (SQLException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        validadorDeReglas = new ValidadorDeReglas();
        validadorDeReglas.agregarLimiteATextField(TextFieldHoraEntrada, 15);
        validadorDeReglas.agregarLimiteATextField(TextFieldHoraSalida, 15);
    }

    public void onAgregarClick() {
        ArrayList<String> camposTextField = new ArrayList<>();
        ArrayList<String> camposComboBox = new ArrayList<>();
        ArrayList <LocalDate> camposFecha = new ArrayList<>();
        String horaEntrada = TextFieldHoraEntrada.getText();
        String horaSalida = TextFieldHoraSalida.getText();
        camposTextField.add(horaEntrada);
        camposTextField.add(horaSalida);
        String propiedad = (String) ComboBoxPropiedad.getValue();
        String cliente = (String) ComboBoxCliente.getValue();
        camposComboBox.add(propiedad);
        camposComboBox.add(cliente);
        LocalDate fecha = DatePickerFecha.getValue();
        camposFecha.add(fecha);
        if (validadorDeReglas.validadorDeCamposVacios(camposTextField) || validadorDeReglas.validadorSelectorDeFechasVacios(camposFecha) || validadorDeReglas.validadorComboBoxVacios(camposComboBox)){
            Alert alertaCamposVacios = new Alert(Alert.AlertType.ERROR);
            alertaCamposVacios.setTitle("Error de campos vacíos");
            alertaCamposVacios.setContentText("Error, no seleccionaste alguno de los valores.");
            alertaCamposVacios.setHeaderText("Error de campos vacíos");
            alertaCamposVacios.showAndWait();
        }
        else if (validadorDeReglas.validadorDeCaracteresEspeciales(camposTextField)){
            Alert alertaNombreUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertaNombreUsuarioInvalido.setTitle("Error de de caracteres en alguna de las horas");
            alertaNombreUsuarioInvalido.setContentText("Error, algunas de las hora tiene caracteres invalidos");
            alertaNombreUsuarioInvalido.setHeaderText("Error en las fechas");
            alertaNombreUsuarioInvalido.showAndWait();
        }
        else {

        }
    }

    public void onCancelarClick() {
    }


}
