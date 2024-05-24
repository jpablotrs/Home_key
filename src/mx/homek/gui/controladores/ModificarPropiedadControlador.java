package mx.homek.gui.controladores;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModificarPropiedadControlador implements Initializable {
    @FXML
    public TextField TextFieldClaveCatastral;

    @FXML
    public Label LabelClaveCatastral;

    @FXML
    private TextField TextFieldDireccion;

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
    private void onGuardarClick(ActionEvent event) {
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
            propiedad.setAlquiler(Integer.parseInt(Alquiler.getText()));
            propiedad.setCompra(Integer.parseInt(Compra.getText()));
            propiedad.setElectricidad(Integer.parseInt(Electricidad.getText()));
            propiedad.setAmueblado(Integer.parseInt(Amueblado.getText()));
            Blob fotoBlob = new javax.sql.rowset.serial.SerialBlob(Foto.getText().getBytes());
            propiedad.setFoto(fotoBlob);

            Cliente cliente = new Cliente();
            cliente.setIdCliente(3);
            propiedad.setCliente(cliente);
            propiedad.setClaveCatastral(TextFieldClaveCatastral.getText());

            int resultado = propiedadDAO.modificarPropiedad(propiedad);
            if (resultado != 1) {
                System.out.println("Propiedad modificada con éxito con id: ");
            } else {
                System.out.println("Error al modificar la propiedad.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void llenarVentana(String claveCatastral) {
        try {
            Propiedad propiedad = new Propiedad();
            PropiedadDAO propiedadDAO = new PropiedadDAO();
            propiedad = propiedadDAO.obtenerPropiedadPorClaveCatastral("Clave 1");

            if (propiedad != null) {
                TextFieldDireccion.setText(propiedad.getDireccion());
                TextFieldCiudad.setText(propiedad.getCiudad());
                TextFieldEstado.setText(propiedad.getEstado());
                TextFieldCP.setText(propiedad.getCodigoPostal());
                NumeroHabitaciones.setText(String.valueOf(propiedad.getNumeroHabitaciones()));
                NoBanos.setText(String.valueOf(propiedad.getNumeroBanos()));
                NoPisos.setText(String.valueOf(propiedad.getNumeroPisos()));
                NoCocina.setText(String.valueOf(propiedad.getNumeroCocina()));
                MetrosCuadrados.setText(String.valueOf(propiedad.getMetrosCuadrados()));
                NoPersonas.setText(String.valueOf(propiedad.getNumeroPersonas()));
                Alquiler.setText(String.valueOf(propiedad.getAlquiler()));
                Compra.setText(String.valueOf(propiedad.getCompra()));
                Electricidad.setText(String.valueOf(propiedad.getElectricidad()));
                Amueblado.setText(String.valueOf(propiedad.getAmueblado()));
                Foto.setText(propiedad.getFoto().toString());
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
        String claveCatastral = "Clave 1";
        llenarVentana(claveCatastral);
    }
}
