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

public class TestMayorAValorEnPeriodos extends TestCondiciones {
	
	@Test
	public void CumpleSi_LaEmpresaTiene_TodasLasCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	
	@Test
	public void noCumpleSi_LaEmpresaNoTiene_AlgunaCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1115000), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rip SA");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void fallaSi_NoTieneLaCantidadDePeriodos_QueElUsuarioQuiereEvaluar() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1), 15);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueSoloTieneUnNumero_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(3);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueSoloTieneUnNumero_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(3);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1500), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueTieneOtroIndicadorDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(2);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueTieneOtroIndicadorDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(2);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1500), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueTieneOperacionesrDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(5);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueTieneOperacionesrDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(5);
		Condicion condicion = new CondicionFactory().crearMayorAEnPeriodos(indicador, new BigDecimal(20000), 1);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}

}
