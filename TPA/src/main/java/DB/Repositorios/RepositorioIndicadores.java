package DB.Repositorios;

import static DB.TiposDeRepositorios.NombreRepositorio.INDICADOR;

import DB.TiposDeRepositorios.NombreRepositorio;
import Modelo.Indicadores.Indicador;

public class RepositorioIndicadores extends RepositorioPrivado<Indicador> {
	private static RepositorioIndicadores instancia = null;
	
	private RepositorioIndicadores(NombreRepositorio nombreTabla) {
		super(nombreTabla);
	}
	
	public static RepositorioIndicadores getInstancia() {
		if(instancia == null) instancia = new RepositorioIndicadores(INDICADOR);
		
		return instancia;
	}
	
}
