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
import mx.homek.gui.aplicaciones.LoginApplication;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModicarCuentaController implements Initializable {
    String nombreUsuaeio;
    String tipoUsuario;

    public String getNombreUsuaeio() {
        return nombreUsuaeio;
    }

    public void setNombreUsuaeio(String nombreUsuaeio) {
        this.nombreUsuaeio = nombreUsuaeio;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    private CreadorAlertas creadorAlertas;
    private ValidadorDeReglas validadorDeReglas;
    @FXML
    private TextField TextFieldNombre;
    @FXML
    private TextField TextFieldPrimerApellido;
    @FXML
    private TextField TextFieldSegundoApellido;
    @FXML
    private TextField TextFieldTelefono;
    @FXML
    Button ButtonAgregar;
    @FXML
    Button ButtonCancelar;
    @FXML
    private TextField TextFieldCorreo;
    @FXML
    private TextField TextFieldNombreUsuario;
    @FXML
    private PasswordField PasswordFieldContraseña;
    @FXML
    private ComboBox ComboBoxUniversidad;
    @FXML
    private ComboBox comboBoxEstadoCivil;
    @FXML
    private DatePicker datePickerFecha;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creadorAlertas = new CreadorAlertas();
        validadorDeReglas = new ValidadorDeReglas();
        validadorDeReglas.limitarCampoTexto(TextFieldNombre);
        validadorDeReglas.limitarCampoTexto(TextFieldPrimerApellido);
        validadorDeReglas.limitarCampoTexto(TextFieldSegundoApellido);
        validadorDeReglas.limitarCampoNumerico(TextFieldTelefono);
        validadorDeReglas.agregarLimiteATextField(TextFieldNombre,30);
        validadorDeReglas.agregarLimiteATextField(TextFieldNombreUsuario,30);
        validadorDeReglas.agregarLimiteATextField(TextFieldCorreo,30);
        validadorDeReglas.agregarLimiteATextField(TextFieldTelefono,10);
        validadorDeReglas.agregarLimiteATextField(TextFieldPrimerApellido,30);
        validadorDeReglas.agregarLimiteATextField(TextFieldSegundoApellido,30);
        validadorDeReglas.agregarLimiteAPasswordField(PasswordFieldContraseña,30);
        ObservableList <String> estados = FXCollections.observableArrayList();
        estados.add("Soltero");
        estados.add("Casado");
        estados.add("Divorciado");
        estados.add("Unión libre");
        estados.add("Viudo");
        comboBoxEstadoCivil.setItems(estados);
        ObservableList<String> listaUniversidadesDisponibles = FXCollections.observableArrayList();
        listaUniversidadesDisponibles.add("Masculino");
        listaUniversidadesDisponibles.add("Femenino");
        ComboBoxUniversidad.setItems(listaUniversidadesDisponibles);
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
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
    public void setCampos(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Cliente cliente = new Cliente();
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            int id = usuarioDAO.obtenerIDUsuarioPorNombre(nombreUsuaeio);
            cliente = clienteDAO.consultarClientePorIdUsuario(id);
        }
        catch (SQLException sqlException){
            creadorAlertas.crearAlertaDeAdvertencia("Error en la consulta","Error en la consulta","Error");
        }
        TextFieldNombreUsuario.setText(cliente.getUsuario().getNombreUsuario());
        ComboBoxUniversidad.setValue(cliente.getSexo());
        TextFieldNombreUsuario.setDisable(true);
        TextFieldNombre.setText(cliente.getNombre());
        TextFieldCorreo.setText(cliente.getCorreo());
        TextFieldCorreo.setDisable(true);
        comboBoxEstadoCivil.setValue(cliente.getEstadoCivil());
        PasswordFieldContraseña.setText(cliente.getUsuario().getContrasena());
        TextFieldTelefono.setText(cliente.getTelefono());
        TextFieldPrimerApellido.setText(cliente.getApellidoPaterno());
        TextFieldSegundoApellido.setText(cliente.getApellidoMaterno());
        java.util.Date fechaUtil = new java.util.Date(cliente.getFechaNacimiento().getTime());
        Instant instant = fechaUtil.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        datePickerFecha.setValue(localDate);
    }
    public void onAgregarClick(){
        ArrayList<String> campos = new ArrayList<>();
        ArrayList <String> camposCombo = new ArrayList<>();
        String nombre = TextFieldNombre.getText();
        String apellidoPaterno = TextFieldPrimerApellido.getText();
        String apellidoMaterno = TextFieldSegundoApellido.getText();
        String universidad = (String) ComboBoxUniversidad.getValue();
        String estadoVicil = (String) comboBoxEstadoCivil.getValue();
        String telefono = TextFieldTelefono.getText();
        String correo = TextFieldCorreo.getText();
        LocalDate fecha1 = datePickerFecha.getValue();
        ZonedDateTime zonedDateTime = fecha1.atStartOfDay(ZoneId.systemDefault());
        Date fecha = Date.from(zonedDateTime.toInstant());
        String nombreUsuario = TextFieldNombreUsuario.getText();
        String contraseña = PasswordFieldContraseña.getText();
        campos.add(nombre);
        campos.add(apellidoMaterno);
        campos.add(apellidoPaterno);
        camposCombo.add(universidad);
        campos.add(correo);
        camposCombo.add(estadoVicil);
        campos.add(telefono);
        campos.add(nombreUsuario);
        campos.add(contraseña);
        if(validadorDeReglas.validadorDeCamposVacios(campos) || validadorDeReglas.validadorComboBoxVacios(camposCombo)) {
            Alert alertaCamposVacios = new Alert(Alert.AlertType.ERROR);
            alertaCamposVacios.setTitle("Error de campos vacíos");
            alertaCamposVacios.setContentText("Error, el usuario o la contraseña están vacíos");
            alertaCamposVacios.setHeaderText("Error de campos vacíos");
            alertaCamposVacios.showAndWait();
        }
        else if(validadorDeReglas.validadorDeCaracteresEspeciales(campos)){
            Alert alertaNombreUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertaNombreUsuarioInvalido.setTitle("Error de nombre de usuario");
            alertaNombreUsuarioInvalido.setContentText("Error, nombre de usuario con caracteres invalidos");
            alertaNombreUsuarioInvalido.setHeaderText("Error de nombre de usuario");
            alertaNombreUsuarioInvalido.showAndWait();
        }
        else{
            try{
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = new Usuario();
                usuario.setContrasena(contraseña);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setTipoUsuario("Cliente");
                Cliente cliente = new Cliente();
                cliente.setCorreo(correo);
                cliente.setApellidoMaterno(apellidoMaterno);
                cliente.setApellidoPaterno(apellidoPaterno);
                cliente.setNombre(nombre);
                cliente.setSexo(universidad);
                cliente.setTelefono(telefono);
                cliente.setFechaNacimiento(fecha);
                cliente.setUsuario(usuario);
                cliente.setEstadoCivil(estadoVicil);
                ClienteDAO clienteDAO = new ClienteDAO();
                if(validadorDeReglas.verificadorDeContraseña(usuario.getContrasena())){
                    Alert alertaContraseñaInsegura = new Alert(Alert.AlertType.WARNING);
                    alertaContraseñaInsegura.setTitle("Contraseña insegura");
                    alertaContraseñaInsegura.setContentText("La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número, u  caracter especial y 8 caracteres en total");
                    alertaContraseñaInsegura.setHeaderText("La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número, u  caracter especial y 8 caracteres en total");
                    alertaContraseñaInsegura.showAndWait();
                }
                else {
                    if (usuarioDAO.modificarUsuario(usuario) > 0 && clienteDAO.modificarCliente(cliente) > 0) {

                        Alert alertaRegistroExitoso = new Alert(Alert.AlertType.INFORMATION);
                        alertaRegistroExitoso.setTitle("Modificación exitosa");
                        alertaRegistroExitoso.setContentText("El Cliente se ha modificado con éxito");
                        alertaRegistroExitoso.setHeaderText("Modificación exitosa");
                        alertaRegistroExitoso.showAndWait();
                        Scene escena = TextFieldNombre.getScene();
                        Stage stageAgregarProfesorUV = (Stage) escena.getWindow();
                        stageAgregarProfesorUV.close();
                        MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);

                    }
                    else{
                        Alert alertaRegistroExitoso = new Alert(Alert.AlertType.ERROR);
                        alertaRegistroExitoso.setTitle("Registro fallido");
                        alertaRegistroExitoso.setContentText("El profesor no se ha podido añadir");
                        alertaRegistroExitoso.setHeaderText("Registro fallido");
                        alertaRegistroExitoso.showAndWait();
                    }
                }
            }
            catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
                Alert alertaNoHayConexionConLaBaseDeDatos = new Alert(Alert.AlertType.ERROR);
                alertaNoHayConexionConLaBaseDeDatos.setTitle("Sin conexion a la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.setContentText("No se ha podido establecer conexion con la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.setHeaderText("Sin conexion a la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.showAndWait();
            }
        }
    }
    public void onCancelarClick(){
        Alert confirmacionDeSalida = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacionDeSalida.setHeaderText("Salir del menú");
        confirmacionDeSalida.setContentText("¿Desea Salir del menú Agregar Profesor Externo?");
        Optional<ButtonType> botonSeleccionado = confirmacionDeSalida.showAndWait();
        if (botonSeleccionado.isPresent() && botonSeleccionado.get() == ButtonType.OK) {

            Scene escena = TextFieldNombre.getScene();
            Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
            stageAgregarProfesorExterno.close();
            MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuaeio,tipoUsuario);
        }
    }
}
