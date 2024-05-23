package mx.homek.logic.interfaces;

import mx.homek.logic.implementaciones.PerfilClienteDAO;
import mx.homek.logic.objetoDeTransferencia.PerfilCliente;

import java.sql.SQLException;

public interface IPerfilClienteDAO{
    public abstract int insertarPerfilPreferenciasDelCliente(PerfilClienteDAO preferenciasCliente) throws SQLException;
    public abstract int obtenerIdPreferenciasPorIdCliente(int idCliente) throws SQLException;
    public abstract PerfilCliente consultarPerfilPorId(int idPerfil) throws SQLException;
}
