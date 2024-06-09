/*package mx.testeos;

import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertTrue;

public class pruebasPropiedadDAO {
    @Test
    public void testAgregarPropiedad() throws SQLException {
        System.out.println("Agregando Propiedad");
        PropiedadDAO propiedadDAO = new PropiedadDAO();

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("123 Calle Falsa");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91000");
        propiedad.setNumeroHabitaciones(3);
        propiedad.setNumeroBanos(2);
        propiedad.setNumeroPisos(1);
        propiedad.setNumeroCocina(1);
        propiedad.setMetrosCuadrados(150);
        propiedad.setNumeroPersonas(4);
        propiedad.setAlquiler(1);
        propiedad.setCompra(0);
        propiedad.setElectricidad(1);
        propiedad.setAmueblado(1);
        propiedad.setFoto(null);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(3);
        propiedad.setCliente(cliente);
        propiedad.setClaveCatastral("Clave 4");
        int idGenerado = propiedadDAO.agregarPropiedad(propiedad,3);

        assertTrue("Se generó un ID válido para la propiedad", idGenerado > 0);
    }

    @Test
    public void testConsultarIDPropiedadPorClaveCatastral() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String claveCatastralExistente = "Clave 1";
        int idExistente = propiedadDAO.consultarIDPropiedadPorClaveCatastral(claveCatastralExistente);
        assertEquals(true, idExistente > 0);
    }

    @Test
    public void testConsultarPropiedadExistente() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String claveCatastralExistente = "Clave 1";
        Propiedad propiedad = propiedadDAO.consultarPropiedad(claveCatastralExistente);
        assertNotNull("La propiedad consultada existe", propiedad);

        ClienteDAO gestorCliente = new ClienteDAO();
        int idCliente = gestorCliente.consultarIDClientePorCorreo(propiedad.getIdCliente().getCorreo());

        Propiedad propiedadAuxiliar = new Propiedad();
        propiedadAuxiliar.setIdPropiedad(1);
        propiedadAuxiliar.setDireccion("Calle principal");
        propiedadAuxiliar.setCiudad("Xalapa");
        propiedadAuxiliar.setEstado("Veracruz");
        propiedadAuxiliar.setCodigoPostal("91020");
        propiedadAuxiliar.setNumeroHabitaciones(1);
        propiedadAuxiliar.setNumeroBanos(1);
        propiedadAuxiliar.setNumeroPisos(1);
        propiedadAuxiliar.setNumeroCocina(1);
        propiedadAuxiliar.setMetrosCuadrados(1);
        propiedadAuxiliar.setNumeroPersonas(1);
        propiedadAuxiliar.setAlquiler(1);
        propiedadAuxiliar.setCompra(1);
        propiedadAuxiliar.setElectricidad(1);
        propiedadAuxiliar.setAmueblado(1);
        propiedadAuxiliar.setFoto(null);
        propiedadAuxiliar.setCliente(gestorCliente.consultarClientePorIdUsuario(idCliente));
        propiedadAuxiliar.setClaveCatastral("Clave 1");

        assertEquals("ID de propiedad", propiedadAuxiliar.getIdPropiedad(), propiedad.getIdPropiedad());
        assertEquals("Dirección", propiedadAuxiliar.getDireccion(), propiedad.getDireccion());
        assertEquals("Ciudad", propiedadAuxiliar.getCiudad(), propiedad.getCiudad());
        assertEquals("Estado", propiedadAuxiliar.getEstado(), propiedad.getEstado());
        assertEquals("Código postal", propiedadAuxiliar.getCodigoPostal(), propiedad.getCodigoPostal());
        assertEquals("Número de habitaciones", propiedadAuxiliar.getNumeroHabitaciones(), propiedad.getNumeroHabitaciones());
        assertEquals("Número de baños", propiedadAuxiliar.getNumeroBanos(), propiedad.getNumeroBanos());
        assertEquals("Número de pisos", propiedadAuxiliar.getNumeroPisos(), propiedad.getNumeroPisos());
        assertEquals("Número de cocina", propiedadAuxiliar.getNumeroCocina(), propiedad.getNumeroCocina());
        assertEquals("Metros cuadrados", propiedadAuxiliar.getMetrosCuadrados(), propiedad.getMetrosCuadrados());
        assertEquals("Número de personas", propiedadAuxiliar.getNumeroPersonas(), propiedad.getNumeroPersonas());
        assertEquals("Alquiler", propiedadAuxiliar.getAlquiler(), propiedad.getAlquiler());
        assertEquals("Compra", propiedadAuxiliar.getCompra(), propiedad.getCompra());
        assertEquals("Electricidad", propiedadAuxiliar.getElectricidad(), propiedad.getElectricidad());
        assertEquals("Amueblado", propiedadAuxiliar.getAmueblado(), propiedad.getAmueblado());
        assertEquals("Cliente", propiedadAuxiliar.getIdCliente().getIdCliente(), propiedad.getIdCliente().getIdCliente());
        assertEquals("Clave catastral", propiedadAuxiliar.getClaveCatastral(), propiedad.getClaveCatastral());
    }

    @Test
    public void testConsultarPropiedad() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String claveCatastralExistente = "ABC123";
        Propiedad propiedadObtenida = propiedadDAO.consultarPropiedad(claveCatastralExistente);
        assertNotNull("La propiedad consultada existe", propiedadObtenida);
        Propiedad propiedadEsperada = new Propiedad();
        propiedadEsperada.setIdPropiedad(2);
        propiedadEsperada.setDireccion("123 Calle Falsa");
        propiedadEsperada.setCiudad("Xalapa");
        propiedadEsperada.setEstado("Veracruz");
        propiedadEsperada.setCodigoPostal("91000");
        propiedadEsperada.setNumeroHabitaciones(3);
        propiedadEsperada.setNumeroBanos(2);
        propiedadEsperada.setNumeroPisos(1);
        propiedadEsperada.setNumeroCocina(1);
        propiedadEsperada.setMetrosCuadrados(150);
        propiedadEsperada.setNumeroPersonas(4);
        propiedadEsperada.setAlquiler(1);
        propiedadEsperada.setCompra(0);
        propiedadEsperada.setElectricidad(1);
        propiedadEsperada.setAmueblado(1);
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setIdCliente(3);
        propiedadEsperada.setCliente(clienteEsperado);
        propiedadEsperada.setClaveCatastral("ABC123");
        assertEquals("ID de propiedad", propiedadEsperada.getIdPropiedad(), propiedadObtenida.getIdPropiedad());
        assertEquals("Dirección", propiedadEsperada.getDireccion(), propiedadObtenida.getDireccion());
        assertEquals("Ciudad", propiedadEsperada.getCiudad(), propiedadObtenida.getCiudad());
        assertEquals("Estado", propiedadEsperada.getEstado(), propiedadObtenida.getEstado());
        assertEquals("Código postal", propiedadEsperada.getCodigoPostal(), propiedadObtenida.getCodigoPostal());
        assertEquals("Número de habitaciones", propiedadEsperada.getNumeroHabitaciones(), propiedadObtenida.getNumeroHabitaciones());
        assertEquals("Número de baños", propiedadEsperada.getNumeroBanos(), propiedadObtenida.getNumeroBanos());
        assertEquals("Número de pisos", propiedadEsperada.getNumeroPisos(), propiedadObtenida.getNumeroPisos());
        assertEquals("Número de cocina", propiedadEsperada.getNumeroCocina(), propiedadObtenida.getNumeroCocina());
        assertEquals("Metros cuadrados", propiedadEsperada.getMetrosCuadrados(), propiedadObtenida.getMetrosCuadrados());
        assertEquals("Número de personas", propiedadEsperada.getNumeroPersonas(), propiedadObtenida.getNumeroPersonas());
        assertEquals("Alquiler", propiedadEsperada.getAlquiler(), propiedadObtenida.getAlquiler());
        assertEquals("Compra", propiedadEsperada.getCompra(), propiedadObtenida.getCompra());
        assertEquals("Electricidad", propiedadEsperada.getElectricidad(), propiedadObtenida.getElectricidad());
        assertEquals("Amueblado", propiedadEsperada.getAmueblado(), propiedadObtenida.getAmueblado());
        assertEquals("Cliente", propiedadEsperada.getIdCliente().getIdCliente(), propiedadObtenida.getIdCliente().getIdCliente());
        assertEquals("Clave catastral", propiedadEsperada.getClaveCatastral(), propiedadObtenida.getClaveCatastral());
    }
    @Test
    public void testEliminarPropiedadExistente() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int idPropiedadExistente = 1;
        Propiedad propiedadAEliminar = new Propiedad();
        propiedadAEliminar.setIdPropiedad(idPropiedadExistente);
        int resultado = propiedadDAO.eliminarPropiedad(propiedadAEliminar);
        assertEquals("Se eliminó la propiedad correctamente", 1, resultado);
    }
    @Test
    public void testModificarPropiedadExistente() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        Propiedad propiedadExistente = new Propiedad();
        propiedadExistente.setIdPropiedad(3);
        propiedadExistente.setDireccion("123 Calle Falsa Actualizado");
        propiedadExistente.setCiudad("Xalapa Actualizado");
        propiedadExistente.setEstado("Veracruz Actualizado");
        propiedadExistente.setCodigoPostal("91000 ");
        propiedadExistente.setNumeroHabitaciones(4);
        propiedadExistente.setNumeroBanos(3);
        propiedadExistente.setNumeroPisos(2);
        propiedadExistente.setNumeroCocina(2);
        propiedadExistente.setMetrosCuadrados(200);
        propiedadExistente.setNumeroPersonas(6);
        propiedadExistente.setAlquiler(0);
        propiedadExistente.setCompra(1);
        propiedadExistente.setElectricidad(0);
        propiedadExistente.setAmueblado(0);
        propiedadExistente.setFoto(null);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(3);
        propiedadExistente.setCliente(cliente);
        propiedadExistente.setClaveCatastral("Clave 1");
        int resultado = propiedadDAO.modificarPropiedad(propiedadExistente);
        assertEquals("Se modificó la propiedad correctamente", 1, resultado);
    }

    @Test
    public void testBuscarPorCiudadExistente() throws SQLException {
        System.out.println("Buscando propiedades por ciudad existente");

        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String ciudad = "Xalapa";
        List<Propiedad> propiedades = propiedadDAO.buscarPorCiudad(ciudad);
        assertNotNull("La lista de propiedades no debería ser nula", propiedades);
        assertEquals("El número de propiedades en la ciudad debería ser 1", 1, propiedades.size());
    }
    @Test
    public void testBuscarPorCiudadEstado() throws SQLException {
        System.out.println("Buscando propiedades por estado existente");

        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String estado = "Veracruz";
        List<Propiedad> propiedades = propiedadDAO.buscarPorEstado(estado);
        assertNotNull("La lista de propiedades no debería ser nula", propiedades);
        assertEquals("El número de propiedades en la ciudad debería ser 1", 1, propiedades.size());
    }

    @Test
    public void testBuscarPorNumeroHabitaciones() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int numeroHabitaciones = 3;
        List<Propiedad> propiedades = propiedadDAO.buscarPorNumeroHabitaciones(numeroHabitaciones);
        assertNotNull("La lista de propiedades no debería ser nula", propiedades);
        assertTrue("Debería haber al menos una propiedad con el número de habitaciones especificado", !propiedades.isEmpty());
    }

    @Test
    public void testBuscarPorNumeroBanos() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int numeroBanos = 2;
        List<Propiedad> propiedades = propiedadDAO.buscarPorNumeroBanos(numeroBanos);
        assertNotNull(propiedades);
        assertTrue(!propiedades.isEmpty());
    }

    @Test
    public void testBuscarPorNumeroPisos() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int numeroPisos = 1;
        List<Propiedad> propiedades = propiedadDAO.buscarPorNumeroPisos(numeroPisos);
        assertNotNull(propiedades);
        assertFalse(propiedades.isEmpty());
    }

    @Test
    public void testBuscarPorPrecioAlquiler() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int precioAlquiler = 1;
        List<Propiedad> propiedades = propiedadDAO.buscarPorPrecioAlquiler(precioAlquiler);
        assertNotNull(propiedades);
        assertTrue(propiedades.size() > 0);
    }

    @Test
    public void testBuscarPorPrecioCompra() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        int precioCompra = 0;
        List<Propiedad> propiedades = propiedadDAO.buscarPorPrecioCompra(precioCompra);
        assertNotNull(propiedades);
        assertTrue(propiedades.size() > 0);
    }

    @Test
    public void obtenerPropiedadPorClaveCatastral() throws SQLException {
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        String claveCatastral = "Clave 1";
        Propiedad propiedad = propiedadDAO.obtenerPropiedadPorClaveCatastral(claveCatastral);
        assertNotNull(propiedad);
        assertNotNull(propiedad.getIdPropiedad());
        assertNotNull(propiedad.getDireccion());
        assertNotNull(propiedad.getCiudad());
        assertNotNull(propiedad.getEstado());
        assertNotNull(propiedad.getCodigoPostal());
        assertNotNull(propiedad.getNumeroHabitaciones());
        assertNotNull(propiedad.getNumeroBanos());
        assertNotNull(propiedad.getNumeroPisos());
        assertNotNull(propiedad.getNumeroCocina());
        assertNotNull(propiedad.getMetrosCuadrados());
        assertNotNull(propiedad.getNumeroPersonas());
        assertNotNull(propiedad.getAlquiler());
        assertNotNull(propiedad.getCompra());
        assertNotNull(propiedad.getElectricidad());
        assertNotNull(propiedad.getAmueblado());
        assertNotNull(propiedad.getFoto());
    }
}
*/