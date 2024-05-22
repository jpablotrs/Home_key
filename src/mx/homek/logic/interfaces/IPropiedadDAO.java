package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.sql.SQLException;

public interface IPropiedadDAO {
    public abstract int consultarIDPropiedadPorClaveCatastral(String claveCatastral) throws SQLException;
    public abstract int agregarPropiedad(Propiedad propiedad) throws SQLException;
    public abstract Propiedad consultarPropiedad(String claveCatastral) throws SQLException;
    public abstract int eliminarPropiedad(Propiedad propiedad) throws SQLException;
    public abstract int modificarPropiedad(Propiedad propiedad) throws SQLException;
    public abstract int guardarHistorialDeBusqueta(Propiedad propiedad) throws SQLException;
}
