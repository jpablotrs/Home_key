package mx.homek.logic.objetoDeTransferencia;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String tipoUsuario;
    private String contrasena;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Usuario)) {
            return false;
        }

        Usuario usuario = (Usuario) obj;
        return (this == usuario) || (usuario.getNombreUsuario().equals(this.nombreUsuario) && usuario.getContrasena().equals(this.contrasena) &&
                usuario.getTipoUsuario().equals(this.tipoUsuario));
    }
}
