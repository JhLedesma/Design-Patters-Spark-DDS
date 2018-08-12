package TestMetodologias.TestComparadores;

import static Factories.FactoryComparadores.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Comparadores.ComparadorGlobal;

public class TestComparadorGlobal extends TemplateTestComparadores {
	public static void comparacionGlobal(Metodologia metodologia, Boolean... resultadosEsperados) {
		ComparadorGlobal comparador = crearComparadorGlobal(metodologia);
		
		assertEquals(Arrays.asList(resultadosEsperados), comparador.comparar().stream().map(r -> r.isEvaluacion()).collect(Collectors.toList()));
	}
	
	@Test
	public void deberiaSerTodoVerdaderCuandoSeComparaConMetodologiaSeguraParaTodos() {
		comparacionGlobal(seguroParaTodos, true, true, true);
	}
	
	@Test
	public void deberiaSerTodoFalsoCuandoSeComparaConMetodologiaImposibleParaTodos() {
		comparacionGlobal(imposibleParaTodos, false, false, false);
	}
	
	@Test
	public void laUnicaQueSeAutosuperaEsA() {
		comparacionGlobal(autosuperacion, true, false, false);
	}
	
	@Test
	public void laMasTristeSigueSiendoB() {
		comparacionGlobal(muyTriste, false, true, false);
	}
}
