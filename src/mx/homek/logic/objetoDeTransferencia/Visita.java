package mx.homek.logic.objetoDeTransferencia;

import java.util.Date;

public class Visita {
    private Date fecha;
    private String horaEntrada;
    private String horaSalida;
    private Propiedad propiedad;
    private String estado;
    private Cliente cliente;
    private String claveCatastral;

    public Visita() {

    }

    public String getClaveCatastral() {
        return claveCatastral;
    }

    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Visita)) {
            return false;
        }

        Visita visita = (Visita) obj;
        return (this == visita) || (visita.getFecha().equals(this.fecha) &&
                visita.getHoraEntrada().equals(this.horaEntrada) && visita.getHoraSalida().equals(this.horaSalida) &&
                visita.getPropiedad().equals(this.propiedad) && visita.getEstado().equals(this.estado) &&
                visita.getCliente().equals(this.cliente));
    }
}
