package Factories;

import java.math.BigDecimal;

import Modelo.Indicadores.Numero;

public class FactoryNumero {
	public static Numero crearNumero(int numero) {
		return new Numero(new BigDecimal(numero));
	}
	
	public static Numero crearNumero(float numero) {
		return new Numero(new BigDecimal(numero));
	}
	
	public static Numero crearNumero(double numero) {
		return new Numero(new BigDecimal(numero));
	}
}
