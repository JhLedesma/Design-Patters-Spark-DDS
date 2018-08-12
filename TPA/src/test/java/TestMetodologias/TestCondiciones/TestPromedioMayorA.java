package TestMetodologias.TestCondiciones;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Condiciones.Condicion;
import Modelo.Metodologias.Condiciones.CondicionFactory;

public class TestPromedioMayorA extends TestCondiciones {
	
	@Test
	public void CumpleSi_LaEmpresaTiene_TodasLasCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	
	@Test
	public void noCumpleSi_LaEmpresaNoTiene_AlgunaCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1115000));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rip SA");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElPromedio_DelIndicadorQueSoloTieneUnNumero_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(3);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElPromedio_DelIndicadorQueSoloTieneUnNumero_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(3);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1500));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElPromedio_DelIndicadorQueTieneOtroIndicadorDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(2);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElPromedio_DelIndicadorQueTieneOtroIndicadorDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(2);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1500));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElPromedio_DelIndicadorQueTieneOperacionesrDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(5);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(1));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElPromedio_DelIndicadorQueTieneOperacionesrDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(5);
		Condicion condicion = new CondicionFactory().crearPromedioMayorA(indicador, new BigDecimal(20000));
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}

}
