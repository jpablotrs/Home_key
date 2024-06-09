package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.sql.SQLException;

public interface ICompraPropiedadDAO {
    public abstract int insertarCompraPropiedad(CompraPropiedad compraPropiedad) throws SQLException;
    public abstract int ofertarPropiedad(int idPropiedad) throws SQLException;
    public abstract int cambiarDueño(Cliente cliente, Propiedad propiedad) throws SQLException;
}
