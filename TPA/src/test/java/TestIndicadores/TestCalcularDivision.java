package TestIndicadores;

import org.junit.Assert;
import org.junit.Test;

import static Factories.FactoryNumero.crearNumero;
import static Factories.FactoryOperaciones.dividir;

import Modelo.Excepciones.Indicadores.NoSePuedeDividirPorCeroException;
import Modelo.Indicadores.Division;
import Modelo.Indicadores.Numero;

public class TestCalcularDivision extends TemplateTestIndicadores {
	Numero dos = crearNumero(2);
	Numero cuatro = crearNumero(4);

	Division divisionExpectante;

	@Test(expected = NoSePuedeDividirPorCeroException.class)
	public void noSePuedeDividirPorCero() {
		divisionExpectante = dividir(natural, cero);
		evaluar(divisionExpectante);
	}

	@Test
	public void laDivisionNoEsConmutativa() {
		Division division1 = dividir(natural, entero);
		Division division2 = dividir(entero, natural);

		Assert.assertNotEquals(evaluar(division1), evaluar(division2));
	}

	@Test
	public void dividirDosVecesPorDosEsLoMismoQueDividirPorCuatro() {
		Division divisionPor4 = dividir(natural, cuatro);

		Division divisionPor2 = dividir(dividir(natural, dos), dos);

		Assert.assertEquals(evaluar(divisionPor4), evaluar(divisionPor2));
	}
}
