package mx.homek.gui.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.EstadoDAO;
import mx.homek.logic.implementaciones.MunicipioDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModificarPropiedadControlador implements Initializable {
    @FXML
    public TextField TextFieldClaveCatastral;

    @FXML
    public Label LabelClaveCatastral;
    public TextField NoAutos;
    public CheckBox checkBoxGarageSi;
    public CheckBox checkBoxGarageNo;
    public CheckBox checkBoxAmuebladoSi;
    public CheckBox checkBoxAmuebladoNo;
    public CheckBox checkBoxAlquilerNo;
    public CheckBox checkBoxAlquilerSi;

    @FXML
    private TextField TextFieldDireccion;


    @FXML
    private TextField TextFieldCP;
    @FXML
    private ComboBox comboBoxEstado;
    @FXML
    private ComboBox comboBoxMunicipio;

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
    private Button ButtonModificar;

    @FXML
    private Button ButtonCancelar;

    private String claveCatastral;

    @FXML
    void checkBoxGarageSi(ActionEvent event){
        if(checkBoxGarageSi.isSelected()){
            checkBoxGarageNo.setSelected(false); // Desactiva el otro checkbox
            checkBoxGarageNo.setDisable(true);
            NoAutos.setDisable(false);
        } else {
            checkBoxGarageNo.setDisable(false);
            NoAutos.setDisable(true);
        }
    }

    @FXML
    void checkBoxGarageNo(ActionEvent event){
        if(checkBoxGarageNo.isSelected()){
            checkBoxGarageSi.setSelected(false); // Desactiva el otro checkbox
            checkBoxGarageSi.setDisable(true);
            NoAutos.setDisable(true);
            NoAutos.setText("");
        } else {
            checkBoxGarageSi.setDisable(false);
        }
    }

    @FXML
    void checkBoxAmuebladoSi(ActionEvent event){
        if(checkBoxAmuebladoSi.isSelected()){
            checkBoxAmuebladoNo.setSelected(false); // Desactiva el otro checkbox
            checkBoxAmuebladoNo.setDisable(true);
            NoAutos.setDisable(false);
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



    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
        llenarVentana(claveCatastral);
    }

    @FXML
    private void onGuardarClick(ActionEvent event) {
        CreadorAlertas creadorAlertas = new CreadorAlertas();
        try {
            if (comboBoxEstado.getValue() == null || TextFieldDireccion.getText().equals("") || TextFieldCP.getText().equals("") || NumeroHabitaciones.getText().equals("")
                    || NoBanos.getText().equals("") || NoCocina.getText().equals("") || NoCocina.getText().equals("") || NoPersonas.getText().equals("") || comboBoxMunicipio.getValue() == null) {
                creadorAlertas.crearAlertaDeError("Campos vacíos", "Error, hay uno o más campos vacíos", "Error campos vacíos");
            } else if (Integer.parseInt(NoPisos.getText()) == 0) {
                creadorAlertas.crearAlertaDeAdvertencia("No puede agregarse una propiedad con 0 pisos", "No se puede agregar una propiedad con 0 pisos", "Ninguna propiedad puede tener 0 pisos");
            } else if (Integer.parseInt(MetrosCuadrados.getText()) < 120) {
                creadorAlertas.crearAlertaDeAdvertencia("Norma de desarrollo urbano", "Propiedad sin el mínimo de metros necesarios", "Una propiedad según la norma mexicana de desarrollo urbano no puede tener menos de 120 metros cuadrados, para poder considerarse como propiedad");
            } else if (Integer.parseInt(Compra.getText()) < 100000) {
                creadorAlertas.crearAlertaDeAdvertencia("Propiedad de muy bajo costo", "Su propiedad debe costar por lo menos 100,000 pesos", "En México las propiedades no bajan de entre los 100,000 a 200,000 pesos mexicanos");
            } else {
                PropiedadDAO propiedadDAO = new PropiedadDAO();
                Propiedad propiedad = new Propiedad();
                propiedad.setDireccion(TextFieldDireccion.getText());
                propiedad.setCiudad((String) comboBoxMunicipio.getValue());
                propiedad.setEstado((String) comboBoxEstado.getValue());
                propiedad.setCodigoPostal(TextFieldCP.getText());
                propiedad.setNumeroHabitaciones(Integer.parseInt(NumeroHabitaciones.getText()));
                propiedad.setNumeroBanos(Integer.parseInt(NoBanos.getText()));
                propiedad.setNumeroPisos(Integer.parseInt(NoPisos.getText()));
                propiedad.setNumeroCocina(Integer.parseInt(NoCocina.getText()));
                propiedad.setMetrosCuadrados(Integer.parseInt(MetrosCuadrados.getText()));
                propiedad.setNumeroPersonas(Integer.parseInt(NoPersonas.getText()));
                if (checkBoxAlquilerSi.isSelected() ) {
                    if(!Alquiler.getText().equals(""))
                    propiedad.setAlquiler(Integer.parseInt(Alquiler.getText()));
                    else {
                        creadorAlertas.crearAlertaDeError("Error campos vacíos", "Error el alquiler está vacío", "Error el alquiler está vacío");
                        return;
                    }
                } else if (checkBoxAlquilerNo.isSelected()) {
                    propiedad.setAlquiler(0);
                }
                propiedad.setCompra(Integer.parseInt(Compra.getText()));
                if (checkBoxAmuebladoSi.isSelected()) {
                    propiedad.setAmueblado(1);
                } else if (checkBoxAmuebladoNo.isSelected()) {
                    propiedad.setAmueblado(0);
                }
                if (checkBoxGarageSi.isSelected()) {
                    propiedad.setGarage(1);
                } else if (checkBoxGarageNo.isSelected()) {
                    propiedad.setGarage(0);
                }
                if(checkBoxGarageSi.isSelected())
                propiedad.setNumeroAutos(Integer.parseInt(NoAutos.getText()));
                else
                    propiedad.setNumeroAutos(0);
                propiedad.setClaveCatastral(TextFieldClaveCatastral.getText());
                int resultado = propiedadDAO.modificarPropiedad(propiedad);
                if (resultado != 1) {
                    creadorAlertas.crearAlertaDeInformacion("Modificación realizada con éxito","La propiedad fue modificada con éxito","Su propiedad se modificó exitosamente");
                    Stage stage = (Stage) ButtonModificar.getScene().getWindow();
                    stage.close();
                } else {
                    System.out.println("Error al modificar la propiedad.");
                }

            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }

    }

    @FXML
    public void llenarVentana(String claveCatastral) {
        try {
            Propiedad propiedad = new Propiedad();
            PropiedadDAO propiedadDAO = new PropiedadDAO();
            propiedad = propiedadDAO.obtenerPropiedadPorClaveCatastral(claveCatastral);

            if (propiedad != null) {
                TextFieldDireccion.setText(propiedad.getDireccion());
                MunicipioDAO municipioDAO = new MunicipioDAO();
                EstadoDAO estadoDAO = new EstadoDAO();
                comboBoxEstado.setValue(propiedad.getEstado());
                comboBoxMunicipio.setValue(propiedad.getCiudad());
                String nombre2 = (String) comboBoxEstado.getValue();
                comboBoxMunicipio.setItems(municipioDAO.obtenerMunicipioPorEstado(estadoDAO.obtenerIdEstado(nombre2)));
                TextFieldCP.setText(propiedad.getCodigoPostal());
                NumeroHabitaciones.setText(String.valueOf(propiedad.getNumeroHabitaciones()));
                NoBanos.setText(String.valueOf(propiedad.getNumeroBanos()));
                NoPisos.setText(String.valueOf(propiedad.getNumeroPisos()));
                NoCocina.setText(String.valueOf(propiedad.getNumeroCocina()));
                MetrosCuadrados.setText(String.valueOf(propiedad.getMetrosCuadrados()));
                NoPersonas.setText(String.valueOf(propiedad.getNumeroPersonas()));
                Compra.setText(String.valueOf(propiedad.getCompra()));
                if(propiedad.getAlquiler()>0){
                    checkBoxAlquilerNo.setDisable(true);
                    checkBoxAlquilerSi.setSelected(true);
                    Alquiler.setVisible(true);
                    Alquiler.setText(String.valueOf(propiedad.getAlquiler()));
                }else{
                    checkBoxAlquilerSi.setDisable(true);
                    checkBoxAlquilerNo.setSelected(true);

                }
                if(propiedad.getGarage() == 1){
                    checkBoxGarageNo.setDisable(true);
                    checkBoxGarageSi.setSelected(true);
                }else{
                    checkBoxGarageSi.setDisable(true);
                    checkBoxGarageNo.setSelected(true);
                }
                if(propiedad.getAmueblado() == 1){
                    checkBoxAmuebladoNo.setDisable(true);
                    checkBoxAmuebladoSi.setSelected(true);
                }else{
                    checkBoxAmuebladoSi.setDisable(true);
                    checkBoxAmuebladoNo.setSelected(true);
                }
                if(propiedad.getNumeroAutos() > 0) {
                    NoAutos.setText(String.valueOf(propiedad.getNumeroAutos()));
                    checkBoxGarageSi.setSelected(true);
                    checkBoxGarageNo.setDisable(false);
                }
                else{
                    checkBoxGarageNo.setSelected(true);
                    checkBoxGarageSi.setDisable(false);
                    NoAutos.setDisable(true);
                }
                TextFieldClaveCatastral.setText(String.valueOf(propiedad.getClaveCatastral()));
            } else {
                System.out.println("No se encontró ninguna propiedad con la clave catastral proporcionada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelarClick(ActionEvent event) {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ValidadorDeReglas validadorDeReglas = new ValidadorDeReglas();
        validadorDeReglas.agregarLimiteATextField(TextFieldCP,8);
        validadorDeReglas.agregarLimiteATextField(TextFieldDireccion,60);
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
        validadorDeReglas.agregarLimiteATextField(NoPisos,2);
        validadorDeReglas.limitarCampoNumerico(NoPisos);
        validadorDeReglas.limitarCampoNumerico(NoAutos);
        validadorDeReglas.agregarLimiteATextField(Alquiler,8);
        validadorDeReglas.limitarCampoNumerico(Alquiler);
        Alquiler.setVisible(false);
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
        icono.setGlyphSize(30);
        ButtonModificar.setGraphic(icono);
        FontAwesomeIconView iconoCrearCuenta = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        iconoCrearCuenta.setGlyphSize(30);
        ButtonCancelar.setGraphic(iconoCrearCuenta);
        ButtonModificar.setOnMouseEntered(event -> ButtonModificar.setCursor(Cursor.HAND));
        ButtonModificar.setOnMouseExited(event -> ButtonModificar.setCursor(Cursor.DEFAULT));
        ButtonCancelar.setOnMouseEntered(event -> ButtonCancelar.setCursor(Cursor.HAND));
        ButtonCancelar.setOnMouseExited(event -> ButtonCancelar.setCursor(Cursor.DEFAULT));
        EstadoDAO estadoDAO = new EstadoDAO();
        MunicipioDAO municipioDAO = new MunicipioDAO();
        comboBoxEstado.setItems(estadoDAO.consultarEstados());
        comboBoxEstado.setOnAction(e->{
            String nombre = (String) comboBoxEstado.getValue();
            comboBoxMunicipio.setItems(municipioDAO.obtenerMunicipioPorEstado(estadoDAO.obtenerIdEstado(nombre)));
        });
    }
}
