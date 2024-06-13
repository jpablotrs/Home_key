package mx.homek.gui.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.implementaciones.VisitaDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Usuario;
import mx.homek.logic.objetoDeTransferencia.Visita;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CancelarVisitaControlador implements Initializable {
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
    private ComboBox ComboBoxVisita;
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

    public void setCampos() {
        try{
            PropiedadDAO propiedadDAO = new PropiedadDAO();
            List<Propiedad> propiedades =propiedadDAO.buscarPorCiudad("a");
            ObservableList<Propiedad> listaPropiedades = propiedadDAO.consultarPropiedadesObs(nombreUsuario);
            for(Propiedad propiedad:propiedades){
                if(propiedad.getIdCliente().getUsuario().getNombreUsuario().equals(nombreUsuario)){
                    listaPropiedades.add(propiedad);
                }
            }
            ComboBoxPropiedad.setItems(listaPropiedades);
            ComboBoxPropiedad.setDisable(true);


            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            Usuario usuario = usuarioDAO.consultarUsuarioPorNombre(nombreUsuario);
            Cliente cliente = clienteDAO.consultarClientePorIdUsuario(usuario.getId());
            VisitaDAO visitaDAO = new VisitaDAO();
            ObservableList<Visita> listaVisitas = FXCollections.observableArrayList(visitaDAO.consultarVisitas(cliente.getIdCliente()));
            ComboBoxVisita.setItems(listaVisitas);

            DatePickerFecha.setDisable(true);
            TextFieldHoraEntrada.setDisable(true);
            TextFieldHoraSalida.setDisable(true);

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
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR_MINUS_ALT);
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

    Visita visita = null;

    public void visitaOnAction() {
        visita = (Visita) ComboBoxVisita.getValue();
        ObservableList<Propiedad> listaPropiedades = ComboBoxPropiedad.getItems();
        Propiedad propiedad = listaPropiedades.filtered(p -> p.getIdPropiedad() == visita.getPropiedad().getIdPropiedad()).get(0);
        ComboBoxPropiedad.setValue(propiedad);
        TextFieldHoraEntrada.setText(visita.getHoraEntrada());
        TextFieldHoraSalida.setText(visita.getHoraSalida());

        java.util.Date fecha = new java.util.Date(visita.getFecha().getTime());
        DatePickerFecha.setValue(fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    public void onAgregarClick() {
        ArrayList<String> camposTextField = new ArrayList<>();
        ArrayList<String> camposComboBox = new ArrayList<>();
        ArrayList <LocalDate> camposFecha = new ArrayList<>();
        String horaEntrada = TextFieldHoraEntrada.getText();
        String horaSalida = TextFieldHoraSalida.getText();
        camposTextField.add(horaEntrada);
        camposTextField.add(horaSalida);
        Propiedad propiedad = (Propiedad) ComboBoxPropiedad.getValue();
        //String cliente = (String) ComboBoxCliente.getValue();
        //camposComboBox.add(cliente);
        camposComboBox.add(propiedad!=null?propiedad.toString():null);
        LocalDate fecha = DatePickerFecha.getValue();
        boolean horaEntradaValida = true;
        boolean horaSalidaValida = true;
        try {
            var horaE = LocalTime.parse(horaEntrada);
            if (horaE.getHour() < 8 || horaE.getHour() > 20)
                horaEntradaValida = false;
            try {
                var horaS = LocalTime.parse(horaSalida);
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
        else {
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                VisitaDAO visitaDAO = new VisitaDAO();
                Usuario usuario = usuarioDAO.consultarUsuarioPorNombre(nombreUsuario);
                Cliente cliente =clienteDAO.consultarClientePorIdUsuario(usuario.getId());

                visita.setCliente(cliente);
                visita.setFecha(Date.valueOf(fecha));
                visita.setPropiedad(propiedad);
                visita.setHoraEntrada(horaEntrada);
                visita.setHoraSalida(horaSalida);
                visita.setEstado(1);
                visitaDAO.cancelarVisita(visita);

                Alert alertaGuardadoCorrecto = new Alert(Alert.AlertType.INFORMATION);
                alertaGuardadoCorrecto.setTitle("Visita cancelada");
                alertaGuardadoCorrecto.setContentText("La visita ha sido cancelada exitosamente");
                alertaGuardadoCorrecto.setHeaderText("Visita cancelada exitosamente");
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
        confirmacionDeSalida.setContentText("¿Desea Salir del menú Cancelar Visita?");
        Optional<ButtonType> botonSeleccionado = confirmacionDeSalida.showAndWait();
        if (botonSeleccionado.isPresent() && botonSeleccionado.get() == ButtonType.OK) {
            cerrarVentana();
        }

    }


}
