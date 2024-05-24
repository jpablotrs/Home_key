package mx.homek.gui.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.sql.Blob;
import java.sql.SQLException;

public class AgregarPropiedadControlador {
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
            propiedad.setAlquiler(Integer.parseInt(Alquiler.getText()));
            propiedad.setCompra(Integer.parseInt(Compra.getText()));
            propiedad.setElectricidad(Integer.parseInt(Electricidad.getText()));
            propiedad.setAmueblado(Integer.parseInt(Amueblado.getText()));


            Cliente cliente = new Cliente();
            cliente.setIdCliente(3);
            propiedad.setCliente(cliente);
            propiedad.setClaveCatastral(TextFieldClaveCatastral.getText());

            int idGenerado = propiedadDAO.agregarPropiedad(propiedad);
            if (idGenerado != -1) {
                System.out.println("Propiedad agregada con éxito con id: " + idGenerado);
            } else {
                System.out.println("Error al agregar la propiedad.");
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
}
