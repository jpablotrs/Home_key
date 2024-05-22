package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.SQLException;

public interface IPropiedadAlquiladaDAO {
    public abstract int alquilarPropiedad(PropiedadAlquilada alquilarPropiedad) throws SQLException;
    public abstract void firmarContrato() throws SQLException;
}
