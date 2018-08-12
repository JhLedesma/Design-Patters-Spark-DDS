package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;

import Modelo.Indicadores.Indicador;

public class CondicionFactory {

	public Promedio crearPromedioMayorA(Indicador indicador, BigDecimal valor){
		return new Promedio(indicador, valor, 1, "mayor");
	}
	
	public Promedio crearPromedioMenorA(Indicador indicador, BigDecimal valor){
		return new Promedio(indicador, valor, -1, "menor");
	}
	
	public Booleana crearMayorAEnPeriodos(Indicador indicador, BigDecimal valor, int anios){
		return new Booleana (indicador, valor, anios, 1, "mayor");
	}
	
	public Booleana crearMenorAEnPeriodos(Indicador indicador, BigDecimal valor, int anios){
		return new Booleana (indicador, valor, anios, -1, "menor");
	}
	
	public Mediana crearMedianaMayorA(Indicador indicador, BigDecimal valor){
		return new Mediana(indicador, valor, 1, "mayor");
	}
	
	public Mediana crearMedianaMenorA(Indicador indicador, BigDecimal valor){
		return new Mediana(indicador, valor, -1, "menor");
	}
	
	public Sumatoria crearSumatoriaMayorA(Indicador indicador, BigDecimal valor){
		return new Sumatoria(indicador, valor, 1, "mayor");
	}
	
	public Sumatoria crearSumatoriaMenorA(Indicador indicador, BigDecimal valor){
		return new Sumatoria(indicador, valor, -1, "menor");
	}
	
}
