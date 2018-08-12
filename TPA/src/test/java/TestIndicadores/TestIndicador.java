package TestIndicadores;

import static Factories.FactoryIndicador.crearIndicador;

import org.junit.Test;

import Modelo.Excepciones.Indicadores.IndicadorSinFormulaException;
import Modelo.Excepciones.Indicadores.IndicadorSinNombreException;
import Modelo.Indicadores.*;

import org.junit.Assert;

public class TestIndicador extends TemplateTestIndicadores {
	Indicador van = crearIndicador("VAN", roe);

	@Test
	public void sePuedeObtenerLaCadenaConLaFormulaDelIndicador() {
		Assert.assertEquals("( VAN = ( ROE = XD - 20 * -500 / EBITDA ) )", van.imprimirFormula());
	}

	@Test(expected = IndicadorSinNombreException.class)
	public void noSePuedeInstanciarIndicadorSinNombre() {
		crearIndicador("", cero);
	}

	@Test(expected = IndicadorSinFormulaException.class)
	public void noSePuedeInstanciarIndicadorSinFormula() {
		crearIndicador("No Tengo nada", null);
	}
}
