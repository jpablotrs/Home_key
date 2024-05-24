package mx.testeos;

import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.PropiedadAlquiladaDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class PruebaPropiedadAlquiladaDAO {
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
        propiedad.setDireccion("Av. Xalapa 720");
        propiedad.setCiudad("Xalapa");
        propiedad.setEstado("Veracruz");
        propiedad.setCodigoPostal("91002");
        propiedad.setNumeroHabitaciones(3);
        propiedad.setNumeroBanos(1);
        propiedad.setNumeroPisos(1);
        propiedad.setMetrosCuadrados(80);
        propiedad.setNumeroPersonas(2);
        propiedad.setAlquiler(1800);
        propiedad.setCompra(120000);
        propiedad.setElectricidad(1);
        propiedad.setAmueblado(1);
        propiedad.setCliente(clienteDueno);
        propiedad.setClaveCatastral("12AS78");
        propiedad.setEstadoOferta("Normal");

        PropiedadDAO gestorPropiedad = new PropiedadDAO();
        gestorPropiedad.agregarPropiedad(propiedad);

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
}
