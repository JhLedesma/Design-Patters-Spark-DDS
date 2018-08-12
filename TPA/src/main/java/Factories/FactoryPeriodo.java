package Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Periodo;

public class FactoryPeriodo {
	public static Periodo crearPeriodo(int anio, Cuenta... cuentas) {
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		listaCuentas.addAll(Arrays.asList(cuentas));
		
		return new Periodo(anio, listaCuentas);
	}
}
