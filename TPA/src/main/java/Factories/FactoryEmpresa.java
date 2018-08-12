package Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;

public class FactoryEmpresa {
	public static Empresa crearEmpresa(String nombre, Periodo... periodos) {
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();
		listaPeriodos.addAll(Arrays.asList(periodos));
		
		return new Empresa(nombre, listaPeriodos);
	}
}
