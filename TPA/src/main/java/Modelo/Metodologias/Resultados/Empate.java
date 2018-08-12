package Modelo.Metodologias.Resultados;

import Modelo.Empresa.Empresa;

public class Empate extends Resultado {

	public Empate(Empresa empresa1, Empresa empresa2) {
		super(empresa1);
		this.listaEmpresas.add(empresa2);
		nombre = "Empate";
	}
}
