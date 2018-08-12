package Modelo.Indicadores;

import Modelo.Empresa.Empresa;

public class Query {

	Empresa empresa;
	Integer periodo;
	
	//Constructor
	public Query(Empresa _empresa, Integer _periodo){
	empresa = _empresa;
	periodo = _periodo;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public Integer getPeriodo() {
		return periodo;
	}
	
}
