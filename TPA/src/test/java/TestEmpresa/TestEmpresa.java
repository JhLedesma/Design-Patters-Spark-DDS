package TestEmpresa;

import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Archivo.CargaBatch.EmpresaToken;
import Modelo.Excepciones.Empresas.EmpresaSinNombreException;
import Modelo.Excepciones.Empresas.EmpresaSinPeriodoException;
import Modelo.Excepciones.Empresas.NoEsLaMismaEmpresaException;
import Modelo.Excepciones.Empresas.NoExisteElPeriodoException;
import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;

import static Factories.FactoryCuenta.*;
import static Factories.FactoryPeriodo.*;
import static Factories.FactoryEmpresa.*;

public class TestEmpresa {
	static Cuenta cuenta1, cuenta2, cuenta3, cuenta4, cuenta5;

	static Periodo periodo1, periodo2, periodo3;

	static Empresa empresa;
	
	@Before
	public void iniciarTest() {
		iniciarCuentas();
		iniciarPeriodos();
		iniciarEmpresa();
	}
	
	public static void iniciarCuentas() {
		cuenta1 = crearCuentaConValor("XD", 680);
		cuenta2 = crearCuentaConValor("DX", 86);
		cuenta3 = crearCuentaConValor("Holis", 1000);
		cuenta4 = crearCuentaConValor("XD", 420);
		cuenta5 = crearCuentaConValor("XD", 9001);
	}
	
	public void iniciarPeriodos() {
		periodo1 = crearPeriodo(2006, cuenta1, cuenta2);
		periodo2 = crearPeriodo(2007, cuenta3, cuenta4);
		periodo3 = crearPeriodo(2008, cuenta5);
	}

	public void iniciarEmpresa() {
		empresa = crearEmpresa("Rolito INC.", periodo1, periodo2);
	}

	@Test(expected = EmpresaSinNombreException.class)
	public void noSeDeberianTenerEmpresasSinNombre() {
		crearEmpresa("", periodo1);
	}

	@Test(expected = EmpresaSinPeriodoException.class)
	public void noSeDeberianTenerEmpresasSinPeriodos() {
		crearEmpresa("No hay nada");
	}

	@Test
	public void sePuedenObtenerLosAniosDeLosPeriodos() {
		assertEquals(Arrays.asList(new Integer(2006), new Integer(2007)), empresa.obtenerAniosDeTodosLosPeriodos());
	}

	@Test
	public void sePuedeEncontrarUnPeriodo() {
		assertEquals(periodo1, empresa.buscarPeriodo(2006));
	}
	
	@Test
	public void sePuedeEncontrarLaMismaCuentaEnDosPeriodosDistintos() {
		assertEquals(empresa.buscarPeriodo(2006).buscarCuenta("XD").getNombre(), empresa.buscarPeriodo(2007).buscarCuenta("XD").getNombre());
	}

	@Test(expected = NoExisteElPeriodoException.class)
	public void noSeDeberiaEncontrarUnPeriodoQueNoExiste() {
		empresa.buscarPeriodo(new Integer(-1));
	}
	
	@Test(expected = NoEsLaMismaEmpresaException.class)
	public void noPuedoActualizarUnaEmpresaSiNoEsLaMisma() {
		empresa.actualizar(new EmpresaToken("Cualquiera", "QEPD", 2500, 7500));
	}
	
	@Test
	public void puedeTenerUnPeriodoQueTengo() {
		assertTrue(empresa.tienePeriodo(periodo1));
	}
	
	@Test
	public void noPuedeTenerUnPeriodoQueNoTiene() {
		assertFalse(empresa.tienePeriodo(periodo3));
	}
	
	@Test
	public void puedoActualizarUnaCuentaDeUnPeriodoDeUnaEmpresa() {
		EmpresaToken empresaActualizada = new EmpresaToken("Rolito INC.", "XD", 2006, 9001);
		
		empresa.actualizar(empresaActualizada);
		
		assertEquals(cuenta1.getValor(), new Integer(9001));
	}
	
	@Test
	public void puedoAgregarUnaCuentaDeUnPeriodoAUnaEmpresa() {
		EmpresaToken empresaActualizada = new EmpresaToken("Rolito INC.", "Yeah", 2006, 9220);
		
		empresa.actualizar(empresaActualizada);
		
		assertTrue(empresa.buscarPeriodo(2006).tieneCuenta(crearCuentaConValor("Yeah", 9220)));
	}
	
	@Test
	public void puedoAgregarUnPeriodoAUnaEmpresa() {
		EmpresaToken empresaActualizada = new EmpresaToken("Rolito INC.", "QEPD", 2001, 15);
		
		empresa.actualizar(empresaActualizada);
		
		assertTrue(empresa.tienePeriodo(crearPeriodo(2001, crearCuentaConValor("QEPD", 15))));
	}
}
