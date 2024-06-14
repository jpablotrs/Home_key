package mx.testeos;

import mx.homek.dataaccess.ConexionBaseDeDatos;
import mx.homek.logic.implementaciones.ClienteDAO;
import mx.homek.logic.implementaciones.CompraPropiedadDAO;
import mx.homek.logic.implementaciones.PropiedadDAO;
import mx.homek.logic.implementaciones.UsuarioDAO;
import mx.homek.logic.objetoDeTransferencia.Cliente;
import mx.homek.logic.objetoDeTransferencia.CompraPropiedad;
import mx.homek.logic.objetoDeTransferencia.Propiedad;
import mx.homek.logic.objetoDeTransferencia.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PruebaComprarPropiedadDAO {
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
        ejecutarConsultasBase("Delete from cliente");
        ejecutarConsultasBase("Delete from comprapropiedad");
        ejecutarConsultasBase("Delete from propiedad");
        ejecutarConsultasBase("Delete from usuario");
    }

    @Test
    public void pruebaComprarPropiedadExitoso() throws SQLException {
        Usuario usuarioComprador = new Usuario();
        usuarioComprador.setNombreUsuario("Kiwi78");
        usuarioComprador.setContrasena("12");
        usuarioComprador.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioComprador);

        Cliente clienteComprador = new Cliente();
        clienteComprador.setNombre("Juan");
        clienteComprador.setApellidoPaterno("Garcia");
        clienteComprador.setApellidoMaterno("Garcia");
        clienteComprador.setEstadoCivil("Soltero");
        Date fechaNacimientoComprador = new Date(1989, 6, 17);
        clienteComprador.setFechaNacimiento(fechaNacimientoComprador);
        clienteComprador.setSexo("Hombre");
        clienteComprador.setCorreo("juangarcia@gmail.com");
        clienteComprador.setTelefono("2281090912");
        clienteComprador.setUsuario(usuarioComprador);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteComprador);

        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Kiwi78");
        usuarioDueno.setContrasena("kiwi09");
        usuarioDueno.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Juan");
        clienteDueno.setApellidoPaterno("Garcia");
        clienteDueno.setApellidoMaterno("Garcia");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1989, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("juangarcia@gmail.com");
        clienteDueno.setTelefono("2281090912");
        clienteDueno.setUsuario(usuarioDueno);

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

        CompraPropiedad propiedadComprada = new CompraPropiedad();
        propiedadComprada.setFecha(new Date(2023, 9, 15));
        propiedadComprada.setCliente(clienteComprador);
        propiedadComprada.setPropiedad(propiedad);

        CompraPropiedadDAO gestorPropiedadComprada = new CompraPropiedadDAO();
        int resultado = gestorPropiedadComprada.insertarCompraPropiedad(propiedadComprada);

        int resultadoEsperado = 1;

        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void pruebaComprarPropiedadFallido() throws SQLException {
        Usuario usuarioComprador = new Usuario();
        usuarioComprador.setNombreUsuario("Kiwi78");
        usuarioComprador.setContrasena("12");
        usuarioComprador.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioComprador);

        Cliente clienteComprador = new Cliente();
        clienteComprador.setNombre("Juan");
        clienteComprador.setApellidoPaterno("Garcia");
        clienteComprador.setApellidoMaterno("Garcia");
        clienteComprador.setEstadoCivil("Soltero");
        Date fechaNacimientoComprador = new Date(1989, 6, 17);
        clienteComprador.setFechaNacimiento(fechaNacimientoComprador);
        clienteComprador.setSexo("Hombre");
        clienteComprador.setCorreo("juangarcia@gmail.com");
        clienteComprador.setTelefono("2281090912");
        clienteComprador.setUsuario(usuarioComprador);

        ClienteDAO gestorCliente = new ClienteDAO();
        gestorCliente.insertarCliente(clienteComprador);

        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Kiwi78");
        usuarioDueno.setContrasena("kiwi09");
        usuarioDueno.setTipoUsuario("Cliente");

        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Juan");
        clienteDueno.setApellidoPaterno("Garcia");
        clienteDueno.setApellidoMaterno("Garcia");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1989, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Hombre");
        clienteDueno.setCorreo("juangarcia@gmail.com");
        clienteDueno.setTelefono("2281090912");
        clienteDueno.setUsuario(usuarioDueno);

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

        CompraPropiedad propiedadComprada = new CompraPropiedad();
        propiedadComprada.setFecha(new Date(2023, 9, 15));
        propiedadComprada.setCliente(clienteComprador);

        CompraPropiedadDAO gestorPropiedadComprada = new CompraPropiedadDAO();
        assertThrows(NullPointerException.class, () -> gestorPropiedadComprada.insertarCompraPropiedad(propiedadComprada));
    }

    @Test
    public void pruebaOfertarPropiedadExitoso() throws SQLException {
        Usuario usuarioDueno = new Usuario();
        usuarioDueno.setNombreUsuario("Lau009");
        usuarioDueno.setContrasena("lau654");
        usuarioDueno.setTipoUsuario("Cliente");

        UsuarioDAO gestorUsuario = new UsuarioDAO();
        gestorUsuario.insertarUsuario(usuarioDueno);

        Cliente clienteDueno = new Cliente();
        clienteDueno.setNombre("Laura");
        clienteDueno.setApellidoPaterno("Luna");
        clienteDueno.setApellidoMaterno("Luna");
        clienteDueno.setEstadoCivil("Soltero");
        Date fechaNacimientoDueno = new Date(1989, 6, 17);
        clienteDueno.setFechaNacimiento(fechaNacimientoDueno);
        clienteDueno.setSexo("Mujer");
        clienteDueno.setCorreo("lauluna@gmail.com");
        clienteDueno.setTelefono("281634501");
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

        CompraPropiedadDAO gestorPropiedadComprada = new CompraPropiedadDAO();
        int idPropiedad = gestorPropiedad.consultarIDPropiedadPorClaveCatastral(propiedad.getClaveCatastral());
        int resultado = gestorPropiedadComprada.ofertarPropiedad(idPropiedad);

        int resultadoEsperado = 1;

        assertEquals(resultadoEsperado, resultado);
    }
}
