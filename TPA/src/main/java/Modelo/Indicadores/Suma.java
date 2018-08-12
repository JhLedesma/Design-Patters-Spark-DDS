package Modelo.Indicadores;

import java.math.BigDecimal;

public class Suma extends Operacion {

	public Suma(Expresion valorA) {
		super(valorA);
	}
	
	public Suma(Expresion valorA, Expresion valorB) {
		super(valorA, valorB);
	}
	
	public Suma(){};
	
	@Override
	public BigDecimal realizarOperacion(Query query) {
		return this.valorA.calcular(query).add(this.valorB.calcular(query));
	}
	
	public String miOperador() {
		return "+";
	}
}
