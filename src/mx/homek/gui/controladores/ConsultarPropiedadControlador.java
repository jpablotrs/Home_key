/*package mx.homek.gui.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

public class ConsultarPropiedadControlador implements Initializable {

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

    private PropiedadDAO propiedadDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            propiedadDAO = new PropiedadDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        column_ClaveCatastral.setCellValueFactory(new PropertyValueFactory<>("claveCatastral"));
        column_Direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        asignarBotonesDeModificarPropiedad();
    }

    @FXML
    private void onBuscarClick(ActionEvent event) throws SQLException {
        String ciudad = TextFieldCiudad.getText();
        List<Propiedad> propiedades = buscarPropiedades(ciudad);
        mostrarPropiedadesEnTabla(propiedades);
    }

    private List<Propiedad> buscarPropiedades(String ciudad) throws SQLException{
        try {
            return propiedadDAO.buscarPorCiudad(ciudad);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarPropiedadControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    private void mostrarPropiedadesEnTabla(List<Propiedad> propiedades) {
        ObservableList<Propiedad> listaPropiedades = FXCollections.observableArrayList(propiedades);
        tablePropiedades.setItems(listaPropiedades);
    }

    public void asignarBotonesDeModificarPropiedad(){
        Callback<TableColumn<Propiedad, Void>, TableCell<Propiedad, Void>> frabricaDeCelda = (final TableColumn<Propiedad, Void> param) -> {
            final TableCell<Propiedad, Void> cell = new TableCell<Propiedad, Void>() {
                private final Button btn_Modificar = new Button();{
                    btn_Modificar.setText("Modificar");
                    btn_Modificar.setOnAction((ActionEvent event) -> {
                        Propiedad propiedadSeleccionada = getTableView().getItems().get(getIndex());
                        PropiedadAuxiliar.setInstancia(propiedadSeleccionada);
                        String ruta = "/mx/homek/gui/fxml/ModificarPropiedad.fxml";
                    });
                }
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    }else{
                        setGraphic(btn_Modificar);
                    }
                }
            };
            return cell;
        };
        column_Modificar.setCellFactory(frabricaDeCelda);
    }


}
*/