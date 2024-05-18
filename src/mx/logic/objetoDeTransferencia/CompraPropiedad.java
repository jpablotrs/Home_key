package mx.logic.objetoDeTransferencia;

import java.util.Date;

public class CompraPropiedad{
    private Date fecha;
    private Cliente cliente;
    private Propiedad propiedad;

    public CompraPropiedad() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof CompraPropiedad)) {
            return false;
        }

        CompraPropiedad compraPropiedad = (CompraPropiedad) obj;
        return (this == compraPropiedad) || (compraPropiedad.getFecha().equals(this.fecha) &&
                compraPropiedad.getCliente().equals(this.cliente) && compraPropiedad.getPropiedad().equals(this.propiedad));
    }
}
