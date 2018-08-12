package TestIndicadores;

import org.junit.Test;
import org.junit.experimental.theories.Theory;

import static Factories.FactoryOperaciones.*;

import Modelo.Excepciones.Indicadores.FaltaOperandoDerechoException;
import Modelo.Indicadores.Expresion;
import Modelo.Indicadores.Suma;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;

public class TestCalcularSuma extends TemplateTestIndicadores{
	Suma sumaConNatural, sumaConEbitda, sumaConRoe, sumaConSumas;
	
	@Before
	public void iniciarSuma(){
		sumaConNatural = sumar(natural);
		sumaConEbitda = sumar(natural, ebitda);
		sumaConRoe = sumar(natural, roe);
		sumaConSumas = sumar(sumaConEbitda, sumaConEbitda);
	}
	
	@Test
	public void calcularSumaConsigoMismo(){
		sumaConNatural.addOperando(natural);
		
		Assert.assertEquals(40, evaluarEntero(sumaConNatural));
	}
	
	@Test
	public void calcularSumaConNeutro(){
		sumaConNatural.addOperando(cero);
		
		Assert.assertEquals(evaluar(natural), evaluar(sumaConNatural));
	}
	
	@Test
	public void calcularSumaDeEnteros(){
		sumaConNatural.addOperando(entero);
		
		Assert.assertEquals(-480, evaluarEntero(sumaConNatural));
	}
	
	@Test
	public void calcularSumaDeReales(){
		sumaConNatural.addOperando(realPositivo);
		
		Assert.assertEquals(new BigDecimal(520.35), evaluar(sumaConNatural));
	}
	
	@Test
	public void calcularSumaNumeroConCuenta(){
		sumaConNatural.addOperando(ebitda);
		
		Assert.assertEquals(new BigDecimal(2020), evaluar(sumaConNatural));
	}
	
	@Test
	public void calcularSumaCuentaConIndicador(){
		sumaConEbitda.addOperando(roe);
		
		Assert.assertEquals(new BigDecimal(-3100), evaluar(sumaConEbitda));
	}
	
	@Test
	public void calcularSumaConOtraSuma(){
		sumaConNatural.addOperando(sumaConEbitda);
		
		Assert.assertEquals(new BigDecimal(2040), evaluar(sumaConNatural));
	}
	
	@Test
	public void calcularSumaConSumaConOtraSuma(){
		Assert.assertEquals(new BigDecimal(4040),evaluar(sumaConSumas));
	}
	
	@Test(expected = FaltaOperandoDerechoException.class)
	public void noSePuedeCalcularSumaSiFaltaElOperandoDerecho(){
		evaluar(sumaConNatural);
	}
	
	@Theory
	public void laSumaEsConmutativa(Expresion operandoIzquierdo, Expresion operandoDerecho)
	{
		int valor1 = evaluarEntero(sumar(operandoIzquierdo, operandoDerecho));
		int valor2 = evaluarEntero(sumar(operandoDerecho, operandoIzquierdo));
		
		Assert.assertEquals(valor1, valor2);
	}
}
