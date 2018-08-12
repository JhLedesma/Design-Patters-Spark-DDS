package Modelo.Metodologias.Comparadores;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;


public class ComparadorUnario {
	
	public Empresa empresa;
	public Metodologia metodologia;
	
	public ComparadorUnario (Empresa empresa, Metodologia metodologia) {
		
		this.empresa = empresa;
		this.metodologia = metodologia;
		
	}
	
	public boolean evaluar() {
		
		return metodologia.cumple(empresa);
		
	}
	
	
}