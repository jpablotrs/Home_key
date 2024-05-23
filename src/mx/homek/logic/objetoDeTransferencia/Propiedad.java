package mx.homek.logic.objetoDeTransferencia;

public class Propiedad {
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String numeroHabitaciones;
    private String numeroBanos;
    private String numeroPisos;
    private String numeroCocina;
    private String metrosCuadrados;
    private String numeroPersonas;
    private String alquiler;
    private String compra;
    private Cliente cliente;
    private String claveCatastral;
    private String estadoOferta;

    public Propiedad(){

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

    public String getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(String numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public String getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(String numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public String getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(String numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public String getNumeroCocina() {
        return numeroCocina;
    }

    public void setNumeroCocina(String numeroCocina) {
        this.numeroCocina = numeroCocina;
    }

    public String getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(String metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(String numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(String alquiler) {
        this.alquiler = alquiler;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getClaveCatastral() {
        return claveCatastral;
    }

    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    public String getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(String estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Propiedad)) {
            return false;
        }

        Propiedad propiedad = (Propiedad) obj;
        return (this == propiedad) || (propiedad.getDireccion().equals(this.direccion) && propiedad.getCiudad().equals(this.ciudad) &&
                propiedad.getEstado().equals(this.estado) && propiedad.getCodigoPostal().equals(this.codigoPostal) &&
                propiedad.getNumeroHabitaciones() == this.numeroHabitaciones && propiedad.getNumeroBanos() == this.numeroBanos &&
                propiedad.getNumeroPisos() == this.numeroPisos && propiedad.getNumeroCocina() == this.numeroCocina &&
                propiedad.getMetrosCuadrados() == this.metrosCuadrados && propiedad.getNumeroPersonas() == this.numeroPersonas &&
                propiedad.getAlquiler() == this.alquiler && propiedad.getCompra() == this.compra && propiedad.getCliente().equals(this.cliente) &&
                propiedad.getClaveCatastral().equals(claveCatastral) && propiedad.getEstadoOferta().equals(this.estadoOferta));
    }
}
