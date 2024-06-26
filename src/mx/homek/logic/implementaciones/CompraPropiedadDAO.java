package mx.homek.logic.implementaciones;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.ICompraPropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompraPropiedadDAO implements ICompraPropiedadDAO {
    ConexionBaseDeDatos administradorBaseDeDatos;
    Connection conexion;

    public CompraPropiedadDAO() throws SQLException{
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int insertarCompraPropiedad(CompraPropiedad compraPropiedad) throws SQLException {
        String consultaSql = "insert into compraPropiedad (fecha, Cliente_idCliente, Propiedad_idPropiedad) values (?, ?, ?)";

        java.sql.Date fechaCompra = new java.sql.Date(compraPropiedad.getFecha().getTime());
        ClienteDAO gestorCliente = new ClienteDAO();
        int idCliente = gestorCliente.consultarIDClientePorCorreo(compraPropiedad.getCliente().getCorreo());
        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        int idPropiedad = gestorPropiedad.consultarIDPropiedadPorClaveCatastral(compraPropiedad.getPropiedad().getClaveCatastral());

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setDate(1, fechaCompra);
        sentencia.setInt(2, idCliente);
        sentencia.setInt(3, idPropiedad);

        return sentencia.executeUpdate();
    }

    @Override
    public int ofertarPropiedad(int idPropiedad) throws SQLException {
        String consultaSql = "update propiedad set estadoOferta = 'Preferente' where idPropiedad = ?";

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setInt(1, idPropiedad);

        return sentencia.executeUpdate();
    }

    @Override
    public int cambiarDueño(Cliente cliente, Propiedad propiedad) throws SQLException {
        String consultaSql = "update propiedad set Cliente_idCliente = ? where idPropiedad = ?";

        ClienteDAO gestorCliente = new ClienteDAO();
        int idCliente = gestorCliente.consultarIDClientePorCorreo(cliente.getCorreo());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        int idPropiedad = gestorPropiedad.consultarIDPropiedadPorClaveCatastral(propiedad.getClaveCatastral());

        PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
        sentencia.setInt(1, idCliente);
        sentencia.setInt(2, idPropiedad);

        return sentencia.executeUpdate();
    }

    @Override
    public boolean existeVentaAPropiedad(Propiedad propiedad) throws SQLException {
        String consulta = "Select * from compraPropiedad where Propiedad_idPropiedad = ?";
        PreparedStatement consultasql =  conexion.prepareStatement(consulta);
        consultasql.setInt(1,propiedad.getIdPropiedad());
        ResultSet resultado = consultasql.executeQuery();
        if(resultado.next()){
            return true;
        }
        return false;
    }
}
