package TestIndicadores;

import org.junit.Assert;
import org.junit.Test;

import Modelo.Indicadores.Query;

public class TestCalcularPrimitivas extends TemplateTestIndicadores{
	@Test
	public void sePuedeCalcularUnNumero() {
		Assert.assertEquals(20, evaluarEntero(natural));
	}

	@Test
	public void sePuedeCalcularUnaCuenta() {
		Assert.assertEquals(2000, evaluarEntero(ebitda));
	}
	
	@Test
	public void sePuedeCalcularUnIndicador() {
		Assert.assertEquals(-3120, evaluarEntero(roe));
	}
	
	@Test
	public void sePuedenCalcularLosValoresDeUnaCuentaParaDistintosAnios() {
		Assert.assertNotEquals(evaluarEntero(xd), xd.calcular(new Query(empresa, 2002)));
	}
}
