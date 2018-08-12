package TestIndicadores;

import static Factories.FactoryCuenta.crearCuenta;
import static Factories.FactoryCuenta.crearCuentaConValor;
import static Factories.FactoryEmpresa.crearEmpresa;
import static Factories.FactoryIndicador.crearIndicador;
import static Factories.FactoryNumero.crearNumero;
import static Factories.FactoryOperaciones.dividir;
import static Factories.FactoryOperaciones.multiplicar;
import static Factories.FactoryOperaciones.restar;
import static Factories.FactoryOperaciones.sumar;
import static Factories.FactoryPeriodo.crearPeriodo;
import static Factories.FactoryQueryIndicador.consultar;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Cuenta_Indicadores;
import Modelo.Indicadores.Expresion;
import Modelo.Indicadores.Indicador;
import Modelo.Indicadores.Numero;
import Modelo.Indicadores.Query;

@RunWith(Theories.class)
public class TemplateTestIndicadores {
	public static Numero natural = crearNumero(20);
	public static Numero uno = crearNumero(1);
	public static Numero cero = crearNumero(0);
	public static Numero entero = crearNumero(-500);
	public static Numero realPositivo = crearNumero(500.35);
	public static Numero realNegativo = crearNumero(-200.13);
	
	public static Cuenta_Indicadores ebitda = crearCuenta("EBITDA");
	public static Cuenta_Indicadores fcf = crearCuenta("FCF");
	public static Cuenta_Indicadores xd = crearCuenta("XD");
	public static Cuenta_Indicadores otroxd = crearCuenta("XD");

	public static Cuenta ebitdaConValor = crearCuentaConValor("EBITDA", 2000);
	public static Cuenta fcfConValor = crearCuentaConValor("FCF", 0);
	public static Cuenta xdConValor = crearCuentaConValor("XD", 12500);
	public static Cuenta otroxdConValor = crearCuentaConValor("XD", 12000);

	public static Indicador roe = crearIndicador("ROE", dividir(multiplicar(restar(xd, natural), entero), ebitda));
	public static Indicador roa = crearIndicador("ROA", sumar(roe, ebitda));

	public static Periodo periodo2001 = crearPeriodo(2001, ebitdaConValor, fcfConValor, xdConValor);
	public static Periodo periodo2002 = crearPeriodo(2002, otroxdConValor);

	public static Empresa empresa = crearEmpresa("Rolito", periodo2001, periodo2002);

	public static Query consulta = consultar(empresa, periodo2001);

	@DataPoints
	public static Expresion[] numerosReales = { natural, uno, cero, entero, realNegativo, realPositivo, ebitda, xd, roe, roa };

	public static BigDecimal evaluar(Expresion unaExpresion) {
		return unaExpresion.calcular(consulta);
	}

	public static int evaluarEntero(Expresion unaExpresion) {
		return evaluar(unaExpresion).intValue();
	}
	
	@Test
	public void paraQueNoRompa() {
		assertTrue(true);
	}
}
