package mx.logic.interfaces;

import mx.logic.objetoDeTransferencia.CompraPropiedad;

import java.sql.SQLException;

public interface ICompraPropiedadDAO {
    public abstract int insertarCompraPropiedad(CompraPropiedad compraPropiedad) throws SQLException;
    public abstract void ofertarPropiedad() throws SQLException;
}
