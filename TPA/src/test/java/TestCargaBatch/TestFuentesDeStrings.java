package TestCargaBatch;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Archivo.CargaBatch.FuentesDeStrings.Archivo;
import Archivo.CargaBatch.FuentesDeStrings.MockArchivo;
import Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo.GestorDeArchivo;
import Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo.LimpiadorDeArchivo;
import Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo.RestauradorDeArchivo;

public class TestFuentesDeStrings {
	private MockArchivo mock;
	private static Archivo archivo;
	private static GestorDeArchivo limpiador, restaurador;
	
	@Before
	public void iniciarFuentes()
	{
		mock = new MockArchivo("Rolito,EDITBA,2000,8000", "Axel's Consortium Bags,FCF,2017,6969");
		archivo = new Archivo("repositorioEmpresasMock.csv");
		limpiador = new LimpiadorDeArchivo();
		restaurador = new RestauradorDeArchivo("repositorioEmpresasBackup.csv");
	}
	
	@AfterClass
	public static void restaurarFuentes()
	{
		
		restaurador.atender(archivo);
	}
	
	@Test
	public void sePuedenEscanearUnMock()
	{
		String[] esperado = {"Rolito,EDITBA,2000,8000", "Axel's Consortium Bags,FCF,2017,6969"};
		assertArrayEquals(esperado, mock.darLineas().toArray());
	}
	
	@Test
	public void unMockConAlgoNoEstaVacio()
	{
		assertFalse(mock.noTieneLineas());
	}

	public void unMockVacioMeTiraQueNoHayNadaQueEscanear()
	{
		assertTrue(new MockArchivo().noTieneLineas());
	}
	
	@Test
	public void sePuedeEscanearUnArchivo()
	{
		assertEquals(71, archivo.darLineas().size());
	}
	
	@Test
	public void siEscaneoDosVecesUnMismoArchivoLimpiadoNoObtengoMasLineas()
	{
		archivo.darLineas();
		
		limpiador.atender(archivo);
		
		assertTrue(archivo.noTieneLineas());
	}
}
