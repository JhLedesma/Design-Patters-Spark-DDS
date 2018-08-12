package Factories;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Resultados.*;

public class FactoryResultado {
	public static Resultado empate(Empresa empresa1, Empresa empresa2) {
		return new Empate(empresa1, empresa2);
	}

	public static Resultado ganar(Empresa empresa) {
		return new Ganador(empresa);
	}
}
