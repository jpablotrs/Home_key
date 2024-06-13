package mx.homek.gui.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import
        javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;
import mx.homek.gui.aplicaciones.LoginApplication;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.implementaciones.VisitaDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Usuario;
import mx.homek.logic.objetoDeTransferencia.Visita;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private LocalTimePicker TextFieldHoraEntrada;
    @FXML
    private LocalTimePicker TextFieldHoraSalida;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private Button ButtonAgregar;
    @FXML
    private TextField TextFieldNombreUsuario;
    @FXML
    private PasswordField PasswordFieldContraseña;

    public void setCampos() {
        try{
            PropiedadDAO propiedadDAO = new PropiedadDAO();
            ObservableList<Propiedad> listaPropiedades = propiedadDAO.consultarPropiedadesObs(getNombreUsuario());
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
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR_CHECK_ALT);
        icono.setGlyphSize(30);
        ButtonAgregar.setGraphic(icono);
        FontAwesomeIconView iconoCrearCuenta = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        iconoCrearCuenta.setGlyphSize(30);
        ButtonCancelar.setGraphic(iconoCrearCuenta);
        ButtonAgregar.setOnMouseEntered(event -> ButtonAgregar.setCursor(Cursor.HAND));
        ButtonAgregar.setOnMouseExited(event -> ButtonAgregar.setCursor(Cursor.DEFAULT));
        ButtonCancelar.setOnMouseEntered(event -> ButtonCancelar.setCursor(Cursor.HAND));
        ButtonCancelar.setOnMouseExited(event -> ButtonCancelar.setCursor(Cursor.DEFAULT));
    }

    public void onAgregarClick() {
        ArrayList<String> camposTextField = new ArrayList<>();
        ArrayList<String> camposComboBox = new ArrayList<>();
        ArrayList <LocalDate> camposFecha = new ArrayList<>();
        LocalTime horaE = TextFieldHoraEntrada.getLocalTime();
        LocalTime horaS = TextFieldHoraSalida.getLocalTime();

        Propiedad propiedad = (Propiedad) ComboBoxPropiedad.getValue();

        camposComboBox.add(propiedad!=null?propiedad.toString():null);
        LocalDate fecha = DatePickerFecha.getValue();
        boolean horaEntradaValida = true;
        boolean horaSalidaValida = true;
        try {

            if (horaE.getHour() < 8 || horaE.getHour() > 20)
                horaEntradaValida = false;
            try {
                if (horaS.getHour() < 8 || horaS.getHour() > 20)
                    horaSalidaValida = false;
                if (horaE.isAfter(horaS))
                    horaSalidaValida = false;
            } catch (Exception e) {
                horaSalidaValida = false;
            }
        } catch (Exception e) {
            horaEntradaValida = false;
        }

        camposFecha.add(fecha);
        if (validadorDeReglas.validadorDeCamposVacios(camposTextField) || validadorDeReglas.validadorSelectorDeFechasVacios(camposFecha) || validadorDeReglas.validadorComboBoxVacios(camposComboBox)){
            Alert alertaCamposVacios = new Alert(Alert.AlertType.ERROR);
            alertaCamposVacios.setTitle("Error de campos vacíos");
            alertaCamposVacios.setContentText("Error, la propiedad o la fecha no pueden estar vacías");
            alertaCamposVacios.setHeaderText("Error de campos vacíos");
            alertaCamposVacios.showAndWait();
        }
        else if (validadorDeReglas.validadorDeCaracteresEspeciales(camposTextField)){
            Alert alertaNombreUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertaNombreUsuarioInvalido.setTitle("Error de de caracteres en la fecha");
            alertaNombreUsuarioInvalido.setContentText("Error, algunas de las hora tiene caracteres invalidos");
            alertaNombreUsuarioInvalido.setHeaderText("Error en las fechas");
            alertaNombreUsuarioInvalido.showAndWait();
        }
        else if (fecha.isBefore(LocalDate.now())) {
            Alert alertafechaInvalida = new Alert(Alert.AlertType.ERROR);
            alertafechaInvalida.setTitle("Error de fecha");
            alertafechaInvalida.setContentText("Error, la fecha no puede ser anterior al día de hoy");
            alertafechaInvalida.setHeaderText("Error en las fechas");
            alertafechaInvalida.showAndWait();
        }
        else if (!horaEntradaValida) {
            Alert alertahoraInvalida = new Alert(Alert.AlertType.ERROR);
            alertahoraInvalida.setTitle("Error de hora");
            alertahoraInvalida.setContentText("Error, la hora de entrada no es válida");
            alertahoraInvalida.setHeaderText("Error en la hora de entrada");
            alertahoraInvalida.showAndWait();

        }
        else if (!horaSalidaValida) {
            Alert alertahoraInvalida = new Alert(Alert.AlertType.ERROR);
            alertahoraInvalida.setTitle("Error de hora");
            alertahoraInvalida.setContentText("Error, la hora de salida no es válida");
            alertahoraInvalida.setHeaderText("Error en la hora de salida");
            alertahoraInvalida.showAndWait();

        }
        else if (!horaSalidaValida) {
            Alert alertahoraInvalida = new Alert(Alert.AlertType.ERROR);
            alertahoraInvalida.setTitle("Error de hora");
            alertahoraInvalida.setContentText("Error, la hora de salida no es válida");
            alertahoraInvalida.setHeaderText("Error en la hora de salida");
            alertahoraInvalida.showAndWait();

        }
        else {
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                VisitaDAO visitaDAO = new VisitaDAO();
                Usuario usuario = usuarioDAO.consultarUsuarioPorNombre(nombreUsuario);
                Cliente cliente =clienteDAO.consultarClientePorIdUsuario(usuario.getId());

                Visita visita = new Visita();
                visita.setCliente(cliente);
                visita.setFecha(Date.valueOf(fecha));
                visita.setPropiedad(propiedad);
                visita.setHoraEntrada(horaE.toString());
                visita.setHoraSalida(horaS.toString());
                visita.setEstado(1);
                List<Visita> listaVisitas = visitaDAO.consultarVisitas(cliente.getIdCliente());
                boolean traslape = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.nnn");
                for(Visita visita1 : listaVisitas){
                    LocalTime horaEntradVisita = LocalTime.parse(visita1.getHoraEntrada(),formatter);
                    LocalTime horaSalidaVisita = LocalTime.parse(visita1.getHoraSalida(),formatter);
                    LocalTime horaEntrada = LocalTime.parse(visita.getHoraEntrada(),formatter);
                    LocalTime horaSalida= LocalTime.parse(visita.getHoraSalida(),formatter);
                    java.util.Date fechaUtil = new java.util.Date(visita1.getFecha().getTime());
                    java.util.Date fechaUtil2 = new java.util.Date(visita.getFecha().getTime());
                    if(fechaUtil.toInstant().compareTo(fechaUtil2.toInstant()) == 0) {
                        if (horaEntradVisita.isBefore(horaEntrada) && horaSalidaVisita.isAfter(horaSalida) ||
                                horaEntradVisita.isAfter(horaEntrada) && horaSalidaVisita.isBefore(horaSalida) ||
                                horaEntradVisita.isBefore(horaEntrada) && horaSalidaVisita.isAfter(horaEntrada)
                        || horaEntrada.isAfter(horaEntradVisita)&& horaSalida.isBefore(horaSalidaVisita) ||
                        horaEntrada.isBefore(horaEntradVisita) && horaSalida.isBefore(horaSalidaVisita) && horaSalida.isAfter(horaEntradVisita)) {
                            CreadorAlertas creadorAlertas = new CreadorAlertas();
                            creadorAlertas.crearAlertaDeError("Ya hay una visita agendada en un horario similar", "Ya hay una visita agendada en un horario similar", "Ya hay una visita agendada en un horario similar");
                            return;
                        }
                    }
                }
                visitaDAO.insertarVisita(visita);
                Alert alertaGuardadoCorrecto = new Alert(Alert.AlertType.INFORMATION);
                alertaGuardadoCorrecto.setTitle("Éxito");
                alertaGuardadoCorrecto.setContentText("Información guardada");
                alertaGuardadoCorrecto.setHeaderText("La información de la visita se ha almacenado con éxito");
                alertaGuardadoCorrecto.showAndWait();

                cerrarVentana();
            } catch (Exception e) {
                Alert alertahoraInvalida = new Alert(Alert.AlertType.ERROR);
                alertahoraInvalida.setTitle("Error de BD");
                alertahoraInvalida.setContentText("Hubo un error al guardar la información en la BD");
                alertahoraInvalida.setHeaderText(e.toString());
                alertahoraInvalida.showAndWait();
            }
        }
    }

    private void cerrarVentana() {
        Scene escena = ComboBoxPropiedad.getScene();
        Stage stageVisita = (Stage) escena.getWindow();
        stageVisita.close();
        MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario, tipoUsuario);
    }

    public void onCancelarClick() {
        Alert confirmacionDeSalida = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacionDeSalida.setHeaderText("Salir del menú");
        confirmacionDeSalida.setContentText("¿Desea Salir del menú Agregar Visita?");
        Optional<ButtonType> botonSeleccionado = confirmacionDeSalida.showAndWait();
        if (botonSeleccionado.isPresent() && botonSeleccionado.get() == ButtonType.OK) {
            cerrarVentana();
        }

    }


}
