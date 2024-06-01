module mx.homek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires mysql.connector.j;
    requires java.sql.rowset;

    opens mx.homek.gui.controladores to javafx.fxml;
    opens mx.homek.gui.aplicaciones to javafx.fxml;
    opens mx.homek.gui.fxml to javafx.fxml;
    opens mx.homek.dataaccess to javafx.fxml;
    exports mx.homek.dataaccess;
    exports mx.homek.logic.implementaciones;
    exports mx.homek.logic.objetoDeTransferencia;
    exports mx.homek.logic.interfaces;
    exports mx.homek.gui;
    exports mx.homek.gui.controladores;
    exports mx.homek.gui.aplicaciones;
    exports mx.testeos;
    opens mx.homek.gui to javafx.fxml;
}