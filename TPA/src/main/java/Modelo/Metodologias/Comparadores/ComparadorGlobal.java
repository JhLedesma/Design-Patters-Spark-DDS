package Modelo.Metodologias.Comparadores;

import java.util.List;
import java.util.stream.Collectors;

import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Resultados.ResultadoAdapterView;

public class ComparadorGlobal {
	

	public Metodologia metodologia;
	List<Empresa> listaEmpresas;

	public ComparadorGlobal(Metodologia metodologia){
		
		this.metodologia = metodologia;
		listaEmpresas = RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		
	}
	
	public List<ResultadoAdapterView> comparar(){
		List<Empresa> listaEmpresas = RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		return listaEmpresas.stream().map(emp -> new ResultadoAdapterView(emp.getNombre(), metodologia, generarResultadoUnario(emp))).collect(Collectors.toList());
		
		
		/*List<ResultadoAdapterView> listaResultadosAdapter = new ArrayList<ResultadoAdapterView>();
		for(int i=0; listaEmpresas.size()>i; i++){
			
			boolean resultado = new ComparadorUnario(listaEmpresas.get(i),metodologia).evaluar();
			ResultadoAdapterView resultadoFinal = new ResultadoAdapterView(listaEmpresas.get(i).getNombre(),metodologia, resultado);
			listaResultadosAdapter.add(resultadoFinal);
		}
		return listaResultadosAdapter;*/

	}

	private boolean generarResultadoUnario(Empresa emp) {
		return new ComparadorUnario(emp,metodologia).evaluar();
	}

}
