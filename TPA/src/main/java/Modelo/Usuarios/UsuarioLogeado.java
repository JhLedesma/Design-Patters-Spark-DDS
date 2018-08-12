package Modelo.Usuarios;

import java.time.LocalDateTime;

public class UsuarioLogeado extends Usuario {
    private LocalDateTime cuandoExpira;

    public UsuarioLogeado(String email, LocalDateTime cuandoExpira) {
    	super(email, null);
        this.cuandoExpira = cuandoExpira;
    }

    public LocalDateTime getCuandoExpira() {
        return cuandoExpira;
    }
}
