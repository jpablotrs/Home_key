package mx.logic.interfaces;

import mx.logic.objetoDeTransferencia.PropiedadAlquilada;

import java.sql.SQLException;

public interface IPropiedadAlquiladaDAO {
    public abstract int alquilarPropiedad(PropiedadAlquilada alquilarPropiedad) throws SQLException;
    public abstract void firmarContrato() throws SQLException;
}
