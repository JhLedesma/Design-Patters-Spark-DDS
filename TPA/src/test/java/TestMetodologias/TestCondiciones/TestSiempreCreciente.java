package TestMetodologias.TestCondiciones;

import org.junit.Assert;
import org.junit.Test;

import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Condiciones.Condicion;
import Modelo.Metodologias.Condiciones.SiempreCreciente;

public class TestSiempreCreciente extends TestCondiciones {
	
	@Test
	public void CumpleSi_LaEmpresaTiene_TodasLasCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new SiempreCreciente(indicador, 5);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Tecno");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSi_LaEmpresaNoTiene_AlgunaCuenta() {
		
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(4);
		Condicion condicion = new SiempreCreciente(indicador, 5);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("TecnoPlus");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleCuando_ElIndicadorTieneSoloTieneUnNumero(){
		Indicador indicador = RepositorioIndicadores.getInstancia().buscarListaDeObjetos().get(3);
		Condicion condicion = new SiempreCreciente(indicador, 5);
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Tecno");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	

}
