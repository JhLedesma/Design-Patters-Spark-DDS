package Factories;

import Modelo.Empresa.Cuenta;
import Modelo.Indicadores.Cuenta_Indicadores;

public class FactoryCuenta {
	public static Cuenta crearCuentaConValor(String nombre, int valor) {
		return new Cuenta(nombre, valor);
	}
	
	public static Cuenta_Indicadores crearCuenta(String nombre) {
		return new Cuenta_Indicadores(nombre);
	}
}
