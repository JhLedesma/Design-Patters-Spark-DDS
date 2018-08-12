package Modelo.Indicadores;

import java.math.BigDecimal;

public class Multiplicacion extends Operacion {

	public Multiplicacion(){};
	
	public Multiplicacion(Expresion valorA) {
		super(valorA);
	}

	public Multiplicacion(Expresion valorA, Expresion valorB) {
		super(valorA, valorB);
	}

	@Override
	public BigDecimal realizarOperacion(Query query) {
		return this.valorA.calcular(query).multiply(this.valorB.calcular(query));
	}

	public String miOperador() {
		return "*";
	}
}