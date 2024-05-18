package mx.logic.objetoDeTransferencia;

import java.util.Date;

public class PropiedadAlquilada {
    private Date fechaEntrada;
    private Date fechaSalida;
    private Date fechaAlquiler;
    private Propiedad propiedad;
    private Cliente cliente;

    public PropiedadAlquilada() {

    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof PropiedadAlquilada)) {
            return false;
        }

        PropiedadAlquilada propiedadAlquilada = (PropiedadAlquilada) obj;
        return (this == propiedadAlquilada) || (propiedadAlquilada.getFechaAlquiler().equals(this.fechaAlquiler) &&
                propiedadAlquilada.getFechaEntrada().equals(this.fechaEntrada) && propiedadAlquilada.getFechaSalida().equals(this.fechaSalida) &&
                propiedadAlquilada.getPropiedad().equals(this.propiedad) && propiedadAlquilada.getCliente().equals(this.cliente));
    }
}
