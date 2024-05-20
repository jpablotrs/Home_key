package mx.logic.objetoDeTransferencia;

import java.sql.Blob;

public class Propiedad {
    private int idPropiedad;
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private int numeroHabitaciones;
    private int numeroBanos;
    private int numeroPisos;
    private int numeroCocina;
    private int metrosCuadrados;
    private int numeroPersonas;
    private int alquiler;
    private int compra;
    private int electricidad;
    private int amueblado;
    private Blob foto;
    private Cliente idCliente;
    private String claveCatastral;

    public Propiedad() {
    }


    public Cliente getCliente() {
        return idCliente;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public int getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(int numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public int getNumeroCocina() {
        return numeroCocina;
    }

    public void setNumeroCocina(int numeroCocina) {
        this.numeroCocina = numeroCocina;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getElectricidad() {
        return electricidad;
    }

    public void setElectricidad(int electricidad) {
        this.electricidad = electricidad;
    }

    public int getAmueblado() {
        return amueblado;
    }

    public void setAmueblado(int amueblado) {
        this.amueblado = amueblado;
    }

    public void setCliente(Cliente cliente) {
        this.idCliente = cliente;
    }
    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public String getClaveCatastral() {
        return claveCatastral;
    }

    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Propiedad)) {
            return false;
        }

        Propiedad propiedad = (Propiedad) obj;
        return (this == propiedad) ||
                (propiedad.getDireccion().equals(this.direccion) &&
                        propiedad.getCiudad().equals(this.ciudad) &&
                        propiedad.getEstado().equals(this.estado) &&
                        propiedad.getCodigoPostal().equals(this.codigoPostal) &&
                        propiedad.getNumeroHabitaciones() == this.numeroHabitaciones &&
                        propiedad.getNumeroBanos() == this.numeroBanos &&
                        propiedad.getNumeroPisos() == this.numeroPisos &&
                        propiedad.getNumeroCocina() == this.numeroCocina &&
                        propiedad.getMetrosCuadrados() == this.metrosCuadrados &&
                        propiedad.getNumeroPersonas() == this.numeroPersonas &&
                        propiedad.getAlquiler() == this.alquiler &&
                        propiedad.getCompra() == this.compra &&
                        propiedad.getElectricidad() == this.electricidad &&
                        propiedad.getAmueblado() == this.amueblado &&
                        ((this.foto == null && propiedad.getFoto() == null) || (this.foto != null && this.foto.equals(propiedad.getFoto()))) &&
                        propiedad.getIdCliente().equals(this.idCliente) &&
                        propiedad.getClaveCatastral().equals(this.claveCatastral));
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "idPropiedad=" + idPropiedad +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", numeroHabitaciones=" + numeroHabitaciones +
                ", numeroBanos=" + numeroBanos +
                ", numeroPisos=" + numeroPisos +
                ", numeroCocina=" + numeroCocina +
                ", metrosCuadrados=" + metrosCuadrados +
                ", numeroPersonas=" + numeroPersonas +
                ", alquiler=" + alquiler +
                ", compra=" + compra +
                ", electricidad=" + electricidad +
                ", amueblado=" + amueblado +
                ", foto=" + foto +
                ", cliente=" + idCliente +
                ", claveCatastral='" + claveCatastral + '\'' +
                '}';
    }

}
