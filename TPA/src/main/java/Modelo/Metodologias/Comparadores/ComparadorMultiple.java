package Modelo.Metodologias.Comparadores;

import java.util.List;
import java.util.stream.Collectors;

import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Resultados.Resultado;
import Modelo.Metodologias.Resultados.ResultadoAdapterView;

public class ComparadorMultiple {

	public Empresa empresa;
	public Metodologia metodologia;

	public ComparadorMultiple(Empresa empresa, Metodologia metodologia){
		this.empresa = empresa;
		this.metodologia = metodologia;
	}
	
	public List<ResultadoAdapterView> comparar(){
		List<Empresa> listaEmpresas =  RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		return listaEmpresas.stream().map(emp -> generarResultadoViewAdapter(emp)).collect(Collectors.toList());
		
		/*List<ResultadoAdapterView> listaResultadosAdapter = new ArrayList<ResultadoAdapterView>();
		for(Empresa e :listaEmpresas){
			
			Resultado resultado = new ComparadorDual(empresa, e, metodologia).comparar();
			ResultadoAdapterView resultadoFinal = new ResultadoAdapterView(empresa.getNombre(), e.getNombre(), resultado.getNombre());
			listaResultadosAdapter.add(resultadoFinal);
		}
		return listaResultadosAdapter;*/
		
	}

	private ResultadoAdapterView generarResultadoViewAdapter(Empresa emp) {
		return new ResultadoAdapterView(empresa.getNombre(),emp.getNombre(),generarResultadoDual(emp).getNombre());
	}

	private Resultado generarResultadoDual(Empresa emp) {
		return new ComparadorDual(empresa, emp, metodologia).comparar();
	}
	
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}
}