package mx.homek.logic.interfaces;

import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.sql.SQLException;

public interface IClienteDAO {
    public abstract int consultarIDClientePorCorreo(String correo) throws SQLException;
    public abstract int insertarCliente(Cliente cliente) throws SQLException;
    public abstract int modificarCliente(Cliente cliente) throws SQLException;
    public abstract int eliminarCliente(Cliente cliente) throws SQLException;
}
