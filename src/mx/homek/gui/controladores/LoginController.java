package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Usuario;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField TextFieldUsuario;
    private CreadorAlertas creadorAlertas;
    @FXML
    private PasswordField PasswordFieldContraseña;
    @FXML
    private Button ButtonIngresar;
    private ValidadorDeReglas validadorDeReglas;
    @FXML
    public void onIngresarClick(){
        String nombreUsuario = TextFieldUsuario.getText();
        String contraseña = PasswordFieldContraseña.getText();
        ArrayList<String> campos = new ArrayList<>();
        campos.add(nombreUsuario);
        campos.add(contraseña);
        if(validadorDeReglas.validadorDeCamposVacios(campos)) {
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
                if(!usuarioDAO.verificarUsuarioExistente(usuario.getNombreUsuario(),usuario.getContrasena())){
                    Alert alertaUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
                    alertaUsuarioInvalido.setTitle("Usuario no encontrado");
                    alertaUsuarioInvalido.setContentText("EL nombre de usuario y/o la contraseña son inválidos");
                    alertaUsuarioInvalido.setHeaderText("Usuario invalido");
                    alertaUsuarioInvalido.showAndWait();
                }
                else{
                        Scene escena = TextFieldUsuario.getScene();
                        Stage stageLogin = (Stage) escena.getWindow();
                        stageLogin.close();
                        String tipoUsuario = usuarioDAO.consultarTipoUsuarioPorNombre(usuario.getNombreUsuario());
                    MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);
                }
            }
            catch(SQLException sqlException){
                Alert alertaNoHayConexionConLaBaseDeDatos = new Alert(Alert.AlertType.ERROR);
                alertaNoHayConexionConLaBaseDeDatos.setTitle("Sin conexion a la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.setContentText("No se ha podido establecer conexion con la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.setHeaderText("Sin conexion a la base de datos");
                alertaNoHayConexionConLaBaseDeDatos.showAndWait();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creadorAlertas = new CreadorAlertas();
        validadorDeReglas = new ValidadorDeReglas();
        TextFieldUsuario.setPromptText("Nombre De usuario aquí");
        PasswordFieldContraseña.setPromptText("contraseña");
        validadorDeReglas.agregarLimiteATextField(TextFieldUsuario, 30);
        validadorDeReglas.agregarLimiteAPasswordField(PasswordFieldContraseña, 30);
    }
    public void onCrearCuentaClick(){

    }
}
