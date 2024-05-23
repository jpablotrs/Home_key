package mx.testeos;

import mx.homek.logic.implementaciones.*;
import mx.homek.logic.objetoDeTransferencia.*;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class pruebasVisitaDAO {
    @Test
    public void pruebaInsertarVisitaBDSatisfactorio() throws SQLException {
        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = new Visita();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        PerfilClienteDAO perfilClienteDAO = new PerfilClienteDAO();
        PropiedadDAO propiedadDAO = new PropiedadDAO();

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("lalg");
        usuario.setTipoUsuario("Comprador");
        usuario.setContrasena("12345");
        usuarioDAO.insertarUsuario(usuario);

        Cliente cliente = new Cliente();
        Date fecha = new Date(2004, 05,04);
        cliente.setNombre("Luis Angel");
        cliente.setApellidoMaterno("Lopez");
        cliente.setApellidoPaterno("Garcia");
        cliente.setEstadoCivil("Soltero");
        cliente.setFechaNacimiento(fecha);
        cliente.setSexo("Masculino");
        cliente.setCorreo("la@gmail.com");
        cliente.setTelefono("2281237863");
        cliente.setUsuario(usuario);
        clienteDAO.insertarCliente(cliente);

        PerfilCliente perfilCliente = new PerfilCliente();
        perfilCliente.setCiudad("Ciudad de Mexico");
        perfilCliente.setCostoMaximo("2,499,999");
        perfilCliente.setEstado("Ciudad de Mexico");
        perfilCliente.setPisos("2");
        perfilCliente.setNumeroHabitaciones("3");
        perfilCliente.setNumeroBanos("3");
        perfilCliente.setCliente(cliente);
        perfilClienteDAO.insertarPerfilPreferenciasDelCliente(perfilCliente);

        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion("independencia");
        propiedad.setCiudad("xalapa");
        propiedad.setEstado("veracruz");
        propiedad.setCodigoPostal("91731");
        propiedad.setNumeroHabitaciones("3");
        propiedad.setNumeroBanos("2");
        propiedad.setNumeroPisos("2");
        propiedad.setNumeroCocina("1");
        propiedad.setMetrosCuadrados("280");
        propiedad.setNumeroPersonas("3");
        propiedad.setAlquiler("6000");
        propiedad.setCompra("2400000");
        propiedad.setClaveCatastral("0000-0000-0000");
        propiedad.setCliente(cliente);
        

        visita.setClaveCatastral(propiedad.getClaveCatastral());
        visita.setHoraEntrada("8:30am");
        visita.setHoraSalida("10:00am");
        Date fecha1 = new Date(2024, 05, 23);
        visita.setFecha(fecha1);
        visita.setEstado("Activa");
        visita.setCliente(cliente);
        visita.setPropiedad(propiedad);

        int registroExitoso = 1;

        int registro = visitaDAO.insertarVisita(visita);
    }
}
