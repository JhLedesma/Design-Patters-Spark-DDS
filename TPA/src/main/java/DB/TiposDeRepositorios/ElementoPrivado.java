package DB.TiposDeRepositorios;

import Modelo.Usuarios.Usuario;

public interface ElementoPrivado extends TipoDeRepositorio {
	Usuario getUsuario();
	
	void setUsuario(Usuario unUsuario);
}
