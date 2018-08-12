package Modelo.Metodologias.Resultados;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;

public class Evaluacion {
	private String empresa;
	private Boolean resultado;
	
	public Evaluacion(Empresa unaEmpresa, Metodologia unaMetodologia) {
		empresa = unaEmpresa.getNombre();
		resultado = unaMetodologia.cumple(unaEmpresa);
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Boolean getResultado() {
		return resultado;
	}
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
}
