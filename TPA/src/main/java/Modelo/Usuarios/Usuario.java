package Modelo.Usuarios;

import org.uqbar.commons.utils.Observable;

import DB.TiposDeRepositorios.TipoDeRepositorio;

import javax.persistence.*;

@Entity
@Observable
@Table(name = "usuario")
public class Usuario implements TipoDeRepositorio {

    @Id
    @GeneratedValue
    @Column(name = "usuario_id")
    private long id;

    @Column(name = "usuario_email")
    private String nombre;

    @Column(name = "usuario_password_md5")
    private String passwordHasheada;

    @SuppressWarnings("unused")
	private Usuario(){};

    public Usuario(String email, String passwordHasheada) {
        this.nombre = email;
        this.passwordHasheada = passwordHasheada;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return nombre;
    }

    public String getPasswordHasheada() {
        return passwordHasheada;
    }

	public boolean chequearPassword(String password) {
		return this.passwordHasheada.equals(password);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != usuario.id) return false;
        if (!nombre.equals(usuario.nombre)) return false;
        return passwordHasheada.equals(usuario.passwordHasheada);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + nombre.hashCode();
        result = 31 * result + passwordHasheada.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }
}
