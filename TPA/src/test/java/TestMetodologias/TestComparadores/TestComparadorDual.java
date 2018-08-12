package TestMetodologias.TestComparadores;

import static Factories.FactoryComparadores.*;
import static Factories.FactoryResultado.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestComparadorDual extends TemplateTestComparadores {
	@Test
	public void compararUnaEmpresaConsigoMismaDaEmpate() {
		assertEquals(empate(a, a).getNombre(), crearComparadorDual(a, a, autosuperacion).comparar().getNombre());
	}
	
	@Test
	public void compararAutosuperacionAConBGanaA() {
		assertEquals(ganar(a).getNombre(), crearComparadorDual(a, b, autosuperacion).comparar().getNombre());
	}
	
	@Test
	public void compararAutosuperacionBConAGanaA() {
		assertEquals(ganar(a).getNombre(), crearComparadorDual(b, a, autosuperacion).comparar().getNombre());
	}
	
	@Test
	public void empateSiNingunaCumple() {
		assertEquals(empate(a, b).getNombre(), crearComparadorDual(a, b, imposible).comparar().getNombre());
	}
	
	@Test
	public void empateSiAmbosCumplen() {
		assertEquals(empate(a, b).getNombre(), crearComparadorDual(a, b, seguro).comparar().getNombre());
	}
	
	@Test
	public void noSePuedeCompararEmpresaCConAutosuperacion() {
		assertNotEquals(ganar(c).getNombre(), crearComparadorDual(a, c, autosuperacion).comparar().getNombre());
	}
}
