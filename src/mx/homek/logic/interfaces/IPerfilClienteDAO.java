package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.PerfilCliente;
import mx.homek.logic.objetoDeTransferencia.Usuario;

import java.sql.SQLException;

public interface IPerfilClienteDAO{
    public abstract int insertarPerfilPreferenciasDelCliente(PerfilCliente preferenciasCliente) throws SQLException;
    public abstract int convertirPerfilPreferenciasAID(PerfilCliente preferencias) throws SQLException;
}
