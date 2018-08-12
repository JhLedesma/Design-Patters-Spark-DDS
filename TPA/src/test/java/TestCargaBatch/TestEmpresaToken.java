package TestCargaBatch;

import org.junit.Test;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.FormatoAnioIncorrectoException;
import Archivo.CargaBatch.Excepciones.FormatoValorIncorrectoException;
import Modelo.Excepciones.Empresas.CuentaSinNombreException;
import Modelo.Excepciones.Empresas.EmpresaSinNombreException;

public class TestEmpresaToken {
	@Test
	public void sePuedeCrearUnaEmpresaCorrectamente()
	{
		new EmpresaToken("Rolito", "EDITBA", 2000, 8000);
	}
	
	@Test
	public void sePuedeCrearUnaEmpresaCorrectamenteParaCSV()
	{
		new EmpresaToken("Rolito", "EDITBA", "2000", "8000");
	}
	
	@Test(expected = EmpresaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaSinNombre()
	{
		new EmpresaToken("", "EDITBA", 2000, 8000);
	}
	
	@Test(expected = EmpresaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaSinNombreParaCSV()
	{
		new EmpresaToken("", "EDITBA", "2000", "8000");
	}
	
	@Test(expected = EmpresaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaConEspaciosEnBlancoSolo()
	{
		new EmpresaToken("   ", "EDITBA", 2000, 8000);
	}
	
	@Test(expected = EmpresaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaConEspaciosEnBlancoSoloParaCSV()
	{
		new EmpresaToken("   ", "EDITBA", "2000", "8000");
	}
	
	@Test
	public void sePuedeCrearUnaEmpresaConEspaciosEnBlanco()
	{
		new EmpresaToken("Axxxel's Consortium Bag", "EDITBA", 2000, 8000);
	}
	
	@Test
	public void sePuedeCrearUnaEmpresaConEspaciosEnBlancoParaCSV()
	{
		new EmpresaToken("Axxxel's Consortium Bag", "EDITBA", "2000", "8000");
	}
	
	@Test(expected = CuentaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaSinCuentaNominada()
	{
		new EmpresaToken("Rolito", "", 2000, 8000);
	}
	
	@Test(expected = CuentaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaSinCuentaNominadaParaCSV()
	{
		new EmpresaToken("Rolito", "", "2000", "8000");
	}
	
	@Test(expected = CuentaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaConCuentaConNombreConEspaciosSolo()
	{
		new EmpresaToken("Rolito", "   ", 2000, 8000);
	}
	
	@Test(expected = CuentaSinNombreException.class)
	public void noSePuedeCrearUnaEmpresaConCuentaConNombreConEspaciosSoloParaCSV()
	{
		new EmpresaToken("Rolito", "   ", "2000", "8000");
	}
	
	@Test
	public void sePuedeCrearUnaEmpresaConCuentaConNombreConEspacios()
	{
		new EmpresaToken("Rolito", "Que Dios Te Re Bendiga", 2000, 8000);
	}
	
	@Test
	public void sePuedeCrearUnaEmpresaConCuentaConNombreConEspaciosParaCSV()
	{
		new EmpresaToken("Rolito", "Que Dios Te Re Bendiga", "2000", "8000");
	}
	
	@Test(expected = FormatoAnioIncorrectoException.class)
	public void noSePuedeCrearUnaEmpresaConPeriodoErroneoEnCSV()
	{
		new EmpresaToken("Rolito", "EDITBA", "Lepra", "8000");
	}
	
	@Test(expected = FormatoValorIncorrectoException.class)
	public void noSePuedeCrearUnaEmpresaConValorErroneoEnCSV()
	{
		new EmpresaToken("Rolito", "EDITBA", "2000", "Lepra");
	}
}
