package TestEmpresa;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Modelo.Excepciones.Empresas.NoEsElMismoPeriodoException;
import Modelo.Excepciones.Empresas.PeriodoSinCuentasException;
import Modelo.Excepciones.Empresas.YaExisteLaCuentaException;
import Modelo.Excepciones.Indicadores.NoTieneLaCuentaException;
import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Periodo;

import static Factories.FactoryCuenta.*;
import static Factories.FactoryPeriodo.*;

public class TestPeriodo {
	Cuenta cuenta1, cuenta2, cuenta3;

	Periodo periodo;

	@Before
	public void iniciarCuentas() {
		cuenta1 = crearCuentaConValor("Rolito", 500);
		cuenta2 = crearCuentaConValor("Axxxel", 700);
		cuenta3 = crearCuentaConValor("Skybell", 800);
	}

	@Before
	public void iniciarPeriodo() {
		periodo = crearPeriodo(2000, cuenta1, cuenta2);
	}

	@Test(expected = PeriodoSinCuentasException.class)
	public void noSeDeberiaPoderCrearUnPeriodoSinCuentas() {
		crearPeriodo(2000);
	}
	
	@Test
	public void sePuedeBuscarUnaCuenta() {
		Cuenta cuentaBuscada = periodo.buscarCuenta("Rolito");

		Assert.assertEquals(cuenta1, cuentaBuscada);
	}

	@Test
	public void sePuedenEncontrarTodasLasCuentas() {
		Cuenta rolito = periodo.buscarCuenta("Rolito");
		Cuenta axxxel = periodo.buscarCuenta("Axxxel");

		Assert.assertEquals(cuenta1, rolito);
		Assert.assertEquals(cuenta2, axxxel);
	}

	@Test(expected = NoTieneLaCuentaException.class)
	public void noDeberiaEncontrarseUnaCuentaQueNoEstaEnElPeriodo() {
		periodo.buscarCuenta("PuseCualquierCosa");
	}

	@Test
	public void sePuedeAgregarUnaCuenta() {
		periodo.agregarCuenta(cuenta3);

		Assert.assertEquals(Arrays.asList(cuenta1, cuenta2, cuenta3), periodo.getCuentas());
	}

	@Test(expected = YaExisteLaCuentaException.class)
	public void noSeDeberiaAgregarUnaCuentaQueYaExsite() {
		periodo.agregarCuenta(cuenta1);
	}

	// No entiendo el m�todo 'Est� Entre', de hecho, no trabaja con el estado
	// interno de un Per�odo, as� que
	// no est� bien que es responsabilidad est� ah�.
	
	@Test
	public void puedoTenerUnaCuentaQueTengo() {
		Assert.assertTrue(periodo.tieneCuenta(cuenta1));
	}
	
	@Test
	public void noPuedoTenerUnaCuentaQueNoTengo() {
		Assert.assertFalse(periodo.tieneCuenta(cuenta3));
	}
	
	@Test
	public void sePuedeActualizarUnaCuentaDeUnPeriodo() {
		Periodo periodoActualizado = crearPeriodo(2000, crearCuentaConValor("Rolito", 600));
		
		periodo.actualizar(periodoActualizado);
		
		Assert.assertEquals(new Integer(600), periodo.buscarCuenta("Rolito").getValor());
	}
	
	@Test
	public void sePuedeAgregarCuentaNuevaAUnPeriodo() {
		Periodo periodoNuevo = crearPeriodo(2000, crearCuentaConValor("Ecks Dee", 1200));
		
		periodo.actualizar(periodoNuevo);
		
		Assert.assertTrue(periodo.tieneCuenta(crearCuentaConValor("Ecks Dee", 1200)));
	}
	
	@Test(expected = NoEsElMismoPeriodoException.class)
	public void noSePuedeActualizarUnPeriodoSiNoCoincidenLosAnios() {
		Periodo periodoNuevo = crearPeriodo(2001, crearCuentaConValor("Rolito", 1500));
		
		periodo.actualizar(periodoNuevo);
	}
}
