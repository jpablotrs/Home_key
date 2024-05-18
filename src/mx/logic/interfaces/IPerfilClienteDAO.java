package mx.logic.interfaces;

import mx.logic.implementaciones.PerfilCliente;

import java.sql.SQLException;

public interface IPerfilClienteDAO{
    public abstract int insertarPerfilPreferenciasDelCliente(PerfilCliente preferenciasCliente) throws SQLException;
}
