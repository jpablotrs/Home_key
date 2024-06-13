package mx.homek.gui.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.homek.gui.aplicaciones.AlquilarPropiedaAplicacion;
import mx.homek.gui.aplicaciones.ComprarPropiedadAplicacion;
import mx.homek.gui.aplicaciones.MenuPrincipalApplication;
import mx.homek.logic.Validadores.CreadorAlertas;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.CompraPropiedadDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

public class ConsultarPropiedadControlador implements Initializable {
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
    @FXML
    private Label labelConsultarPropiedad;
    @FXML
    private Label labelTItuloConsultar;
    @FXML
    private Label labelBuscar;
    @FXML
    private TextField TextFieldCiudad;
    @FXML
    private Button ButtonBuscar;
    @FXML
    private Label labelFiltro;
    @FXML
    private ComboBox comboBoxEstado;
    @FXML
    private ComboBox comboBoxNumeroHabitaciones;
    @FXML
    private ComboBox comboBoxNobaños;
    @FXML
    private ComboBox comboBoxNoPisos;
    @FXML
    private ComboBox comboBoxAlquiler;
    @FXML
    private ComboBox comboBoxCompra;
    @FXML
    private Label labelAmueblado;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private CheckBox checkBoxAmueblado;
    @FXML
    private CheckBox checkBoxSinAmueblar;
    @FXML
    private TableView<Propiedad> tablePropiedades;
    @FXML
    private TableColumn<Propiedad, String> column_ClaveCatastral;
    @FXML
    private TableColumn<Propiedad, String> column_Direccion;
    @FXML
    private TableColumn<Propiedad, Void> column_Modificar;
    @FXML
    Button botonComprar;
    @FXML
    Button botonAlquilar;

    Propiedad propiedadSeleccionada;
    private PropiedadDAO propiedadDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontAwesomeIconView icono = new FontAwesomeIconView(FontAwesomeIcon.SHOPPING_CART);
        icono.setGlyphSize(30);
        botonComprar.setGraphic(icono);
        FontAwesomeIconView iconoCrearCuenta = new FontAwesomeIconView(FontAwesomeIcon.SHOPPING_BASKET);
        iconoCrearCuenta.setGlyphSize(30);
        botonAlquilar.setGraphic(iconoCrearCuenta);
        botonComprar.setOnMouseEntered(event -> botonComprar.setCursor(Cursor.HAND));
        botonComprar.setOnMouseExited(event -> botonComprar.setCursor(Cursor.DEFAULT));
        botonAlquilar.setOnMouseEntered(event -> botonAlquilar.setCursor(Cursor.HAND));
        botonAlquilar.setOnMouseExited(event -> botonAlquilar.setCursor(Cursor.DEFAULT));
        ButtonBuscar.setOnMouseEntered(event -> ButtonBuscar.setCursor(Cursor.HAND));
        ButtonBuscar.setOnMouseExited(event -> ButtonBuscar.setCursor(Cursor.DEFAULT));
        FontAwesomeIconView iconoBuscar = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
        iconoBuscar.setGlyphSize(20);
        ButtonBuscar.setGraphic(iconoBuscar);
        FontAwesomeIconView iconoCerrar = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        iconoCerrar.setGlyphSize(30);
        ButtonCancelar.setGraphic(iconoCerrar);
        ButtonCancelar.setOnMouseEntered(event -> ButtonCancelar.setCursor(Cursor.HAND));
        ButtonCancelar.setOnMouseExited(event -> ButtonCancelar.setCursor(Cursor.DEFAULT));
        try {
            propiedadDAO = new PropiedadDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        column_ClaveCatastral.setCellValueFactory(new PropertyValueFactory<>("estado"));
        column_Direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        botonAlquilar.setDisable(true);
        botonComprar.setDisable(true);

    }

    @FXML
    private void onBuscarClick(ActionEvent event) throws SQLException {
        String ciudad = TextFieldCiudad.getText();
        List<Propiedad> propiedades = buscarPropiedades(ciudad);
        List <Propiedad> propiedadesNombre= new ArrayList<>();
        CompraPropiedadDAO compraPropiedadDAO = new CompraPropiedadDAO();
        for(Propiedad propiedad : propiedades){
            if(propiedad.getCiudad().indexOf(ciudad)>=0 && !compraPropiedadDAO.existeVentaAPropiedad(propiedad)){
                propiedadesNombre.add(propiedad);
            }
        }
        mostrarPropiedadesEnTabla(propiedadesNombre);
    }
    public void cargarPropiedades(){
        tablePropiedades.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            propiedadSeleccionada = newValue;
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                ClienteDAO clienteDAO = new ClienteDAO();
                int idCliente = clienteDAO.consultarIDClientePorCorreo(propiedadSeleccionada.getIdCliente().getCorreo());
                Cliente cliente = clienteDAO.consultarClientePorIdCliente(idCliente);
                if (cliente.getUsuario().getNombreUsuario().equals(nombreUsuario)) {
                    botonAlquilar.setDisable(true);
                    botonComprar.setDisable(true);
                } else if (propiedadSeleccionada.getAlquiler() == 0) {
                    botonAlquilar.setDisable(true);
                    botonComprar.setDisable(false);
                } else {
                    botonAlquilar.setDisable(false);
                    botonComprar.setDisable(false);
                }

            }
            catch (SQLException sqlException){

            }
            catch (Exception exception){

            }
        });
    }

    public void actualizarTabla()  {
        try {
            String ciudad = TextFieldCiudad.getText();
            CompraPropiedadDAO compraPropiedadDAO = new CompraPropiedadDAO();
            List<Propiedad> propiedades = new ArrayList<>();
                    for(Propiedad propiedad:buscarPropiedades(ciudad)){
                        if(!compraPropiedadDAO.existeVentaAPropiedad(propiedad)){
                            propiedades.add(propiedad);
                        }
                    }
            mostrarPropiedadesEnTabla(propiedades);
            tablePropiedades.refresh();
        }
        catch (SQLException sqlException){
            CreadorAlertas creadorAlertas = new CreadorAlertas();
            creadorAlertas.crearAlertaDeError("Error en la consulta","Error","Error");
        }
    }

    private List<Propiedad> buscarPropiedades(String ciudad) throws SQLException {
        try {
            return propiedadDAO.buscarPorNumeroHabitaciones(2);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarPropiedadControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    private void mostrarPropiedadesEnTabla(List<Propiedad> propiedades) {
        ObservableList<Propiedad> listaPropiedades = FXCollections.observableArrayList();
        listaPropiedades.clear(); // Vacía la lista antes de agregar nuevos elementos
        listaPropiedades.addAll(propiedades); // Agrega todas las propiedades a la lista
        tablePropiedades.setItems(listaPropiedades);
        tablePropiedades.refresh();
    }



    public void asignarBotonesDeModificarPropiedad() {
        Callback<TableColumn<Propiedad, Void>, TableCell<Propiedad, Void>> frabricaDeCelda = (final TableColumn<Propiedad, Void> param) -> {
            final TableCell<Propiedad, Void> cell = new TableCell<Propiedad, Void>() {
                private final Button btn_Modificar = new Button();

                {
                    btn_Modificar.setText("Modificar");

                    btn_Modificar.setOnAction((ActionEvent event) -> {
                        try {
                            Propiedad propiedadSeleccionada = getTableView().getItems().get(getIndex());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/homek/gui/fxml/ModificarPropiedad.fxml"));
                            Scene scene = new Scene(loader.load());
                            ModificarPropiedadControlador controller = loader.getController();
                            controller.setClaveCatastral(propiedadSeleccionada.getClaveCatastral());

                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Propiedad propiedadSeleccionada = getTableView().getItems().get(getIndex());
                        try{
                        if (propiedadSeleccionada.getIdCliente().getUsuario().getNombreUsuario().equals(nombreUsuario))
                            setGraphic(btn_Modificar);
                    }
                        catch (NullPointerException nullPointerException){

                        }
                    }
                }
            };
            return cell;
        };
        column_Modificar.setCellFactory(frabricaDeCelda);
    }
    public void onRegresarClick(){
        Scene escena = TextFieldCiudad.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        MenuPrincipalApplication menuPrincipalApplication = new MenuPrincipalApplication(nombreUsuario,tipoUsuario);
    }
    public void onComprarClick() {
        Scene escena = TextFieldCiudad.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try{
            ComprarPropiedadAplicacion comprarPropiedadAplicacion = new ComprarPropiedadAplicacion(nombreUsuario,tipoUsuario,propiedadSeleccionada);
    }
        catch (IOException ioException){

        }

    }
    public void onAlquilarClick(){
        Scene escena = TextFieldCiudad.getScene();
        Stage stageAgregarProfesorExterno = (Stage) escena.getWindow();
        stageAgregarProfesorExterno.close();
        try{
            AlquilarPropiedaAplicacion alquilarPropiedaAplicacion = new AlquilarPropiedaAplicacion(nombreUsuario, tipoUsuario,propiedadSeleccionada);
        }
        catch (IOException ioException){

        }
    }
    public void onBuscarClick(){

    }
}
