package TestCargaBatch;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Contenedores.ContenedorDeStrings;
import Archivo.CargaBatch.Excepciones.NoHayNadaException;
import Archivo.CargaBatch.Excepciones.TieneErroresDeScanException;
import Archivo.CargaBatch.FuentesDeStrings.MockArchivo;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoPositivo;
import Archivo.CargaBatch.Scanners.CSV;

public class TestContenedorDeStrings {
	ContenedorDeStrings contenedor, contenedorMedioFallado, contenedorFallado, contenedorVacio;
	
	private ContenedorDeStrings inicioDeContenedor(String... texto) {
		MockArchivo fuente  = new MockArchivo(texto);
		CSV scanner = new CSV(",");
		
		return new ContenedorDeStrings(fuente, scanner);
	}
	
	@Before
	public void iniciarContenedores() {
		contenedor = inicioDeContenedor("Rolito,EDITBA,2000,8000", "Axel's Consortium Bags,FCF,2017,6969");
		contenedorVacio = inicioDeContenedor();
		contenedorMedioFallado = inicioDeContenedor("EDITBA,2000,8000", "Axel's Consortium Bags,FCF,2017,6969");
		contenedorFallado = inicioDeContenedor(",EDITBA,Lepra,8000", "Axel's Consortium Bags,FCF,2017,6969,Khe");
	}
	
	@Test
	public void sePuedeParsearExitosamente() {
		EmpresaToken empresa1 = new EmpresaToken("Rolito", "EDITBA", "2000", "8000");
		EmpresaToken empresa2 = new EmpresaToken("Axel's Consortium Bags", "FCF", "2017", "6969");
		
		List<EmpresaToken> escaneo = contenedor.serEscaneado().stream().map(r -> r.devolverResultado()).collect(Collectors.toList());
		
		EmpresaToken[] esperado = {empresa1, empresa2};
		EmpresaToken[] actual = {escaneo.get(0), escaneo.get(1)};
		
		assertArrayEquals(esperado, actual);
	}
	
	@Test
	public void HayDosResultadosPositivosEnUnContenedorCorrecto() {	
		assertTrue(contenedor.serEscaneado().stream().allMatch(r -> r.getClass().equals(ResultadoPositivo.class)));
	}
	
	@Test
	public void hayUnResultadoPositivoSiHayUnErrorEnElContenedor() {
		assertEquals(1, contenedorMedioFallado.serEscaneado().stream().filter(r -> r.getClass().equals(ResultadoPositivo.class)).collect(Collectors.toList()).size());
	}
	
	@Test(expected = TieneErroresDeScanException.class)
	public void soloSeEscaneanLasCosasSinErroresDeUnContenedor() {
		EmpresaToken esperado = new EmpresaToken("Axel's Consortium Bags", "FCF", "2017", "6969");
		
		EmpresaToken actual = contenedorMedioFallado.serEscaneado().get(1).devolverResultado();
		
		assertEquals(actual, esperado);
		
		contenedorMedioFallado.serEscaneado().get(0).devolverResultado();
	}
	
	@Test
	public void siSoloHayErroresNoDeberianHaberResultadosPositivos() {
		assertTrue(contenedorFallado.serEscaneado().stream().noneMatch(r -> r.getClass().equals(ResultadoPositivo.class)));
	}
	
	@Test(expected = NoHayNadaException.class)
	public void noDeberiaEscanearseNadaSiNoHayNada() {
		contenedorVacio.serEscaneado();
	}
}
