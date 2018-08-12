package Factories;

import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Query;

public class FactoryQueryIndicador {
	public static Query consultar(Empresa empresa, Periodo periodo) {
		return new Query(empresa, periodo.getAnio());
	}
}
