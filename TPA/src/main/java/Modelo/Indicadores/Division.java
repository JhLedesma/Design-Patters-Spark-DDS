package Modelo.Indicadores;

import java.math.BigDecimal;

import Modelo.Excepciones.Indicadores.NoSePuedeDividirPorCeroException;

public class Division extends Operacion {
	
	public Division(){};

	public Division(Expresion valorA) {
		super(valorA);
	}

	public Division(Expresion valorA, Expresion valorB) {
		super(valorA, valorB);
	}
	
	@Override
	public void puedeRealizarOperacion(Query query){
		super.puedeRealizarOperacion(query);
		if(this.valorB.calcular(query).equals(new BigDecimal(0))) throw new NoSePuedeDividirPorCeroException();
	}

	@Override
	public BigDecimal realizarOperacion(Query query) {
		return this.valorA.calcular(query).divide(this.valorB.calcular(query), BigDecimal.ROUND_FLOOR);
	}

	public String miOperador() {
		return "/";
	}
}
