package mx.homek.logic.implementaciones;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.interfaces.IPropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class    PropiedadDAO implements IPropiedadDAO {
    private ConexionBaseDeDatos administradorBaseDeDatos;
    private Connection conexion;

    public PropiedadDAO() throws SQLException {
        administradorBaseDeDatos = new ConexionBaseDeDatos();
        conexion = administradorBaseDeDatos.obtenerConexion();
    }

    @Override
    public int consultarIDPropiedadPorClaveCatastral(String claveCatastral) throws SQLException {
        String consultaSQL = "select idPropiedad from propiedad where claveCatastral = ?";
        PreparedStatement consultarIdPropiedad = conexion.prepareStatement(consultaSQL);
        consultarIdPropiedad.setString(1, claveCatastral);
        ResultSet resultadoConsulta = consultarIdPropiedad.executeQuery();
        if(resultadoConsulta.next())
            return resultadoConsulta.getInt("idPropiedad");
        return -1;
    }

    public Propiedad obtenerPropiedadPorClaveCatastral(String claveCatastral) throws SQLException {
        String consultaSQL = "SELECT * FROM propiedad WHERE claveCatastral = ?";
        PreparedStatement consultarPropiedad = conexion.prepareStatement(consultaSQL);
        consultarPropiedad.setString(1, claveCatastral);
        ResultSet resultadoConsulta = consultarPropiedad.executeQuery();

        if (resultadoConsulta.next()) {
            Propiedad propiedad = new Propiedad();
            propiedad.setIdPropiedad(resultadoConsulta.getInt("idPropiedad"));
            propiedad.setDireccion(resultadoConsulta.getString("dirección"));
            propiedad.setCiudad(resultadoConsulta.getString("ciudad"));
            propiedad.setEstado(resultadoConsulta.getString("estado"));
            propiedad.setCodigoPostal(resultadoConsulta.getString("codigoPostal"));
            propiedad.setNumeroHabitaciones(resultadoConsulta.getInt("numHabitaciones"));
            propiedad.setNumeroBanos(resultadoConsulta.getInt("numBaños"));
            propiedad.setNumeroPisos(resultadoConsulta.getInt("numPisos"));
            propiedad.setNumeroCocina(resultadoConsulta.getInt("Cocina"));
            propiedad.setMetrosCuadrados(resultadoConsulta.getInt("metrosCuadrados"));
            propiedad.setNumeroPersonas(resultadoConsulta.getInt("numPersonas"));
            propiedad.setAlquiler(resultadoConsulta.getInt("alquiler"));
            propiedad.setCompra(resultadoConsulta.getInt("compra"));
            propiedad.setElectricidad(resultadoConsulta.getInt("electricidad"));
            propiedad.setAmueblado(resultadoConsulta.getInt("amueblado"));
            propiedad.setFoto(resultadoConsulta.getBlob("foto"));
            propiedad.setClaveCatastral(resultadoConsulta.getString("claveCatastral"));
            return propiedad;
        } else {
            return null;
        }
    }


    @Override
    public int agregarPropiedad(Propiedad propiedad,int id) throws SQLException {
        String consultaExistenciaSQL = "SELECT COUNT(*) AS total FROM propiedad WHERE claveCatastral = ?";
        PreparedStatement verificarExistencia = conexion.prepareStatement(consultaExistenciaSQL);
        verificarExistencia.setString(1, propiedad.getClaveCatastral());
        ResultSet resultadoExistencia = verificarExistencia.executeQuery();
        if (resultadoExistencia.next()) {
            int totalPropiedades = resultadoExistencia.getInt("total");
            if (totalPropiedades > 0) {
                System.out.println("Ya existe una propiedad con la misma clave catastral.");
                return -1;
            }
        }

        String insercionSQL = "INSERT INTO propiedad (direccion, ciudad, estado, codigoPostal, numHabitaciones, numBanos, numPisos, cocina, metrosCuadrados, numPersonas, alquiler, compra, electricidad, amueblado, Cliente_idCliente, claveCatastral) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ClienteDAO gestorCliente = new ClienteDAO();
        PreparedStatement insertarPropiedad = conexion.prepareStatement(insercionSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        insertarPropiedad.setString(1, propiedad.getDireccion());
        insertarPropiedad.setString(2, propiedad.getCiudad());
        insertarPropiedad.setString(3, propiedad.getEstado());
        insertarPropiedad.setString(4, propiedad.getCodigoPostal());
        insertarPropiedad.setInt(5, propiedad.getNumeroHabitaciones());
        insertarPropiedad.setInt(6, propiedad.getNumeroBanos());
        insertarPropiedad.setInt(7, propiedad.getNumeroPisos());
        insertarPropiedad.setInt(8, propiedad.getNumeroCocina());
        insertarPropiedad.setInt(9, propiedad.getMetrosCuadrados());
        insertarPropiedad.setInt(10, propiedad.getNumeroPersonas());
        insertarPropiedad.setInt(11, propiedad.getAlquiler());
        insertarPropiedad.setInt(12, propiedad.getCompra());
        insertarPropiedad.setInt(13, propiedad.getElectricidad());
        insertarPropiedad.setInt(14, propiedad.getAmueblado());
        insertarPropiedad.setInt(15,id);
        insertarPropiedad.setString(16, propiedad.getClaveCatastral());
        int filasInsertadas = insertarPropiedad.executeUpdate();
        if (filasInsertadas > 0) {
            ResultSet generatedKeys = insertarPropiedad.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public ObservableList<String> consultarPropiedades() throws SQLException {
        String consultaSQL = "SELECT claveCatastral FROM propiedad";
        PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL);
        ResultSet resultado = consultarPropiedades.executeQuery();
        ObservableList<String> propiedades = FXCollections.observableArrayList();

        if(resultado.next()) {
            String clave = resultado.getString("claveCatastral");
            propiedades.add(clave);
        }
        return propiedades;
    }

    @Override
    public Propiedad consultarPropiedad(String claveCatastral) throws SQLException {
        String consultaSQL = "SELECT * FROM propiedad WHERE claveCatastral = ?";
        PreparedStatement consultarPropiedad = conexion.prepareStatement(consultaSQL);
        consultarPropiedad.setString(1, claveCatastral);
        ResultSet resultadoConsulta = consultarPropiedad.executeQuery();
        if (resultadoConsulta.next()) {
            Propiedad propiedad = new Propiedad();
            propiedad.setIdPropiedad(resultadoConsulta.getInt("idPropiedad"));
            propiedad.setDireccion(resultadoConsulta.getString("dirección"));
            propiedad.setCiudad(resultadoConsulta.getString("ciudad"));
            propiedad.setEstado(resultadoConsulta.getString("estado"));
            propiedad.setCodigoPostal(resultadoConsulta.getString("codigoPostal"));
            propiedad.setNumeroHabitaciones(resultadoConsulta.getInt("numHabitaciones"));
            propiedad.setNumeroBanos(resultadoConsulta.getInt("numBaños"));
            propiedad.setNumeroPisos(resultadoConsulta.getInt("numPisos"));
            propiedad.setNumeroCocina(resultadoConsulta.getInt("cocina"));
            propiedad.setMetrosCuadrados(resultadoConsulta.getInt("metrosCuadrados"));
            propiedad.setNumeroPersonas(resultadoConsulta.getInt("numPersonas"));
            propiedad.setAlquiler(resultadoConsulta.getInt("alquiler"));
            propiedad.setCompra(resultadoConsulta.getInt("compra"));
            propiedad.setElectricidad(resultadoConsulta.getInt("electricidad"));
            propiedad.setAmueblado(resultadoConsulta.getInt("amueblado"));
            propiedad.setFoto(resultadoConsulta.getBlob("foto"));
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultadoConsulta.getInt("Cliente_idCliente"));
            propiedad.setCliente(cliente);
            propiedad.setClaveCatastral(resultadoConsulta.getString("claveCatastral"));
            return propiedad;
        }
        return null;
    }

    @Override
    public int eliminarPropiedad(Propiedad propiedad) throws SQLException {
        String eliminacionSQL = "DELETE FROM propiedad WHERE idPropiedad = ?";
        PreparedStatement eliminarPropiedad = conexion.prepareStatement(eliminacionSQL);
        eliminarPropiedad.setInt(1, propiedad.getIdPropiedad());
        return eliminarPropiedad.executeUpdate();
    }

    @Override
    public int modificarPropiedad(Propiedad propiedad) throws SQLException {
        int idModificado = -1;
        String actualizacionSQL = "UPDATE propiedad SET dirección = ?, ciudad = ?, estado = ?, codigoPostal = ?, numHabitaciones = ?, numBaños = ?, numPisos = ?, cocina = ?, metrosCuadrados = ?, numPersonas = ?, alquiler = ?, compra = ?, electricidad = ?, amueblado = ?, foto = ?, Cliente_idCliente = ? WHERE claveCatastral = ?";

        try (PreparedStatement actualizarPropiedad = conexion.prepareStatement(actualizacionSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            actualizarPropiedad.setString(1, propiedad.getDireccion());
            actualizarPropiedad.setString(2, propiedad.getCiudad());
            actualizarPropiedad.setString(3, propiedad.getEstado());
            actualizarPropiedad.setString(4, propiedad.getCodigoPostal());
            actualizarPropiedad.setInt(5, propiedad.getNumeroHabitaciones());
            actualizarPropiedad.setInt(6, propiedad.getNumeroBanos());
            actualizarPropiedad.setInt(7, propiedad.getNumeroPisos());
            actualizarPropiedad.setInt(8, propiedad.getNumeroCocina());
            actualizarPropiedad.setInt(9, propiedad.getMetrosCuadrados());
            actualizarPropiedad.setInt(10, propiedad.getNumeroPersonas());
            actualizarPropiedad.setInt(11, propiedad.getAlquiler());
            actualizarPropiedad.setInt(12, propiedad.getCompra());
            actualizarPropiedad.setInt(13, propiedad.getElectricidad());
            actualizarPropiedad.setInt(14, propiedad.getAmueblado());
            actualizarPropiedad.setBlob(15, propiedad.getFoto());
            actualizarPropiedad.setInt(16, 3);
            actualizarPropiedad.setString(17, propiedad.getClaveCatastral());

            int filasActualizadas = actualizarPropiedad.executeUpdate();
            if (filasActualizadas > 0) {
                try (ResultSet generatedKeys = actualizarPropiedad.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idModificado = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idModificado;
    }



    @Override
    public int guardarHistorialDeBusqueta(Propiedad propiedad) throws SQLException {
        String insercionHistorialSQL = "INSERT INTO historial_busqueda (idPropiedad, dirección, ciudad, estado, codigoPostal, numHabitaciones, numBaños, numPisos, cocina, metrosCuadrados, numPersonas, alquiler, compra, electricidad, amueblado, foto, Cliente_idCliente, claveCatastral, fechaBusqueda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        ClienteDAO gestorCliente = new ClienteDAO();
        int idCliente = gestorCliente.consultarIDClientePorCorreo(propiedad.getIdCliente().getCorreo());

        PreparedStatement insertarHistorial = conexion.prepareStatement(insercionHistorialSQL);
        insertarHistorial.setInt(1, propiedad.getIdPropiedad());
        insertarHistorial.setString(2, propiedad.getDireccion());
        insertarHistorial.setString(3, propiedad.getCiudad());
        insertarHistorial.setString(4, propiedad.getEstado());
        insertarHistorial.setString(5, propiedad.getCodigoPostal());
        insertarHistorial.setInt(6, propiedad.getNumeroHabitaciones());
        insertarHistorial.setInt(7, propiedad.getNumeroBanos());
        insertarHistorial.setInt(8, propiedad.getNumeroPisos());
        insertarHistorial.setInt(9, propiedad.getNumeroCocina());
        insertarHistorial.setInt(10, propiedad.getMetrosCuadrados());
        insertarHistorial.setInt(11, propiedad.getNumeroPersonas());
        insertarHistorial.setInt(12, propiedad.getAlquiler());
        insertarHistorial.setInt(13, propiedad.getCompra());
        insertarHistorial.setInt(14, propiedad.getElectricidad());
        insertarHistorial.setInt(15, propiedad.getAmueblado());
        insertarHistorial.setBlob(16, propiedad.getFoto());
        insertarHistorial.setInt(17, idCliente);
        insertarHistorial.setString(18, propiedad.getClaveCatastral());

        return insertarHistorial.executeUpdate();
    }
    @Override
    public List<Propiedad> buscarPorCiudad(String ciudad) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE ciudad = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setString(1, ciudad);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorEstado(String estado) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE estado = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setString(1, estado);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorNumeroHabitaciones(int numeroHabitaciones) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad ";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorNumeroBanos(int numeroBanos) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE numBanos = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setInt(1, numeroBanos);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorNumeroPisos(int numeroPisos) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE numPisos = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setInt(1, numeroPisos);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorPrecioAlquiler(int precioAlquiler) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE alquiler = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setInt(1, precioAlquiler);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    @Override
    public List<Propiedad> buscarPorPrecioCompra(int precioCompra) throws SQLException {
        List<Propiedad> propiedades = new ArrayList<>();
        String consultaSQL = "SELECT * FROM propiedad WHERE compra = ?";
        try (PreparedStatement consultarPropiedades = conexion.prepareStatement(consultaSQL)) {
            consultarPropiedades.setInt(1, precioCompra);
            try (ResultSet resultadoConsulta = consultarPropiedades.executeQuery()) {
                while (resultadoConsulta.next()) {
                    Propiedad propiedad = obtenerPropiedadDesdeResultSet(resultadoConsulta);
                    propiedades.add(propiedad);
                }
            }
        }
        return propiedades;
    }

    private Propiedad obtenerPropiedadDesdeResultSet(ResultSet resultadoConsulta) throws SQLException {
        Propiedad propiedad = new Propiedad();
        propiedad.setIdPropiedad(resultadoConsulta.getInt("idPropiedad"));
        propiedad.setDireccion(resultadoConsulta.getString("direccion"));
        propiedad.setCiudad(resultadoConsulta.getString("ciudad"));
        propiedad.setEstado(resultadoConsulta.getString("estado"));
        propiedad.setCodigoPostal(resultadoConsulta.getString("codigoPostal"));
        propiedad.setNumeroHabitaciones(resultadoConsulta.getInt("numHabitaciones"));
        propiedad.setNumeroBanos(resultadoConsulta.getInt("numBanos"));
        propiedad.setNumeroPisos(resultadoConsulta.getInt("numPisos"));
        propiedad.setNumeroCocina(resultadoConsulta.getInt("cocina"));
        propiedad.setMetrosCuadrados(resultadoConsulta.getInt("metrosCuadrados"));
        propiedad.setNumeroPersonas(resultadoConsulta.getInt("numPersonas"));
        propiedad.setAlquiler(resultadoConsulta.getInt("alquiler"));
        propiedad.setCompra(resultadoConsulta.getInt("compra"));
        propiedad.setElectricidad(resultadoConsulta.getInt("electricidad"));
        propiedad.setAmueblado(resultadoConsulta.getInt("amueblado"));
        propiedad.setFoto(resultadoConsulta.getBlob("foto"));
        Cliente cliente = new Cliente();
        cliente.setIdCliente(resultadoConsulta.getInt("Cliente_idCliente"));
        propiedad.setCliente(cliente);
        propiedad.setClaveCatastral(resultadoConsulta.getString("claveCatastral"));
        return propiedad;
    }


}