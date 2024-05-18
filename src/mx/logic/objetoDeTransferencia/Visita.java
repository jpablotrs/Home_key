package mx.logic.objetoDeTransferencia;

import java.util.Date;

public class Visita {
    private Date fecha;
    private String horaEntrada;
    private String horaSalida;
    private Propiedad propiedad;

    public Visita() {

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

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Visita)) {
            return false;
        }

        Visita visita = (Visita) obj;
        return (this == visita) || (visita.getFecha().equals(this.fecha) &&
                visita.getHoraEntrada().equals(this.horaEntrada) && visita.getHoraSalida().equals(this.horaSalida) &&
                visita.getPropiedad().equals(this.propiedad));
    }
}
