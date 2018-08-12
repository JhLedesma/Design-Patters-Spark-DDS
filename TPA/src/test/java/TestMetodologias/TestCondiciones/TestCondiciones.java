package TestMetodologias.TestCondiciones;

import static Factories.FactoryCuenta.*;
import static Factories.FactoryIndicador.crearIndicador;
import static Factories.FactoryNumero.crearNumero;
import static Factories.FactoryOperaciones.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Archivo.LectorDeArchivos;
import Archivo.Empresa.CSVParser;
import DB.Proveedores.ProveedorMock;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;


public abstract class TestCondiciones {
	
	@Before
	public void x() throws IOException{
		RepositorioEmpresas.getInstancia().setProveedor(instanciarEmpresas());
		RepositorioIndicadores.getInstancia().setProveedor(instanciarIndicadores());
	}
	
	@Test
	public void aplicarMayorAListaVacia() {
		List<Integer> list = new ArrayList<Integer>();
		
		Assert.assertEquals(true, list.stream().allMatch(num -> num >2));			
	}
	
	@Test
	public void estaEntreDebeRetornar_UnaListaVacia_SiNotieneLaCantidadDePeriodosNecesariosParaEvaluar(){
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		Assert.assertTrue(empresa.getPeriodos().stream().filter(periodo -> periodo.estaEntre(20, empresa.getPeriodos(), periodo)).collect(Collectors.toList()).size() == 0);
	}
	
	public static ProveedorMock<Empresa> instanciarEmpresas() throws IOException {
		return new ProveedorMock<Empresa>(new CSVParser(",").parse(new LectorDeArchivos().getFile("repositorioEmpresasMock.csv")));
	}
	
	public static ProveedorMock<Indicador> instanciarIndicadores() {
		Indicador b, c, d, TieneSoloUnaConstante, TieneSoloUnaCuenta, IndicadorConOperaciones, TieneSoloUnaCuentaV2, AAA;
		
		b = crearIndicador("b", crearNumero(453));
		c = crearIndicador("c", b);
		d = crearIndicador("d", c);
		TieneSoloUnaConstante = crearIndicador("TieneSoloUnaConstante", crearNumero(1000));
		TieneSoloUnaCuenta = crearIndicador("TieneSoloUnaCuenta", crearCuenta("EDITBA"));
		IndicadorConOperaciones = crearIndicador("IndicadorConOperaciones", sumar(multiplicar(sumar(sumar(crearNumero(100), crearCuenta("EDITBA")), crearNumero(50)), crearNumero(1)), b));
		TieneSoloUnaCuentaV2 = crearIndicador("TieneSoloUnaCuentaV2", crearCuenta("Free Cash Flow"));
		AAA = crearIndicador("AAA", restar(sumar(crearNumero(20), crearCuenta("xs")), crearNumero(4)));
		
		return new ProveedorMock<Indicador>(b, c, d, TieneSoloUnaConstante, TieneSoloUnaCuenta, IndicadorConOperaciones, TieneSoloUnaCuentaV2, AAA);
	}
	
}
		
