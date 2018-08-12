package Modelo.Metodologias.Resultados;

import Modelo.Empresa.Empresa;

public class Ganador extends Resultado {
	
	public Ganador(Empresa empresa) {
		super(empresa);
		nombre = "Ganador" + empresa.getNombre();
	}


}
