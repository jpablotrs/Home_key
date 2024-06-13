package mx.homek.logic.interfaces;

import javafx.collections.ObservableList;

public interface iMunicipioDAO {
    public abstract ObservableList<String> obtenerMunicipioPorEstado(int  id);
}
