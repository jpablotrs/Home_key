package mx.homek.gui.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.homek.gui.aplicaciones.ConsultarPropiedadAplicacion;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.CompraPropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ComprarPropiedadControlador implements Initializable {
    @FXML
    private Label LabelDireccion;
    @FXML
    private Label LabelCiudad;
    @FXML
    private Label LabelEstado;
    @FXML
    private Label LabelCP;
    @FXML
    private Label LabelHabitaciones;
    @FXML
    private Label LabelBaños;
    @FXML
    private Label LabelPisos;
    @FXML
    private Label LabelCocinas;
    @FXML
    private Label LabelMetrosCuadrados;
    @FXML
    private Label LabelNumeroDePersonas;
    @FXML
    public Label LabelPrecioCompra;
    @FXML
    private Button ButtonCancelar;
    @FXML
    public Button ButtonComprar;
    private String nombreUsuario;
    private String tipoUsuario;
    private Propiedad propiedad;
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

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.SHOPPING_CART);
        icono.setGlyphSize(30);
        ButtonComprar.setGraphic(icono);
        FontAwesomeIconView iconoCrearCuenta = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        iconoCrearCuenta.setGlyphSize(30);
        ButtonCancelar.setGraphic(iconoCrearCuenta);
        ButtonComprar.setOnMouseEntered(event -> ButtonComprar.setCursor(Cursor.HAND));
        ButtonComprar.setOnMouseExited(event -> ButtonComprar.setCursor(Cursor.DEFAULT));
        ButtonCancelar.setOnMouseEntered(event -> ButtonCancelar.setCursor(Cursor.HAND));
        ButtonCancelar.setOnMouseExited(event -> ButtonCancelar.setCursor(Cursor.DEFAULT));
    }

    public void cargarDatosPropiedad() {
        LabelDireccion.setText(propiedad.getDireccion());
        LabelCiudad.setText(propiedad.getCiudad());
        LabelEstado.setText(propiedad.getEstado());
        LabelCP.setText(propiedad.getCodigoPostal());
        LabelHabitaciones.setText(Integer.toString(propiedad.getNumeroHabitaciones()));
        LabelBaños.setText(Integer.toString(propiedad.getNumeroBanos()));
        LabelPisos.setText(Integer.toString(propiedad.getNumeroPisos()));
        LabelCocinas.setText(Integer.toString(propiedad.getNumeroCocina()));
        LabelMetrosCuadrados.setText(Integer.toString(propiedad.getMetrosCuadrados()));
        LabelNumeroDePersonas.setText(Integer.toString(propiedad.getNumeroPersonas()));
        LabelPrecioCompra.setText(Integer.toString(propiedad.getCompra()));
    }

    public void onAgregarClick() {
        Cliente comprador = new Cliente();

        try {
            UsuarioDAO gestorUsuario = new UsuarioDAO();
            int idUsuario = gestorUsuario.obtenerIDUsuarioPorNombre(nombreUsuario);
            ClienteDAO gestorCliente = new ClienteDAO();
            comprador = gestorCliente.consultarClientePorIdUsuario(idUsuario);

        }
        catch (SQLException sqlException) {

        }

        LocalDate fechaCompra = LocalDate.now();

        CompraPropiedad compraPropiedad = new CompraPropiedad();
        compraPropiedad.setCliente(comprador);
        compraPropiedad.setPropiedad(propiedad);
        compraPropiedad.setFecha(java.sql.Date.valueOf(fechaCompra));

        try {
            CompraPropiedadDAO gestorCompra = new CompraPropiedadDAO();
            gestorCompra.insertarCompraPropiedad(compraPropiedad);
            CreadorAlertas creadorAlertas = new CreadorAlertas();
            gestorCompra.cambiarDueño(comprador, propiedad);
            creadorAlertas.crearAlertaDeInformacion("Ha comprado la propiedad exitosamente","Compra exitosa","La propiedad es suya");
            Scene escena = LabelCocinas.getScene();
            Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
            stageAgregarProfesorExterno.close();
            MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);
        }
        catch (SQLException sqlException) {

        }
    }

    public void onCancelarClick() {
        Scene escena = LabelCocinas.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try {
            ConsultarPropiedadAplicacion consultarPropiedadAplicacion = new ConsultarPropiedadAplicacion(nombreUsuario, tipoUsuario);
        }
        catch (IOException ioException){

        }
    }
}
