package Modelo.Indicadores;

import java.math.BigDecimal;

public class Resta extends Operacion {
	
	public Resta(){};

	public Resta(Expresion valorA) {
		super(valorA);
	}

	public Resta(Expresion valorA, Expresion valorB) {
		super(valorA, valorB);
	}

	@Override
	public BigDecimal realizarOperacion(Query query) {
		return this.valorA.calcular(query).subtract(this.valorB.calcular(query));
	}

	public String miOperador() {
		return "-";
	}
}