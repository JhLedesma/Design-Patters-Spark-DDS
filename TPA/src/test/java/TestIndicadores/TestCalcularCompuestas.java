package TestIndicadores;

import java.math.BigDecimal;

import static Factories.FactoryOperaciones.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.Theory;

import Modelo.Indicadores.*;

public class TestCalcularCompuestas extends TemplateTestIndicadores {
	@Test
	public void seRespetanLasPrioridadesDeLasOperaciones() {
		assertEquals(evaluarEntero(sumar(natural, multiplicar(natural, natural))), evaluarEntero(sumar(natural, parentesis(multiplicar(natural, natural)))));
	}

	@Test
	public void sePuedeHacerUnCalculoConTodasLasOperacionesYValores() {
		Suma adicion = sumar(natural, entero);

		Multiplicacion producto = multiplicar(adicion, roe);

		Parentesis parentesis = parentesis(producto);

		Resta sustraccion = restar(parentesis, ebitda);

		Division resultado = dividir(sustraccion, natural);

		assertEquals(new BigDecimal(74780), evaluar(resultado));
	}

	@Theory
	public void seCumpleLaPropiedadDistributivaDeLaMultiplicacion(Expresion a, Expresion b) {
		Suma sinDistribuir = sumar(multiplicar(a, b), multiplicar(a, b));

		Multiplicacion conDistribucion = multiplicar(a, parentesis(sumar(b, b)));

		assertEquals(evaluar(sinDistribuir), evaluar(conDistribucion));
	}

	@Test
	public void elNumeradorPuedeDistribuirseRespectoDeLaSuma() {
		Division sinDistribucion = dividir(sumar(natural, xd), natural);

		Suma conDistribucion = sumar(dividir(natural, natural), dividir(xd, natural));

		assertEquals(evaluar(sinDistribucion), evaluar(conDistribucion));
	}
}
