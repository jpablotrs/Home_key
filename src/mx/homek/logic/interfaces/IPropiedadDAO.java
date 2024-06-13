package mx.homek.logic.interfaces;

import javafx.collections.ObservableList;
import mx.homek.logic.objetoDeTransferencia.Propiedad;

import java.sql.SQLException;
import java.util.List;

public interface IPropiedadDAO {
    public abstract int consultarIDPropiedadPorClaveCatastral(String claveCatastral) throws SQLException;
    public abstract Propiedad obtenerPropiedadPorClaveCatastral(String claveCatastral) throws SQLException;

    public abstract int agregarPropiedad(Propiedad propiedad,int id) throws SQLException;

    ObservableList<Propiedad> consultarPropiedadesObs(String nombreUsuario) throws SQLException;

    public abstract Propiedad consultarPropiedad(String claveCatastral) throws SQLException;
    public abstract Propiedad consultarPropiedad(int idPropiedad) throws SQLException;
    public abstract int eliminarPropiedad(Propiedad propiedad) throws SQLException;
    public abstract int modificarPropiedad(Propiedad propiedad) throws SQLException;
    public abstract int guardarHistorialDeBusqueta(Propiedad propiedad) throws SQLException;

    public abstract List<Propiedad> buscarPorCiudad(String ciudad) throws SQLException;
    public abstract List<Propiedad> buscarPorEstado(String estado) throws SQLException;
    public abstract List<Propiedad> buscarPorNumeroHabitaciones(int numeroHabitaciones) throws SQLException;
    public abstract List<Propiedad> buscarPorNumeroBanos(int numeroBanos) throws SQLException;
    public abstract List<Propiedad> buscarPorNumeroPisos(int numeroPisos) throws SQLException;
    public abstract List<Propiedad> buscarPorPrecioAlquiler(int precioAlquiler) throws SQLException;
    public abstract List<Propiedad> buscarPorPrecioCompra(int precioCompra) throws SQLException;
    public abstract ObservableList<String> consultarPropiedades() throws SQLException;


}