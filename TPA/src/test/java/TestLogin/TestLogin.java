package TestLogin;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import DB.Proveedores.ProveedorMock;
import DB.Repositorios.RepositorioUsuarios;
import Modelo.Usuarios.GestorDeUsuarios;
import Modelo.Usuarios.Usuario;
import Modelo.Usuarios.Excepciones.PasswordIncorrectaException;
import spark.Request;

public class TestLogin {

	private GestorDeUsuarios gestorDeUsuarios;
	private RepositorioUsuarios repositorio;
	
	private Usuario axel;
	private Usuario nachito;
	
	private static String EMAIL = "email";
	private static String ID_USER = "idUser";
	
	@Before
	public void init() {
		gestorDeUsuarios = GestorDeUsuarios.getInstance();
		repositorio = RepositorioUsuarios.getInstancia();
		
		axel = new Usuario("axel@bags.com", "6b5b687895b5883436a775cb27fd196a");
		nachito = new Usuario("nachi@rip.to", "pen0r");
		repositorio.setProveedor(new ProveedorMock<Usuario>());
		repositorio.agregarObjeto(axel);
		repositorio.agregarObjeto(nachito);
	}
	
	@Test(expected = PasswordIncorrectaException.class)
	public void axelIngresoPasswordIncorrecta() {
		
		gestorDeUsuarios.obtenerId("axel@bags.com", "qepd");
		
	}
	
	@Test
	public void existenLosUsuarios() {
		
		int idAxel = gestorDeUsuarios.obtenerId("axel@bags.com", "6b5b687895b5883436a775cb27fd196a");
		assertEquals(0, idAxel);
		
		int idNachito = gestorDeUsuarios.obtenerId("nachi@rip.to", "pen0r");
		assertEquals(1, idNachito);
		
	}
	
	@Test
	public void noEstaLogeado() {
		
		Request request = Mockito.mock(Request.class);
		Mockito.when(request.cookie(ID_USER)).thenReturn(null);
		Mockito.when(request.cookie(EMAIL)).thenReturn(null);
		
		Map<Object, Object> actualMap = gestorDeUsuarios.obtenerMapa(request);
		
		assertEquals(new HashMap<>(), actualMap);
		
		Mockito.when(request.cookie(ID_USER)).thenReturn("0");
		Mockito.when(request.cookie(EMAIL)).thenReturn("nachi@rip.to");
		
		Map<Object, Object> actualMapOnlyEmail = gestorDeUsuarios.obtenerMapa(request);
		
		Map<Object, Object> expectedMap = new HashMap<>();
		expectedMap.put(EMAIL, null);
		
		assertEquals(expectedMap, actualMapOnlyEmail);
		
	}
	
	@Test
	public void estaLogeado() {
		
		Request request = Mockito.mock(Request.class);
		
		int idNachito = gestorDeUsuarios.obtenerId("nachi@rip.to", "pen0r");
		
		Mockito.when(request.cookie(ID_USER)).thenReturn(idNachito + "");
		Mockito.when(request.cookie(EMAIL)).thenReturn("nachi@rip.to");
		
		Map<Object, Object> actualMap = gestorDeUsuarios.obtenerMapa(request);
		
		Map<Object, Object> expectedMap = new HashMap<>();
		expectedMap.put(EMAIL, "nachi@rip.to");
		
		assertEquals(expectedMap, actualMap);
	}
	
}
