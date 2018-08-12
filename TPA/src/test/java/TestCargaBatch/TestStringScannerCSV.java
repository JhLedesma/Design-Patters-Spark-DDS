package TestCargaBatch;

import static org.junit.Assert.*;

import org.junit.Test;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.CantidadCamposIncorrectosException;
import Archivo.CargaBatch.Excepciones.RenglonVacioException;
import Archivo.CargaBatch.Scanners.CSV;

public class TestStringScannerCSV {
	CSV scanner = new CSV(",");
	
	@Test
	public void CSVPuedeEscanearConComa()
	{
		String renglon = "Rolito,EDITBA,2000,8000";
		EmpresaToken esperado = new EmpresaToken("Rolito", "EDITBA", 2000, 8000);
		
		assertEquals(esperado, scanner.intentarEscanear(renglon));
	}
	
	@Test
	public void CSVPuedeEscanearConOtraCosaTurbia()
	{
		CSV scannerTurbio = new CSV("@@");
		String renglon = "Rolito@@EDITBA@@2000@@8000";

		EmpresaToken esperado = new EmpresaToken("Rolito", "EDITBA", 2000, 8000);
		
		assertEquals(esperado, scannerTurbio.intentarEscanear(renglon));
	}
	
	@Test(expected = RenglonVacioException.class)
	public void CSVRompeCuandoPongoAlgoVacio()
	{
		scanner.intentarEscanear("");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoNoHayDelimitadores()
	{
		scanner.intentarEscanear("Rolito");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoFaltan3Campos()
	{
		scanner.intentarEscanear("Rolito,");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoFaltan2Campos()
	{
		scanner.intentarEscanear("Rolito,EDITBA");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoFalta1Campo()
	{
		scanner.intentarEscanear("Rolito,EDITBA,2000");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoHayCamposDeMas()
	{
		scanner.intentarEscanear("Rolito,EDITBA,2000,8000,Khe");
	}
	
	@Test(expected = CantidadCamposIncorrectosException.class)
	public void CSVRompeCuandoElDelimitadorEsCualquiera()
	{
		scanner.intentarEscanear("Rolito/EDITBA/2000/8000");
	}
	
}
