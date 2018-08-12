package DB.Repositorios;

import static DB.TiposDeRepositorios.NombreRepositorio.METODOLOGIA;

import DB.TiposDeRepositorios.NombreRepositorio;
import Modelo.Metodologias.Metodologia;

public class RepositorioMetodologias extends RepositorioPrivado<Metodologia> {
	private static RepositorioMetodologias instancia = null;
    
    private RepositorioMetodologias(NombreRepositorio nombreTabla) {
		super(nombreTabla);
	}
	
    public static RepositorioMetodologias getInstancia() {
    	if(instancia == null) instancia = new RepositorioMetodologias(METODOLOGIA);
    	
        return instancia;
    }

}
