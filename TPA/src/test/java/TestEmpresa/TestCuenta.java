package TestEmpresa;

import Modelo.Empresa.Cuenta;
import Modelo.Excepciones.Empresas.CuentaConValorNegativoException;
import Modelo.Excepciones.Empresas.CuentaSinNombreException;

import org.junit.Assert;
import org.junit.Test;

import static Factories.FactoryCuenta.*;

public class TestCuenta {
	Cuenta prueba1 = crearCuentaConValor("FCF", 0);;

	@Test
	public void sePuedeInstanciarUnaCuentaConValorCero() {
		Assert.assertEquals(new Integer(0), prueba1.getValor());
	}

	@Test(expected = CuentaSinNombreException.class)
	public void noSeDeberiaInstanciarUnaCuentaSinNombre() {
		prueba1 = crearCuentaConValor("", 0);
	}

	@Test(expected = CuentaConValorNegativoException.class)
	public void noSeDeberiaInstanciarUnaCuentaConValorNegativo() {
		prueba1 = crearCuentaConValor("Esto Esta Mal", -1);
	}
	
	@Test
	public void sePuedeActualizarUnValor() {
		prueba1.actualizar(200);
		
		Assert.assertEquals(new Integer(200), prueba1.getValor());
	}
}
