package TestIndicadores;

import static Factories.FactoryOperaciones.multiplicar;
import static Factories.FactoryNumero.crearNumero;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theory;

import Modelo.Indicadores.Expresion;
import Modelo.Indicadores.Multiplicacion;

public class TestCalcularMultiplicacion extends TemplateTestIndicadores {
	Multiplicacion productoExpectante;
	Multiplicacion productoConNatural;
	
	@Before
	public void iniciarMultiplicacion(){
		productoConNatural = multiplicar(natural);
	}
	
	@Test
	public void elProductoDeUnNumeroYSuSimetricoDaElNeutro(){
		productoConNatural.addOperando(crearNumero(0.05));
		
		Assert.assertEquals(evaluarEntero(uno), evaluarEntero(productoConNatural));
	}
	
	@Theory
	public void laMultiplicacionEsConmutativa(Expresion operandoIzquierdo, Expresion operandoDerecho){
		int valor1 = evaluarEntero(multiplicar(operandoIzquierdo, operandoDerecho));
		int valor2 = evaluarEntero(multiplicar(operandoDerecho, operandoIzquierdo));
		
		Assert.assertEquals(valor1, valor2);
	}
	
	@Theory
	public void elUnoEsElNeutroDeLaMultiplicacion(Expresion unValor){
		productoExpectante = multiplicar(uno, unValor);
		
		Assert.assertEquals(evaluar(unValor), evaluar(productoExpectante));
	}
	
	@Theory
	public void elCeroEsAbsorbenteEnLaMultiplicacion(Expresion unValor){
		productoExpectante = multiplicar(cero, unValor);
		
		Assert.assertEquals(evaluarEntero(cero), evaluarEntero(productoExpectante));
	}
	
	@Theory
	public void multiplicarPorSignosDistintosDaNegativo(Expresion operandoIzquierdo, Expresion operandoDerecho){
		Assume.assumeTrue(operandoIzquierdo != cero && operandoDerecho != cero);
		Assume.assumeTrue(evaluar(operandoIzquierdo).signum() != evaluar(operandoDerecho).signum());
		
		Assert.assertTrue(evaluar(multiplicar(operandoIzquierdo, operandoDerecho)).signum() == -1);
	}
}
