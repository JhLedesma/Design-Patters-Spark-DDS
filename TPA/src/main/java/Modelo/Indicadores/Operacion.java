package Modelo.Indicadores;

import java.math.BigDecimal;
import Modelo.Excepciones.Indicadores.FaltaOperandoDerechoException;

public abstract class Operacion implements Expresion {
	Expresion valorA;
	Expresion valorB;
	
	public Operacion(Expresion valorA) {
	    this.valorA = valorA;
	}
	
	public Operacion(Expresion valorA, Expresion valorB){
		this.valorA = valorA;
		this.valorB = valorB;
	}
	
	public Operacion(){};
	
	public BigDecimal calcular(Query query){
		this.puedeRealizarOperacion(query);
		return this.realizarOperacion(query);
	}
	
	public void puedeRealizarOperacion(Query query) {
		if(this.valorB == null) throw new FaltaOperandoDerechoException();
	}
	
	public abstract BigDecimal realizarOperacion(Query query);

	public void addOperando(Expresion operando){
		  this.valorB = operando;
	}

	protected String imprimirValorB() {
		if(valorB == null)
			return "";		
		
		return valorB.imprimirFormula();
	}
	
	abstract public String miOperador();
		
	public String imprimirFormula() {
		if(valorA == null)
			return "";
		
		return valorA.imprimirFormula() + " " + this.miOperador() + " " + this.imprimirValorB();
	}
	
}
