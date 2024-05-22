package mx.homek.logic.interfaces;

import mx.homek.logic.implementaciones.PerfilCliente;

import java.sql.SQLException;

public interface IPerfilClienteDAO{
    public abstract int insertarPerfilPreferenciasDelCliente(PerfilCliente preferenciasCliente) throws SQLException;
}
