package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;

import java.sql.SQLException;

public interface ICompraPropiedadDAO {
    public abstract int insertarCompraPropiedad(CompraPropiedad compraPropiedad) throws SQLException;
    public abstract int ofertarPropiedad(int idPropiedad) throws SQLException;
}
