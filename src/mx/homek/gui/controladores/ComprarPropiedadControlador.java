package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mx.homek.logic.Validadores.ValidadorDeReglas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.CompraPropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

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
        }
        catch (SQLException sqlException) {

        }
    }

    public void onCancelarClick() {
    }
}
