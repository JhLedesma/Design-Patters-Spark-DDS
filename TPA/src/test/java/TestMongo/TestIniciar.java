package TestMongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DB.Excepciones.NoExistenObjetosException;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import DB.Repositorios.RepositorioMetodologias;
import DB.Repositorios.RepositorioUsuarios;
import Main.Bootstrap;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Metodologia;

public class TestIniciar {
	@Before
	public void iniciarRepositorios() {
		Bootstrap.iniciarRepositoriosDePrueba();
	}
	
	@Test(expected = NoExistenObjetosException.class)
	public void empresasVacias() {
		RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
	}
	
	@Test(expected = NoExistenObjetosException.class)
	public void indicadoresVacios() {
		RepositorioIndicadores.getInstancia().buscarListaDeObjetos();
	}
	
	@Test(expected = NoExistenObjetosException.class)
	public void metodologiasVacias() {
		RepositorioMetodologias.getInstancia().buscarListaDeObjetos();
	}
	
	@Test(expected = NoExistenObjetosException.class)
	public void iniciarEmpresasNoImplicaQueLosDemasReposNoEstenVacios() throws IOException {
		Bootstrap.chequearEmpresas();
		RepositorioIndicadores.getInstancia().buscarListaDeObjetos();
	}
	
	@Test
	public void sePuedeIniciarTodoCuandoNoHayNada() throws IOException {
		Bootstrap.iniciarObjetos();
		
		assertNotNull(RepositorioEmpresas.getInstancia().buscarListaDeObjetos());
		assertNotNull(RepositorioIndicadores.getInstancia().buscarListaDeObjetos());
		assertNotNull(RepositorioMetodologias.getInstancia().buscarListaDeObjetos());
	}
	
	@Test
	public void siVuelvoAIniciarNoMeDeberiaAgregarObjetos() throws IOException {
		Bootstrap.iniciarObjetos();
		
		List<Empresa> empresas1 = RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		List<Indicador> indicadores1 = RepositorioIndicadores.getInstancia().buscarListaDeObjetos();
		List<Metodologia> metodologias1 = RepositorioMetodologias.getInstancia().buscarListaDeObjetos();
		
		Bootstrap.iniciarObjetos();
		
		List<Empresa> empresas2 = RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		List<Indicador> indicadores2 = RepositorioIndicadores.getInstancia().buscarListaDeObjetos();
		List<Metodologia> metodologias2 = RepositorioMetodologias.getInstancia().buscarListaDeObjetos();
		
		assertEquals(empresas1, empresas2);
		assertEquals(indicadores1, indicadores2);
		assertEquals(metodologias1, metodologias2);
	}
	
	@Test
	public void cadaUsuarioTieneSusIndicadores() throws IOException {
		Bootstrap.iniciarObjetos();
		
		RepositorioIndicadores.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"));
		RepositorioMetodologias.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"));
		
		List<Indicador> indicadoresAxel = RepositorioIndicadores.getInstancia().buscarListaDeObjetosDeUsuario();
		List<Metodologia> metodologiasAxel = RepositorioMetodologias.getInstancia().buscarListaDeObjetosDeUsuario();
		
		RepositorioIndicadores.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto("qepd@rip.com"));
		RepositorioMetodologias.getInstancia().setUsuario(RepositorioUsuarios.getInstancia().buscarObjeto("qepd@rip.com"));
		
		List<Indicador> indicadoresQepd = RepositorioIndicadores.getInstancia().buscarListaDeObjetosDeUsuario();
		List<Metodologia> metodologiasQepd = RepositorioMetodologias.getInstancia().buscarListaDeObjetosDeUsuario();
		
		assertEquals(indicadoresAxel, Arrays.asList(RepositorioIndicadores.getInstancia().buscarObjeto("ArrorROE"), RepositorioIndicadores.getInstancia().buscarObjeto("VANcomoLasCamionetas")));
		assertEquals(metodologiasAxel, Arrays.asList(RepositorioMetodologias.getInstancia().buscarObjeto("MetodologiaAgil"), RepositorioMetodologias.getInstancia().buscarObjeto("MaomenoMaomeno2")));
		assertEquals(indicadoresQepd, Arrays.asList(RepositorioIndicadores.getInstancia().buscarObjeto("Shasha-Saludos"), RepositorioIndicadores.getInstancia().buscarObjeto("VAI-BYE")));
		assertEquals(metodologiasQepd, Arrays.asList(RepositorioMetodologias.getInstancia().buscarObjeto("GGWP")));
	}

}
