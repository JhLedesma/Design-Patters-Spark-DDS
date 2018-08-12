package Modelo.Metodologias.Resultados;

import java.util.ArrayList;
import java.util.List;

import Modelo.Empresa.Empresa;

public abstract class Resultado {
	
	protected String nombre;
	protected List<Empresa> listaEmpresas = new ArrayList<Empresa>();

	public Resultado(Empresa empresa){
		this.listaEmpresas.add(empresa);
	}

	public List<Empresa> getEmpresas() {
		return listaEmpresas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
}
