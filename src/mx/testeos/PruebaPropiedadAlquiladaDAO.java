package mx.testeos;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadAlquiladaDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

public class PruebaPropiedadAlquiladaDAO {
    public void ejecutarConsultasBase(String consultaSql) {
        try {
            ConexionBaseDeDatos administradorBaseDeDatos;
            Connection conexion;
            administradorBaseDeDatos = new ConexionBaseDeDatos();
            conexion = administradorBaseDeDatos.obtenerConexion();

            PreparedStatement sentencia = conexion.prepareStatement(consultaSql);
            sentencia.executeUpdate();

            conexion.close();
        }
        catch (SQLException sqlException) {

        }
    }

    @Before
    public void limpiarTablas() throws SQLException{
        ejecutarConsultasBase("Delete from alquilarpropiedad");
        ejecutarConsultasBase("Delete from comprapropiedad");
        ejecutarConsultasBase("Delete from preferenciascliente");
        ejecutarConsultasBase("Delete from visita");
        ejecutarConsultasBase("Delete from propiedad");
        ejecutarConsultasBase("Delete from cliente");
        ejecutarConsultasBase("Delete from usuario");
    }

    @Test
    public void pruebaAlquilarPropiedadExitoso() throws SQLException {
        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Pedro123");
        usuarioDueno.setContrasena("budin123");
        usuarioDueno.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Pedro");
        clienteDueno.setApellidoPaterno("Landa");
        clienteDueno.setApellidoMaterno("Landa");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1999, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("pedrolanda@gmail.com");
        clienteDueno.setTelefono("2282009988");
        clienteDueno.setUsuario(usuarioDueno);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteDueno);

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("Av. Americas 980");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91432");
        propiedad.setNumeroHabitaciones(4);
        propiedad.setNumeroBanos(1);
        propiedad.setNumeroPisos(2);
        propiedad.setNumeroCocina(1);
        propiedad.setMetrosCuadrados(7000);
        propiedad.setNumeroPersonas(3);
        propiedad.setAlquiler(2300);
        propiedad.setCompra(140000);
        propiedad.setAmueblado(1);
        propiedad.setCliente(clienteDueno);
        propiedad.setClaveCatastral("09PR61");
        propiedad.setEstadoOferta("Normal");
        propiedad.setGarage(1);
        propiedad.setNumeroAutos(2);

        int idCliente = gestorCliente.consultarIDClientePorCorreo(clienteDueno.getCorreo());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        gestorPropiedad.agregarPropiedad(propiedad, idCliente);

        Usuario usuarioAlquila = new Usuario();
        usuarioAlquila.setNombreUsuario("Shadow23");
        usuarioAlquila.setContrasena("gengar123");
        usuarioAlquila.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioAlquila);

        Cliente clienteAlquila = new Cliente();
        clienteAlquila.setNombre("Pablo");
        clienteAlquila.setApellidoPaterno("Villamil");
        clienteAlquila.setApellidoMaterno("Villamil");
        clienteAlquila.setEstadoCivil("Soltero");
        Date fechaNacimientoAlquilador = new Date(1997, 8, 24);
        clienteAlquila.setFechaNacimiento(fechaNacimientoAlquilador);
        clienteAlquila.setSexo("Hombre");
        clienteAlquila.setCorreo("pablovillamil@gmail.com");
        clienteAlquila.setTelefono("2283567895");
        clienteAlquila.setUsuario(usuarioAlquila);

        gestorCliente.insertarCliente(clienteAlquila);

        PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
        Date fechaEntrada = new Date(2024, 6, 25);
        Date fechaSalida = new Date(2024, 8, 25);
        Date fechaAlquiler= new Date(2024, 5, 12);

        propiedadAlquilada.setFechaEntrada(fechaEntrada);
        propiedadAlquilada.setFechaSalida(fechaSalida);
        propiedadAlquilada.setFechaAlquiler(fechaAlquiler);
        propiedadAlquilada.setPropiedad(propiedad);
        propiedadAlquilada.setCliente(clienteAlquila);

        PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
        int resusltado = gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada);

        int resultadoEsperado = 1;

        assertEquals(resultadoEsperado, resusltado);
    }

    @Test
    public void pruebaAlquilarPropiedadFallido() throws SQLException {
        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Pedro123");
        usuarioDueno.setContrasena("budin123");
        usuarioDueno.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Pedro");
        clienteDueno.setApellidoPaterno("Landa");
        clienteDueno.setApellidoMaterno("Landa");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1999, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("pedrolanda@gmail.com");
        clienteDueno.setTelefono("2282009988");
        clienteDueno.setUsuario(usuarioDueno);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteDueno);

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("Av. Americas 980");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91432");
        propiedad.setNumeroHabitaciones(4);
        propiedad.setNumeroBanos(1);
        propiedad.setNumeroPisos(2);
        propiedad.setNumeroCocina(1);
        propiedad.setMetrosCuadrados(7000);
        propiedad.setNumeroPersonas(3);
        propiedad.setAlquiler(2300);
        propiedad.setCompra(140000);
        propiedad.setAmueblado(1);
        propiedad.setCliente(clienteDueno);
        propiedad.setClaveCatastral("09PR61");
        propiedad.setEstadoOferta("Normal");
        propiedad.setGarage(1);
        propiedad.setNumeroAutos(2);

        int idCliente = gestorCliente.consultarIDClientePorCorreo(clienteDueno.getCorreo());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        gestorPropiedad.agregarPropiedad(propiedad, idCliente);

        Usuario usuarioAlquila = new Usuario();
        usuarioAlquila.setNombreUsuario("Shadow23");
        usuarioAlquila.setContrasena("gengar123");
        usuarioAlquila.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioAlquila);

        Cliente clienteAlquila = new Cliente();
        clienteAlquila.setNombre("Pablo");
        clienteAlquila.setApellidoPaterno("Villamil");
        clienteAlquila.setApellidoMaterno("Villamil");
        clienteAlquila.setEstadoCivil("Soltero");
        Date fechaNacimientoAlquilador = new Date(1997, 8, 24);
        clienteAlquila.setFechaNacimiento(fechaNacimientoAlquilador);
        clienteAlquila.setSexo("Hombre");
        clienteAlquila.setCorreo("pablovillamil@gmail.com");
        clienteAlquila.setTelefono("2283567895");
        clienteAlquila.setUsuario(usuarioAlquila);

        gestorCliente.insertarCliente(clienteAlquila);

        PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
        Date fechaEntrada = new Date(2024, 6, 25);
        Date fechaSalida = new Date(2024, 8, 25);
        Date fechaAlquiler= new Date(2024, 5, 12);

        propiedadAlquilada.setFechaEntrada(fechaEntrada);
        propiedadAlquilada.setFechaSalida(fechaSalida);
        propiedadAlquilada.setFechaAlquiler(fechaAlquiler);

        PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();

        assertThrows(NullPointerException.class, () -> gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada));
    }

    @Test
    public void pruebaConsultarPropiedadesAlquiladasPorPropiedadExitoso() throws SQLException {
        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Pedro123");
        usuarioDueno.setContrasena("budin123");
        usuarioDueno.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Pedro");
        clienteDueno.setApellidoPaterno("Landa");
        clienteDueno.setApellidoMaterno("Landa");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1999, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("pedrolanda@gmail.com");
        clienteDueno.setTelefono("2282009988");
        clienteDueno.setUsuario(usuarioDueno);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteDueno);

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("Av. Americas 980");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91432");
        propiedad.setNumeroHabitaciones(4);
        propiedad.setNumeroBanos(1);
        propiedad.setNumeroPisos(2);
        propiedad.setNumeroCocina(1);
        propiedad.setMetrosCuadrados(7000);
        propiedad.setNumeroPersonas(3);
        propiedad.setAlquiler(2300);
        propiedad.setCompra(140000);
        propiedad.setAmueblado(1);
        propiedad.setCliente(clienteDueno);
        propiedad.setClaveCatastral("09PR61");
        propiedad.setEstadoOferta("Normal");
        propiedad.setGarage(1);
        propiedad.setNumeroAutos(2);

        int idCliente = gestorCliente.consultarIDClientePorCorreo(clienteDueno.getCorreo());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        gestorPropiedad.agregarPropiedad(propiedad, idCliente);

        Usuario usuarioAlquila = new Usuario();
        usuarioAlquila.setNombreUsuario("Shadow23");
        usuarioAlquila.setContrasena("gengar123");
        usuarioAlquila.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioAlquila);

        Cliente clienteAlquila = new Cliente();
        clienteAlquila.setNombre("Pablo");
        clienteAlquila.setApellidoPaterno("Villamil");
        clienteAlquila.setApellidoMaterno("Villamil");
        clienteAlquila.setEstadoCivil("Soltero");
        Date fechaNacimientoAlquilador = new Date(1997, 8, 24);
        clienteAlquila.setFechaNacimiento(fechaNacimientoAlquilador);
        clienteAlquila.setSexo("Hombre");
        clienteAlquila.setCorreo("pablovillamil@gmail.com");
        clienteAlquila.setTelefono("2283567895");
        clienteAlquila.setUsuario(usuarioAlquila);

        gestorCliente.insertarCliente(clienteAlquila);

        PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
        Date fechaEntrada = new Date(2024, 6, 25);
        Date fechaSalida = new Date(2024, 8, 25);
        Date fechaAlquiler= new Date(2024, 5, 12);

        propiedadAlquilada.setFechaEntrada(fechaEntrada);
        propiedadAlquilada.setFechaSalida(fechaSalida);
        propiedadAlquilada.setFechaAlquiler(fechaAlquiler);
        propiedadAlquilada.setPropiedad(propiedad);
        propiedadAlquilada.setCliente(clienteAlquila);

        PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
        gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada);

        ArrayList<PropiedadAlquilada> propiedadesEsperadas = new ArrayList<PropiedadAlquilada>();
        propiedadesEsperadas.add(propiedadAlquilada);

        ArrayList<PropiedadAlquilada> propiedadesConsultadas = gestorPropiedadAlquilada.consultarPropiedadesAlquiladasPorPropiedad(propiedad);

        assertEquals(propiedadesEsperadas, propiedadesConsultadas);
    }

    @Test
    public void pruebaConsultarPropiedadesAlquiladasPorPropiedadFallido() throws SQLException {
        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Pedro123");
        usuarioDueno.setContrasena("budin123");
        usuarioDueno.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Pedro");
        clienteDueno.setApellidoPaterno("Landa");
        clienteDueno.setApellidoMaterno("Landa");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1999, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("pedrolanda@gmail.com");
        clienteDueno.setTelefono("2282009988");
        clienteDueno.setUsuario(usuarioDueno);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteDueno);

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("Av. Americas 980");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91432");
        propiedad.setNumeroHabitaciones(4);
        propiedad.setNumeroBanos(1);
        propiedad.setNumeroPisos(2);
        propiedad.setNumeroCocina(1);
        propiedad.setMetrosCuadrados(7000);
        propiedad.setNumeroPersonas(3);
        propiedad.setAlquiler(2300);
        propiedad.setCompra(140000);
        propiedad.setAmueblado(1);
        propiedad.setCliente(clienteDueno);
        propiedad.setClaveCatastral("09PR61");
        propiedad.setEstadoOferta("Normal");
        propiedad.setGarage(1);
        propiedad.setNumeroAutos(2);

        int idCliente = gestorCliente.consultarIDClientePorCorreo(clienteDueno.getCorreo());

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        gestorPropiedad.agregarPropiedad(propiedad, idCliente);

        Usuario usuarioAlquila = new Usuario();
        usuarioAlquila.setNombreUsuario("Shadow23");
        usuarioAlquila.setContrasena("gengar123");
        usuarioAlquila.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioAlquila);

        Cliente clienteAlquila = new Cliente();
        clienteAlquila.setNombre("Pablo");
        clienteAlquila.setApellidoPaterno("Villamil");
        clienteAlquila.setApellidoMaterno("Villamil");
        clienteAlquila.setEstadoCivil("Soltero");
        Date fechaNacimientoAlquilador = new Date(1997, 8, 24);
        clienteAlquila.setFechaNacimiento(fechaNacimientoAlquilador);
        clienteAlquila.setSexo("Hombre");
        clienteAlquila.setCorreo("pablovillamil@gmail.com");
        clienteAlquila.setTelefono("2283567895");
        clienteAlquila.setUsuario(usuarioAlquila);

        gestorCliente.insertarCliente(clienteAlquila);

        PropiedadAlquilada propiedadAlquilada = new PropiedadAlquilada();
        Date fechaEntrada = new Date(2024, 6, 25);
        Date fechaSalida = new Date(2024, 8, 25);
        Date fechaAlquiler= new Date(2024, 5, 12);

        propiedadAlquilada.setFechaEntrada(fechaEntrada);
        propiedadAlquilada.setFechaSalida(fechaSalida);
        propiedadAlquilada.setFechaAlquiler(fechaAlquiler);
        propiedadAlquilada.setPropiedad(propiedad);
        propiedadAlquilada.setCliente(clienteAlquila);

        PropiedadAlquiladaDAO gestorPropiedadAlquilada = new PropiedadAlquiladaDAO();
        gestorPropiedadAlquilada.alquilarPropiedad(propiedadAlquilada);

        ArrayList<PropiedadAlquilada> propiedadesEsperadas = new ArrayList<PropiedadAlquilada>();
        propiedadesEsperadas.add(propiedadAlquilada);

        ArrayList<PropiedadAlquilada> propiedadesConsultadas = gestorPropiedadAlquilada.consultarPropiedadesAlquiladasPorPropiedad(new Propiedad());

        assertNotEquals(propiedadesEsperadas, propiedadesConsultadas);
    }
}
