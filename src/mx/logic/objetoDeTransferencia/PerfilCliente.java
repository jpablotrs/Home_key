package mx.logic.objetoDeTransferencia;

public class PerfilCliente {
    private String ciudad;
    private String numeroHabitaciones;
    private String numeroBanos;
    private String estado;
    private String pisos;
    private String costoMaximo;
    private Cliente cliente;

    public PerfilCliente(){

    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPisos() {
        return pisos;
    }

    public void setPisos(String pisos) {
        this.pisos = pisos;
    }

    public String getCostoMaximo() {
        return costoMaximo;
    }

    public void setCostoMaximo(String costoMaximo) {
        this.costoMaximo = costoMaximo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof PerfilCliente)) {
            return false;
        }

        PerfilCliente perfilCliente = (PerfilCliente) obj;
        return (this == perfilCliente) || (perfilCliente.getCiudad().equals(this.ciudad) &&
                perfilCliente.getNumeroHabitaciones().equals(this.numeroHabitaciones) &&
                perfilCliente.getNumeroBanos().equals(this.numeroBanos) && perfilCliente.getEstado().equals(this.estado) &&
                perfilCliente.getPisos().equals(this.pisos) && perfilCliente.getCostoMaximo().equals(this.costoMaximo) &&
                perfilCliente.getCliente().equals(this.cliente));
    }
}
