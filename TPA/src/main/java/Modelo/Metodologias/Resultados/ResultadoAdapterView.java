package Modelo.Metodologias.Resultados;

import org.uqbar.commons.utils.Observable;

import Modelo.Metodologias.Metodologia;

@Observable
public class ResultadoAdapterView {
	
	
	public String empresaA;
	public String empresaAComparar;
	public String resultado;
	public String metodologia;
	public boolean evaluacion;
	
	public String getMetodologia() {
		return metodologia;
	}


	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}


	public boolean isEvaluacion() {
		return evaluacion;
	}


	public void setEvaluacion(boolean evaluacion) {
		this.evaluacion = evaluacion;
	}


	public String getEmpresaA() {
		return empresaA;
	}


	public void setEmpresaA(String empresaA) {
		this.empresaA = empresaA;
	}


	public String getEmpresaAComparar() {
		return empresaAComparar;
	}


	public void setEmpresaAComparar(String empresaAComparar) {
		this.empresaAComparar = empresaAComparar;
	}


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



	

	public ResultadoAdapterView(String empresaA, String empresaAComparar, String resultado) {
		
		this.empresaA = empresaA;
		this.empresaAComparar = empresaAComparar;
		this.resultado = resultado;
		
	}

	public ResultadoAdapterView(String empresaA, Metodologia metodologia,  boolean evaluacion) {
		
		this.empresaA = empresaA;
		this.metodologia = metodologia.getNombre();
		this.evaluacion = evaluacion;
		
	}

}
