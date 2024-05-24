package mx.homek.logic.objetoDeTransferencia;

import java.util.Date;

public class Cliente {
    private int IdCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String estadoCivil;
    private Date fechaNacimiento;
    private String sexo;
    private String correo;
    private String telefono;
    private Usuario usuario;


    public Cliente(){

    }

    public int getIdCliente() { return IdCliente;}

    public void setIdCliente(int IdCliente) {this.IdCliente = IdCliente;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Cliente)) {
            return false;
        }

        Cliente cliente = (Cliente) obj;
        return (this == cliente) || (cliente.getNombre().equals(this.nombre) && cliente.getApellidoPaterno().equals(this.apellidoPaterno) &&
                cliente.getApellidoMaterno().equals(this.apellidoMaterno) && cliente.getEstadoCivil().equals(this.estadoCivil) &&
                cliente.getFechaNacimiento().equals(this.fechaNacimiento) && cliente.getSexo().equals(this.sexo) && cliente.getCorreo().equals(this.correo) &&
                cliente.getTelefono().equals(this.telefono) && cliente.getUsuario().equals(this.usuario));
    }

    public PerfilCliente getPerfilCliente() {
        return null;
    }
}
