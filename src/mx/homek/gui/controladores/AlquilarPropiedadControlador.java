package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.ConsultarPropiedadAplicacion;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadAlquiladaDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
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
        LabelHabitaciones.setText(Integer.toString(propiedad.getNumeroHabitaciones()));
        LabelBaños.setText(Integer.toString(propiedad.getNumeroBanos()));
        LabelPisos.setText(Integer.toString(propiedad.getNumeroPisos()));
        LabelCocinas.setText(Integer.toString(propiedad.getNumeroCocina()));
        LabelMetrosCuadrados.setText(Integer.toString(propiedad.getMetrosCuadrados()));
        LabelNumeroDePersonas.setText(Integer.toString(propiedad.getNumeroPersonas()));
        LabelPrecioAlquiler.setText(Integer.toString(propiedad.getAlquiler()));
    }

    public boolean fechaValida(LocalDate fechaSeleccionada) {
        LocalDate fechaActual = LocalDate.now();
        int fechasComparadas = java.sql.Date.valueOf(fechaSeleccionada).compareTo(java.sql.Date.valueOf(fechaActual));

        boolean fechaValida = false;
        if(fechasComparadas > 0) {
            fechaValida = true;
        }

        return fechaValida;
    }

    public boolean validarPeriodoDeAlquilerValido(Date fechaInicio, Date fechaFin) {
        ArrayList<PropiedadAlquilada> registrosDeAlquiler = new ArrayList<PropiedadAlquilada>();

        try {
            PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
            registrosDeAlquiler = gestorPropiedadAlquilada.consultarPropiedadesAlquiladasPorPropiedad(propiedad);
        }
        catch (SQLException sqlException) {
            Alert alertaErrorConsulta = new Alert(Alert.AlertType.ERROR);
            alertaErrorConsulta.setTitle("Error de base de datos");
            alertaErrorConsulta.setContentText("Hubo un error al conectar con la base de datos por favor intentelo mas tarde o recargue la pagina");
            alertaErrorConsulta.setHeaderText("Error de base de datos");
            alertaErrorConsulta.showAndWait();
        }

        boolean fechaValida = false;
        boolean fechaFinInvalida = false;
        boolean fechaInicioInvalida = false;

        if(registrosDeAlquiler != null) {
            for(PropiedadAlquilada registroDeAlquiler : registrosDeAlquiler) {
                Date fechaInicioObtenida = registroDeAlquiler.getFechaEntrada();
                Date fechaFinObteniada = registroDeAlquiler.getFechaSalida();

                int verificarFechaInicioPreAlquiler = fechaInicio.compareTo(fechaInicioObtenida);
                int verificarFechaFinPreAlquiler = fechaFin.compareTo(fechaInicioObtenida);

                if(!fechaFinInvalida && !fechaInicioInvalida) {
                    if(verificarFechaInicioPreAlquiler < 0) {
                        if(verificarFechaFinPreAlquiler > -1) {
                            fechaFinInvalida = true;
                        }
                    }
                    else {
                        int verificarFechaInicioPostAlquiler = fechaInicio.compareTo(fechaFinObteniada);

                        if(verificarFechaInicioPostAlquiler < 1) {
                            fechaInicioInvalida = true;
                        }
                    }
                }
            }

            if(fechaFinInvalida) {
                Alert fechaDeFinInvalida = new Alert(Alert.AlertType.ERROR);
                fechaDeFinInvalida.setTitle("Error de fecha");
                fechaDeFinInvalida.setContentText("El fin de periodo de alquiler seleccionado choca con el inicio de otro alquiler, por favor selecione otra fecha");
                fechaDeFinInvalida.setHeaderText("Error de fecha");
                fechaDeFinInvalida.showAndWait();
            }
            else if(fechaInicioInvalida) {
                Alert fechaDeInicioInvalida = new Alert(Alert.AlertType.ERROR);
                fechaDeInicioInvalida.setTitle("Error de fecha");
                fechaDeInicioInvalida.setContentText("El inicio de periodo de alquiler seleccionado choca con el fin de otro alquiler, por favor selecione otra fecha");
                fechaDeInicioInvalida.setHeaderText("Error de fecha");
                fechaDeInicioInvalida.showAndWait();
            }
            else {
                fechaValida = true;
            }
        }

        return fechaValida;
    }

    public void onAgregarClick() {
        LocalDate fechaSeleccionada = DatePickerFechaInicial.getValue();
        if(fechaSeleccionada != null) {
            String numeroDeMeses = TextFieldNumeroMeses.getText();
            if(!validadorDeReglas.validadorCampoVacio(numeroDeMeses)) {
                if(fechaValida(fechaSeleccionada)) {
                    LocalDate fechaSalida = fechaSeleccionada.plusMonths(Integer.parseInt(numeroDeMeses));
                    Date fechaInicio = java.sql.Date.valueOf(fechaSeleccionada);
                    Date fechaFin = java.sql.Date.valueOf(fechaSalida);
                    if(validarPeriodoDeAlquilerValido(fechaInicio, fechaFin)) {
                        LocalDate fechaActual = LocalDate.now();
                        Cliente alquilador = new Cliente();

                        try {
                            UsuarioDAO gestorUsuario = new UsuarioDAO();
                            int idUsuario = gestorUsuario.obtenerIDUsuarioPorNombre(nombreUsuario);
                            ClienteDAO gestorCliente = new ClienteDAO();
                            alquilador = gestorCliente.consultarClientePorIdUsuario(idUsuario);
                        }
                        catch (SQLException sqlException) {
                            System.out.println(sqlException.getMessage());
                            CreadorAlertas creadorAlertas = new CreadorAlertas();
                            creadorAlertas.crearAlertaDeError("Error en la insercion","Error","Error");
                        }

                        PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
                        propiedadAlquilada.setFechaAlquiler(java.sql.Date.valueOf(fechaActual));
                        propiedadAlquilada.setFechaEntrada(fechaInicio);
                        propiedadAlquilada.setFechaSalida(fechaFin);
                        propiedadAlquilada.setPropiedad(propiedad);
                        propiedadAlquilada.setCliente(alquilador);

                        try {
                            PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
                            gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada);
                            CreadorAlertas creadorAlertas = new CreadorAlertas();
                            creadorAlertas.crearAlertaDeInformacion("Alquiler exitoso","Usted ha alquilado la propiedad", "Alquiler exitoso");
                            Scene escena = LabelBaños.getScene();
                            Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
                            stageAgregarProfesorExterno.close();
                            try {
                                ConsultarPropiedadAplicacion consultarPropiedadAplicacion = new ConsultarPropiedadAplicacion(nombreUsuario, tipoUsuario);
                            }
                            catch (IOException ioException){

                            }
                        }
                        catch (SQLException sqlException) {
                            System.out.println(sqlException.getMessage());
                            CreadorAlertas creadorAlertas = new CreadorAlertas();
                            creadorAlertas.crearAlertaDeError("Error en la insercion","Error","Error");
                        }
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
        Scene escena = LabelBaños.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try {
            ConsultarPropiedadAplicacion consultarPropiedadAplicacion = new ConsultarPropiedadAplicacion(nombreUsuario, tipoUsuario);
        }
        catch (IOException ioException){

        }
    }
}
