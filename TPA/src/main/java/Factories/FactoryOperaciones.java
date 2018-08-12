package Factories;

import Modelo.Indicadores.Division;
import Modelo.Indicadores.Expresion;
import Modelo.Indicadores.Multiplicacion;
import Modelo.Indicadores.Parentesis;
import Modelo.Indicadores.Resta;
import Modelo.Indicadores.Suma;

public class FactoryOperaciones {
	public static Suma sumar(Expresion operandoIzquierdo) {
		return new Suma(operandoIzquierdo);
	}
	
	public static Suma sumar(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		return new Suma(operandoIzquierdo, operandoDerecho);
	}
	
	public static Resta restar(Expresion operandoIzquierdo) {
		return new Resta(operandoIzquierdo);
	}
	
	public static Resta restar(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		return new Resta(operandoIzquierdo, operandoDerecho);
	}
	
	public static Multiplicacion multiplicar(Expresion operandoIzquierdo) {
		return new Multiplicacion(operandoIzquierdo);
	}

	public static Multiplicacion multiplicar(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		return new Multiplicacion(operandoIzquierdo, operandoDerecho);
	}
	
	public static Division dividir(Expresion operandoIzquierdo) {
		return new Division(operandoIzquierdo);
	}
	
	public static Division dividir(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		return new Division(operandoIzquierdo, operandoDerecho);
	}
	
	public static Parentesis parentesis(Expresion expresion) {
		return new Parentesis(expresion);
	}
}
