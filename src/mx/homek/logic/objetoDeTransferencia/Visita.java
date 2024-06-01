package mx.homek.logic.objetoDeTransferencia;

import java.util.Date;

public class Visita {
    private int idVisita;
    private Date fecha;
    private String horaEntrada;
    private String horaSalida;
    private Propiedad propiedad;
    private Cliente cliente;
    private int estado;
    private String estadoDescripcion;

    public Visita() {

    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoDescripcion() {
        if (estado == 1)
            return "Vigente";
        else
            return "Cancelada";
    }


    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
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
                visita.getPropiedad().equals(this.propiedad)  &&
                visita.getCliente().equals(this.cliente));
    }

    @Override
    public String toString() {
        var sFecha = String.format("%02d/%02d/%02d", fecha.getDate(), fecha.getMonth()+1, fecha.getYear()+1900);
        return String.format("%s - %s, %s, %s", sFecha,
                this.propiedad.getDireccion(),
                this.propiedad.getCiudad(),
                this.propiedad.getEstado());
    }
}
