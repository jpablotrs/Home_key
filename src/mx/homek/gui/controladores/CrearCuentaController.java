package mx.homek.gui.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.LoginApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Usuario;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrearCuentaController implements Initializable {
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
    private TextField textFieldEstadoCivil;
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
        validadorDeReglas.agregarLimiteATextField(textFieldEstadoCivil,12);
        validadorDeReglas.agregarLimiteAPasswordField(PasswordFieldContraseña,30);
        ObservableList<String> listaUniversidadesDisponibles = FXCollections.observableArrayList();
        listaUniversidadesDisponibles.add("Masculino");
        listaUniversidadesDisponibles.add("Femenino");
        ComboBoxUniversidad.setItems(listaUniversidadesDisponibles);
        validadorDeReglas.proponerNombreUsuario(TextFieldNombre,TextFieldPrimerApellido,TextFieldSegundoApellido,TextFieldNombreUsuario);
    }
    public void onAgregarClick(){
        ArrayList <String> campos = new ArrayList<>();
        ArrayList <String> camposCombo = new ArrayList<>();
        String nombre = TextFieldNombre.getText();
        String apellidoPaterno = TextFieldPrimerApellido.getText();
        String apellidoMaterno = TextFieldSegundoApellido.getText();
        String universidad = (String) ComboBoxUniversidad.getValue();
        String estadoVicil = textFieldEstadoCivil.getText();
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
        campos.add(estadoVicil);
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
                if(usuarioDAO.existeNombreUsuario(usuario.getNombreUsuario())){
                    Alert alertaNoHayConexionConLaBaseDeDatos = new Alert(Alert.AlertType.WARNING);
                    alertaNoHayConexionConLaBaseDeDatos.setTitle("Usuario ya ocupado");
                    alertaNoHayConexionConLaBaseDeDatos.setContentText("El nombre de usuario y/o la contraseña ya están ocupadas");
                    alertaNoHayConexionConLaBaseDeDatos.setHeaderText("Pruebe con otro nombre de usuario");
                    alertaNoHayConexionConLaBaseDeDatos.showAndWait();
                }
                else if(cliente.getCorreo().indexOf("@")<0){
                    Alert alertaNoHayConexionConLaBaseDeDatos = new Alert(Alert.AlertType.WARNING);
                    alertaNoHayConexionConLaBaseDeDatos.setTitle("Correo con formato incorrecto");
                    alertaNoHayConexionConLaBaseDeDatos.setContentText("El correo no tiene el formato esperado");
                    alertaNoHayConexionConLaBaseDeDatos.setHeaderText("Correo con formato inadecuado");
                    alertaNoHayConexionConLaBaseDeDatos.showAndWait();
                }
                else if(contraseña.length() < 8){
                    Alert alertaContraseñaInsegura = new Alert(Alert.AlertType.WARNING);
                    alertaContraseñaInsegura.setTitle("Contraseña muy corta");
                    alertaContraseñaInsegura.setContentText("Contraseña insegura");
                    alertaContraseñaInsegura.setHeaderText("debe contener por lo menos 8 caracteres");
                    alertaContraseñaInsegura.showAndWait();
                }
                else {
                    if (usuarioDAO.insertarUsuario(usuario) > 0 && clienteDAO.insertarCliente(cliente) > 0) {

                        Alert alertaRegistroExitoso = new Alert(Alert.AlertType.INFORMATION);
                        alertaRegistroExitoso.setTitle("Registro exitoso");
                        alertaRegistroExitoso.setContentText("El Cliente ha sido registrado con éxito");
                        alertaRegistroExitoso.setHeaderText("Registro de Cliente exitoso");
                        alertaRegistroExitoso.showAndWait();
                            Scene escena = TextFieldNombre.getScene();
                            Stage stageAgregarProfesorUV = (Stage) escena.getWindow();
                            stageAgregarProfesorUV.close();
                            LoginApplication loginApplication = new LoginApplication();

                    }
                    else{
                        Alert alertaRegistroExitoso = new Alert(Alert.AlertType.ERROR);
                        alertaRegistroExitoso.setTitle("Registro fallido");
                        alertaRegistroExitoso.setContentText("El Cliente no se ha podido añadir");
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


    public void onCancelarClick() {
        Alert confirmacionDeSalida = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacionDeSalida.setHeaderText("Salir del menú");
        confirmacionDeSalida.setContentText("¿Desea Salir del menú Agregar Profesor Externo?");
        Optional<ButtonType> botonSeleccionado = confirmacionDeSalida.showAndWait();
        if (botonSeleccionado.isPresent() && botonSeleccionado.get() == ButtonType.OK) {

                Scene escena = TextFieldNombre.getScene();
                Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
                stageAgregarProfesorExterno.close();
                LoginApplication loginApplication = new LoginApplication();
        }
    }
}

