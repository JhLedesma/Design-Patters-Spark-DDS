package Factories;

import java.math.BigDecimal;

import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Condiciones.*;

public class FactoryCondiciones {
	public static Condiciones crearMayorAEnPeriodos(Indicador indicador, int valor, int anios) {
		return new Booleana(indicador, new BigDecimal(valor), anios, 1, "mayor");
	}

	public static Condiciones crearMenorAEnPeriodos(Indicador indicador, int valor, int anios) {
		return new Booleana(indicador, new BigDecimal(valor), anios, -1, "menor");
	}

	public static Condiciones crearPromedioMayorA(Indicador indicador, int valor) {
		return new Promedio(indicador, new BigDecimal(valor), 1, "mayor");
	}

	public static Condiciones crearPromedioMenorA(Indicador indicador, int valor) {
		return new Promedio(indicador, new BigDecimal(valor), -1, "menor");
	}
	
	public static Condiciones crearSumatoriaMayorA(Indicador indicador, int valor) {
		return new Sumatoria(indicador, new BigDecimal(valor), 1, "mayor");
	}

	public static Condiciones crearSumatoriaMenorA(Indicador indicador, int valor) {
		return new Sumatoria(indicador, new BigDecimal(valor), -1, "menor");
	}
	
	public static Condiciones crearMedianaMayorA(Indicador indicador, int valor) {
		return new Mediana(indicador, new BigDecimal(valor), 1, "mayor");
	}

	public static Condiciones crearMedianaMenorA(Indicador indicador, int valor) {
		return new Mediana(indicador, new BigDecimal(valor), -1, "menor");
	}
}
