package mx.homek.gui.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import javax.swing.*;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarPropiedadControlador implements Initializable {
    @FXML
    public TextField TextFieldClaveCatastral;
    public CheckBox checkBoxGarageSi;
    public TextField NoAutos;
    public CheckBox checkBoxGarageNo;
    public CheckBox checkBoxAmuebladoSi;
    public CheckBox checkBoxAmuebladoNo;
    public CheckBox checkBoxAlquilerSi;
    public CheckBox checkBoxAlquilerNo;
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
    CreadorAlertas creadorAlertas = new CreadorAlertas();
    @FXML
    public Label LabelClaveCatastral;

    @FXML
    private TextField TextFieldDireccion;
    ValidadorDeReglas validadorDeReglas = new ValidadorDeReglas();
    @FXML
    private TextField TextFieldCiudad;

    @FXML
    private TextField TextFieldEstado;

    @FXML
    private TextField TextFieldCP;

    @FXML
    private TextField NumeroHabitaciones;

    @FXML
    private TextField NoBanos;

    @FXML
    private TextField NoPisos;

    @FXML
    private TextField NoCocina;

    @FXML
    private TextField MetrosCuadrados;

    @FXML
    private TextField NoPersonas;

    @FXML
    private TextField Alquiler;

    @FXML
    private TextField Compra;

    @FXML
    private TextField Electricidad;

    @FXML
    private TextField Amueblado;

    @FXML
    private TextField Foto;

    @FXML
    private Button ButtonAgregar;

    @FXML
    private Button ButtonCancelar;

    @FXML
    void checkBoxGarageSi(ActionEvent event){
        if(checkBoxGarageSi.isSelected()){
            checkBoxGarageNo.setSelected(false); // Desactiva el otro checkbox
            checkBoxGarageNo.setDisable(true);
        } else {
            checkBoxGarageNo.setDisable(false);
        }
    }

    @FXML
    void checkBoxGarageNo(ActionEvent event){
        if(checkBoxGarageNo.isSelected()){
            checkBoxGarageSi.setSelected(false); // Desactiva el otro checkbox
            checkBoxGarageSi.setDisable(true);
        } else {
            checkBoxGarageSi.setDisable(false);
        }
    }

    @FXML
    void checkBoxAmuebladoSi(ActionEvent event){
        if(checkBoxAmuebladoSi.isSelected()){
            checkBoxAmuebladoNo.setSelected(false); // Desactiva el otro checkbox
            checkBoxAmuebladoNo.setDisable(true);
        } else {
            checkBoxAmuebladoNo.setDisable(false);
        }
    }

    @FXML
    void checkBoxAmuebladoNo(ActionEvent event){
        if(checkBoxAmuebladoNo.isSelected()){
            checkBoxAmuebladoSi.setSelected(false); // Desactiva el otro checkbox
            checkBoxAmuebladoSi.setDisable(true);
        } else {
            checkBoxAmuebladoSi.setDisable(false);
        }
    }

    @FXML
    void checkBoxAlquilerSi(ActionEvent event){
        if(checkBoxAlquilerSi.isSelected()){
            checkBoxAlquilerNo.setSelected(false); // Desactiva el otro checkbox
            checkBoxAlquilerNo.setDisable(true);
            Alquiler.setVisible(true); // Muestra el TextField Alquiler
        } else {
            checkBoxAlquilerNo.setDisable(false);
            Alquiler.setVisible(false); // Muestra el TextField Alquiler

        }
    }

    @FXML
    void checkBoxAlquilerNo(ActionEvent event){
        if(checkBoxAlquilerNo.isSelected()){
            checkBoxAlquilerSi.setSelected(false); // Desactiva el otro checkbox
            checkBoxAlquilerSi.setDisable(true);
            Alquiler.setVisible(false); // Oculta el TextField Alquiler
        } else {
            checkBoxAlquilerSi.setDisable(false);
        }
    }

    @FXML
    private void onAgregarClick(ActionEvent event) {
        try {

            PropiedadDAO propiedadDAO = new PropiedadDAO();

            Propiedad propiedad = new Propiedad();
            propiedad.setDireccion(TextFieldDireccion.getText());
            propiedad.setCiudad(TextFieldCiudad.getText());
            propiedad.setEstado(TextFieldEstado.getText());
            propiedad.setCodigoPostal(TextFieldCP.getText());
            propiedad.setNumeroHabitaciones(Integer.parseInt(NumeroHabitaciones.getText()));
            propiedad.setNumeroBanos(Integer.parseInt(NoBanos.getText()));
            propiedad.setNumeroPisos(Integer.parseInt(NoPisos.getText()));
            propiedad.setNumeroCocina(Integer.parseInt(NoCocina.getText()));
            propiedad.setMetrosCuadrados(Integer.parseInt(MetrosCuadrados.getText()));
            propiedad.setNumeroPersonas(Integer.parseInt(NoPersonas.getText()));
            if(checkBoxAlquilerSi.isSelected()){
            propiedad.setAlquiler(Integer.parseInt(Alquiler.getText()));
            }
            propiedad.setCompra(Integer.parseInt(Compra.getText()));
            if(checkBoxAmuebladoSi.isSelected()){
            propiedad.setAmueblado(1);
            }else if(checkBoxAmuebladoNo.isSelected()){
                propiedad.setAmueblado(0);
            }
            if(checkBoxGarageSi.isSelected()){
                propiedad.setGarage(1);
            }else if(checkBoxGarageNo.isSelected()){
                propiedad.setGarage(0);
            }
            propiedad.setNumeroAutos(Integer.parseInt(NoAutos.getText()));

            propiedad.setClaveCatastral(TextFieldClaveCatastral.getText());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            int idUsuario = usuarioDAO.obtenerIDUsuarioPorNombre(nombreUsuario);
            Cliente cliente1 = clienteDAO.consultarClientePorIdUsuario(idUsuario);
            int idCLiente = clienteDAO.consultarIDClientePorCorreo(cliente1.getCorreo());
            int idGenerado = propiedadDAO.agregarPropiedad(propiedad,idCLiente);
            if (idGenerado != -1) {
                creadorAlertas.crearAlertaDeInformacion("Registro de propiedad exitoso","Registro exitoso","Exito");
                Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
                stage.close();
                MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);

            } else {
                System.out.println("Error al agregar la propiedad.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onCancelarClick(ActionEvent event) {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
        MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validadorDeReglas.agregarLimiteATextField(TextFieldCiudad,20);
        validadorDeReglas.agregarLimiteATextField(TextFieldCP,8);
        validadorDeReglas.agregarLimiteATextField(TextFieldEstado,20);
        validadorDeReglas.agregarLimiteATextField(TextFieldDireccion,30);
        validadorDeReglas.agregarLimiteATextField(TextFieldClaveCatastral,20);
        validadorDeReglas.agregarLimiteATextField(NoBanos,2);
        validadorDeReglas.agregarLimiteATextField(NoCocina,1);
        validadorDeReglas.agregarLimiteATextField(NoPersonas,4);
        validadorDeReglas.agregarLimiteATextField(MetrosCuadrados,8);
        validadorDeReglas.agregarLimiteATextField(Compra,12);
        validadorDeReglas.agregarLimiteATextField(Alquiler,12);
        validadorDeReglas.limitarCampoNumerico(MetrosCuadrados);
        validadorDeReglas.limitarCampoNumerico(NoPersonas);
        validadorDeReglas.limitarCampoNumerico(TextFieldCP);
        validadorDeReglas.limitarCampoNumerico(NoCocina);
        validadorDeReglas.limitarCampoNumerico(NoPisos);
        validadorDeReglas.limitarCampoNumerico(NoBanos);
        validadorDeReglas.agregarLimiteATextField(NumeroHabitaciones,3);
        validadorDeReglas.limitarCampoNumerico(NumeroHabitaciones);
        validadorDeReglas.limitarCampoNumerico(Alquiler);
        validadorDeReglas.limitarCampoNumerico(Compra);
        validadorDeReglas.limitarCampoTexto(TextFieldCiudad);
        validadorDeReglas.limitarCampoTexto(TextFieldEstado);
        validadorDeReglas.agregarLimiteATextField(NoPisos,2);
        validadorDeReglas.limitarCampoNumerico(NoPisos);
        validadorDeReglas.limitarCampoNumerico(NoAutos);
        Alquiler.setVisible(false);
    }
}
