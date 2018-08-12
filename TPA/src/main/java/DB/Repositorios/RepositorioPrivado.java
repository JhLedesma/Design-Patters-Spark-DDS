package DB.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import DB.Excepciones.NoExistenObjetosException;
import DB.Repositorios.Excepciones.NoEsUnObjetoDelUsuarioException;
import DB.TiposDeRepositorios.ElementoPrivado;
import DB.TiposDeRepositorios.NombreRepositorio;
import Modelo.Usuarios.Usuario;

public class RepositorioPrivado<P extends ElementoPrivado> extends Repositorio<P> {
	private Usuario usuario;

	public RepositorioPrivado(NombreRepositorio nombreTabla) {
		super(nombreTabla);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<P> buscarListaDeObjetosDeUsuario() throws NoExistenObjetosException {
		return super.buscarListaDeObjetos().stream().filter(o -> o.getUsuario().equals(this.getUsuario())).collect(Collectors.toList());
	}

	public List<P> buscarListaDeObjetosDeUsuario(Usuario usuario) throws NoExistenObjetosException {
		return super.buscarListaDeObjetos().stream().filter(o -> o.getUsuario().equals(usuario)).collect(Collectors.toList());
	}

	public P buscarObjetoDeUsuario(String unNombre) {
		P objeto = super.buscarObjeto(unNombre);
		
		if(!objeto.getUsuario().equals(this.getUsuario())) throw new NoEsUnObjetoDelUsuarioException();
		
		return objeto;
	}

	public List<String> darListaNombresDeUsuario() {
		return this.buscarListaDeObjetos().stream().map(o -> o.getNombre()).collect(Collectors.toList());
	}

	public void eliminarObjetoDeUsuario(P unObjeto) {
		if(unObjeto.getUsuario().equals(this.getUsuario())) super.eliminarObjeto(unObjeto);
		
		else throw new NoEsUnObjetoDelUsuarioException();
	}
}
