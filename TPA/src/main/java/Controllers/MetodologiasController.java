package Controllers;

import java.util.Map;
import java.util.stream.Collectors;

import DB.Excepciones.NoExisteObjetoConEseNombreException;
import DB.Excepciones.NoExistenObjetosException;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioMetodologias;
import DB.Repositorios.RepositorioUsuarios;
import DB.Repositorios.Excepciones.NoEsUnObjetoDelUsuarioException;
import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Resultados.Evaluacion;
import Modelo.Usuarios.GestorDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	public ModelAndView listarMetodologias(Request request, Response response) {
		Map<Object, Object> modelo = GestorDeUsuarios.getInstance().obtenerMapa(request);

		String ruta = "";

		if (modelo.get("email") != null){
			try
			{
				RepositorioMetodologias.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto((String) modelo.get("email")));

				modelo.put("metodologias", RepositorioMetodologias.getInstancia().buscarListaDeObjetosDeUsuario());

				ruta = "Metodologias/metodologiasComparacion.hbs";
			}
			catch (NoExistenObjetosException excepcion)
			{
				ruta = "Metodologias/metodologiasInexistentes.hbs";
			}
			catch (NoEsUnObjetoDelUsuarioException excepcion)
			{
				ruta = "Metodologias/metodologiasProhibidas.hbs";
			}

			return new ModelAndView(modelo, ruta);
		}else{
			response.redirect("/login");
			return null;
		}
		

	}

	public ModelAndView mostrarComparacion(Request request, Response response) {
		Map<Object, Object> modelo = GestorDeUsuarios.getInstance().obtenerMapa(request);
		
		String ruta = "";
		
		try
		{
			RepositorioMetodologias.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto((String) modelo.get("email")));
			
			modelo.put("metodologias", RepositorioMetodologias.getInstancia().buscarListaDeObjetosDeUsuario());
			
			Metodologia metodologiaElegida = RepositorioMetodologias.getInstancia().buscarObjetoDeUsuario(request.queryParams("metodologia"));	
			
			modelo.put("resultados", RepositorioEmpresas.getInstancia().buscarListaDeObjetos().stream().map(e -> new Evaluacion(e, metodologiaElegida)).collect(Collectors.toList()));
			
			modelo.put("metodologiaElegida", metodologiaElegida);
			
			ruta = "Metodologias/metodologiasResultado.hbs";
		}
		catch (NoExistenObjetosException excepcion)
		{
			ruta = "Metodologias/metodologiasInexistentes.hbs";
		}
		catch (NoEsUnObjetoDelUsuarioException excepcion)
		{
			ruta = "Metodologias/metodologiasProhibidas.hbs";
		}
		catch (NoExisteObjetoConEseNombreException excepcion)
		{
			modelo.put("metodologias", RepositorioMetodologias.getInstancia().buscarListaDeObjetosDeUsuario());
			
			modelo.put("metodologiaInexistente", request.queryParams("metodologia"));
			
			ruta = "Metodologias/metodologiasComparacionErronea.hbs";
		}
		
		return new ModelAndView(modelo, ruta);
	}
}
