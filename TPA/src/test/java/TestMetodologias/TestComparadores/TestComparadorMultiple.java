package TestMetodologias.TestComparadores;

import static Factories.FactoryComparadores.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import org.junit.Test;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Metodologia;
import Modelo.Metodologias.Comparadores.ComparadorMultiple;
import Modelo.Metodologias.Resultados.ResultadoAdapterView;


public class TestComparadorMultiple extends TemplateTestComparadores {
	public static void comparacionMultiple(Empresa empresa, Metodologia metodologia, String... resultadosEsperados) {
		ComparadorMultiple comparador = crearComparadorMultiple(empresa, metodologia);
		
		List<ResultadoAdapterView> resultados = comparador.comparar();
		
		assertEquals(Arrays.asList(resultadosEsperados),resultados.stream().map(r -> r.getResultado()).collect(Collectors.toList()));
	}
	
	@Test
	public void todasLasEmpresasEmpatanConMetodologiaSeguroParaTodos() {
		comparacionMultiple(a, seguroParaTodos, "Empate", "Empate", "Empate");
	}
	
	@Test
	public void todasLasEmpresasEmpatanConMetodologiaImposibleParaTodos() {
		comparacionMultiple(a, imposibleParaTodos, "Empate", "Empate", "Empate");
	}
	
	@Test
	public void LaEmpresaAEsLaQueMejorSeAutoSupera() {
		comparacionMultiple(a, autosuperacion, "Empate", "GanadorA", "GanadorA");
	}
	
	@Test
	public void LaEmpresaBEsLaMasTriste() {
		comparacionMultiple(b, muyTriste, "GanadorB", "Empate", "GanadorB");
	}
}
