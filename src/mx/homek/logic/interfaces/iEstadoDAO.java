package mx.homek.logic.interfaces;

import javafx.collections.ObservableList;

public interface iEstadoDAO {
    public abstract ObservableList<String> consultarEstados();
    public abstract int obtenerIdEstado(String nombre);
}
