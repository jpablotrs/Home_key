package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface IPropiedadAlquiladaDAO {
    public abstract int alquilarPropiedad(PropiedadAlquilada alquilarPropiedad) throws SQLException;
    public abstract ArrayList<PropiedadAlquilada> consultarPropiedadesAlquiladasPorPropiedad(Propiedad propiedad) throws SQLException;
}
