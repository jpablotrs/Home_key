package mx.homek.gui.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.sql.Blob;
import java.sql.SQLException;

public class AgregarPropiedadControlador {
    @FXML
    public TextField TextFieldClaveCatastral;
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
}
