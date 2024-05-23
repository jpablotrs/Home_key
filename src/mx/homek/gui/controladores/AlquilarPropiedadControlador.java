package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadAlquiladaDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

public class AlquilarPropiedadControlador implements Initializable {
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
    private Label LabelPrecioAlquiler;
    @FXML
    private DatePicker DatePickerFechaInicial;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private Button ButtonAlquilar;
    @FXML
    private TextField TextFieldNumeroMeses;
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
        validadorDeReglas = new ValidadorDeReglas();
        validadorDeReglas.agregarLimiteATextField(TextFieldNumeroMeses, 2);
        validadorDeReglas.limitarCampoNumerico(TextFieldNumeroMeses);
    }

    public void cargarDatosPropiedad() {
        LabelDireccion.setText(propiedad.getDireccion());
        LabelCiudad.setText(propiedad.getCiudad());
        LabelEstado.setText(propiedad.getEstado());
        LabelCP.setText(propiedad.getCodigoPostal());
        LabelHabitaciones.setText(propiedad.getNumeroHabitaciones());
        LabelBaños.setText(propiedad.getNumeroBanos());
        LabelPisos.setText(propiedad.getNumeroPisos());
        LabelCocinas.setText(propiedad.getNumeroCocina());
        LabelMetrosCuadrados.setText(propiedad.getMetrosCuadrados());
        LabelNumeroDePersonas.setText(propiedad.getNumeroPersonas());
        LabelPrecioAlquiler.setText(propiedad.getAlquiler());
    }

    public boolean fechaValida() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaSeleccionada = DatePickerFechaInicial.getValue();
        int fechasComparadas = java.sql.Date.valueOf(fechaSeleccionada).compareTo(java.sql.Date.valueOf(fechaActual));

        boolean fechaValida = false;
        if(fechasComparadas < 0) {
            fechaValida = true;
        }

        return fechaValida;
    }

    public void onAgregarClick() {
        LocalDate fechaSeleccionada = DatePickerFechaInicial.getValue();
        if(fechaSeleccionada != null) {
            String numeroDeMeses = TextFieldNumeroMeses.getText();
            if(!validadorDeReglas.validadorCampoVacio(numeroDeMeses)) {
                if(fechaValida()) {
                    LocalDate fechaSalida = fechaSeleccionada.plusMonths(Integer.parseInt(numeroDeMeses));
                    LocalDate fechaActual = LocalDate.now();
                    Cliente alquilador = new Cliente();

                    try {
                        UsuarioDAO gestorUsuario = new UsuarioDAO();
                        int idUsuario = gestorUsuario.obtenerIDUsuarioPorNombre(nombreUsuario);
                        ClienteDAO gestorCliente = new ClienteDAO();
                        alquilador = gestorCliente.consultarClientePorIdUsuario(idUsuario);
                    }
                    catch (SQLException sqlException) {

                    }

                    PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
                    propiedadAlquilada.setFechaAlquiler(java.sql.Date.valueOf(fechaActual));
                    propiedadAlquilada.setFechaEntrada(java.sql.Date.valueOf(fechaSeleccionada));
                    propiedadAlquilada.setFechaSalida(java.sql.Date.valueOf(fechaSalida));
                    propiedadAlquilada.setPropiedad(propiedad);
                    propiedadAlquilada.setCliente(alquilador);

                    try {
                        PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
                        gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada);
                    }
                    catch (SQLException sqlException) {

                    }
                }
                else {
                    Alert alertaFechaInvalida = new Alert(Alert.AlertType.ERROR);
                    alertaFechaInvalida.setTitle("Fecha invalida");
                    alertaFechaInvalida.setContentText("Se ingreso una fecha antigua para alquilar la propiedad");
                    alertaFechaInvalida.setHeaderText("Fecha invalida");
                    alertaFechaInvalida.showAndWait();
                }
            }
            else {
                Alert alertaCamposVacios = new Alert(Alert.AlertType.ERROR);
                alertaCamposVacios.setTitle("Error no se escribio el numero de meses");
                alertaCamposVacios.setContentText("Error, no se ha escrito el numero de meses que se rentara la propiedad");
                alertaCamposVacios.setHeaderText("Error no se escribio el numero de meses");
                alertaCamposVacios.showAndWait();
            }
        }
        else {
            Alert alertaSinFecha = new Alert(Alert.AlertType.ERROR);
            alertaSinFecha.setTitle("Error no se selecciono fecha");
            alertaSinFecha.setContentText("Error, no se ha seleccionado una fecha para alquilar");
            alertaSinFecha.setHeaderText("Error no se selecciono fecha");
            alertaSinFecha.showAndWait();
        }
    }

    public void onCancelarClick() {
    }
}
