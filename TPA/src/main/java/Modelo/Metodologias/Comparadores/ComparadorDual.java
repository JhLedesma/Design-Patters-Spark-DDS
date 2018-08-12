package Modelo.Metodologias.Comparadores;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Resultados.Empate;
import Modelo.Metodologias.Resultados.Ganador;
import Modelo.Metodologias.Resultados.Resultado;

public class ComparadorDual {

	private Empresa empresa1;
	private Empresa empresa2;
	private Metodologia metodologia;

	public ComparadorDual(Empresa empresa1, Empresa empresa2, Metodologia metodologia) {
		this.empresa1 = empresa1;
		this.empresa2 = empresa2;
		this.metodologia = metodologia;
	}
	
	public Resultado comparar(){
		
			  if (empresa1 == empresa2) { return new Empate(empresa1, empresa2); }
			  
			  boolean res1, res2;
			  res1 = metodologia.cumple(empresa1);
			  res2 = metodologia.cumple(empresa2);

			  if (res1 == res2) { return new Empate(empresa1, empresa2); }
			  
			  else {
			    if (res1) { return new Ganador(empresa1); }
			    else { return new Ganador(empresa2); }
			  }
			
	}

	public Empresa getEmpresa1() {
		return empresa1;
	}

	public Empresa getEmpresa2() {
		return empresa2;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

}
