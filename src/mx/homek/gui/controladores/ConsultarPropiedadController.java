package mx.homek.gui.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

public class ConsultarPropiedadController {


    @FXML
    private Button buttonRegresar;

    @FXML
    private TableView<Propiedad> tableViewPropiedades;

    @FXML
    private TableColumn<Propiedad, String> colClaveCatastral;

    @FXML
    private TableColumn<Propiedad, String> colDireccion;

    @FXML
    private TableColumn<Propiedad, Void> colDetalles;

    private PropiedadDAO propiedadDAO;

}
