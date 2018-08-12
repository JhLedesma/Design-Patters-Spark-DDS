package Main;

import static Factories.FactoryCondiciones.crearMayorAEnPeriodos;
import static Factories.FactoryCondiciones.crearMedianaMayorA;
import static Factories.FactoryCondiciones.crearSumatoriaMayorA;
import static Factories.FactoryCuenta.crearCuenta;
import static Factories.FactoryCuenta.crearCuentaConValor;
import static Factories.FactoryEmpresa.crearEmpresa;
import static Factories.FactoryIndicador.crearIndicadorDeUsuario;
import static Factories.FactoryNumero.crearNumero;
import static Factories.FactoryOperaciones.dividir;
import static Factories.FactoryOperaciones.multiplicar;
import static Factories.FactoryOperaciones.restar;
import static Factories.FactoryOperaciones.sumar;
import static Factories.FactoryPeriodo.crearPeriodo;
import static Factories.FactoryMetodologia.crearMetodologiaDeUsuario;

import java.io.IOException;
import java.util.Arrays;

import Archivo.CargaBatch.GestorCargaBatch;
import DB.GestorDeCache;
import DB.Excepciones.NoExistenObjetosException;
import DB.Proveedores.ProveedorBD;
import DB.Proveedores.ProveedorMock;
import DB.Proveedores.ProveedorMongoDB;
import DB.Repositorios.*;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Metodologia;
import Modelo.Usuarios.Usuario;
import Observers.NotificadorModificacionEmpresa;

public class Bootstrap {
	public static void iniciarModelo() throws IOException {
		iniciarOtros();
		
		iniciarRepositorios();
		
		iniciarObjetos();
	}
	
	public static void iniciarOtros() {
		NotificadorModificacionEmpresa.getInstance().agregarObservador(GestorDeCache.getInstance());
	}
	
	public static void iniciarRepositorios() {
		RepositorioUsuarios.getInstancia().setProveedor(new ProveedorBD<Usuario>());
		
		RepositorioEmpresas.getInstancia().setProveedor(new ProveedorBD<Empresa>());
		
		RepositorioIndicadores.getInstancia().setProveedor(new ProveedorBD<Indicador>());

		RepositorioMetodologias.getInstancia().setProveedor(new ProveedorBD<Metodologia>());
		
		RepositorioPrecalculados repo = RepositorioPrecalculados.getInstancia();
		RepositorioPrecalculados.getInstancia().setProveedor(new ProveedorMongoDB<>(repo));
	}
	
	public static void iniciarRepositoriosDePrueba() {
		RepositorioUsuarios.getInstancia().setProveedor(new ProveedorMock<Usuario>());
		
		RepositorioEmpresas.getInstancia().setProveedor(new ProveedorMock<Empresa>());
		
		RepositorioIndicadores.getInstancia().setProveedor(new ProveedorMock<Indicador>());

		RepositorioMetodologias.getInstancia().setProveedor(new ProveedorMock<Metodologia>());
	}
	
	public static void iniciarObjetos() throws IOException {
		chequearUsuarios();
		chequearEmpresas();
		chequearIndicadores();
		chequearMetodologias();
	}
	
	public static void chequearUsuarios() {
		try
		{
			RepositorioUsuarios.getInstancia().buscarListaDeObjetos();
		}
		catch (NoExistenObjetosException excepcion) {
			iniciarUsuarios();
		}
	}
	
	public static void chequearEmpresas() throws IOException {
		try
		{
			GestorCargaBatch.iniciar();
			
			RepositorioEmpresas.getInstancia().buscarListaDeObjetos();
		}
		catch (NoExistenObjetosException excepcion) {
			iniciarEmpresas();
		}
	}
	
	public static void chequearIndicadores() {
		try
		{
			RepositorioIndicadores.getInstancia().buscarListaDeObjetos();
		}
		catch (NoExistenObjetosException excepcion) {
			iniciarIndicadores();
		}
	}
	
	public static void chequearMetodologias() {
		try
		{
			RepositorioMetodologias.getInstancia().buscarListaDeObjetos();
		}
		catch (NoExistenObjetosException excepcion) {
			iniciarMetodologias();
		}
	}
	
	public static void iniciarUsuarios() {
		RepositorioUsuarios.getInstancia().agregarListaDeObjetos(
			Arrays.asList(
					new Usuario("axel@bags.com", "a7c15c415c37626de8fa648127ba1ae5"),
					new Usuario("qepd@rip.com", "6b5b687895b5883436a775cb27fd196a")));
	}

	public static void iniciarEmpresas() {
		RepositorioEmpresas.getInstancia().agregarListaDeObjetos(
			Arrays.asList(
				crearEmpresa("Feel-Fort",
						crearPeriodo(2006,
								crearCuentaConValor("EDITBA", 2),
								crearCuentaConValor("FCF", 3)),
						crearPeriodo(2007,
								crearCuentaConValor("EDITBA", 5),
								crearCuentaConValor("FCF", 10))),
				crearEmpresa("Axxxel's Another Consortium Bag",
						crearPeriodo(2006,
								crearCuentaConValor("EDITBA", 3),
								crearCuentaConValor("FCF", 2)),
						crearPeriodo(2007,
								crearCuentaConValor("EDITBA", 1),
								crearCuentaConValor("FCF", 2)))));
	}
	
	public static void iniciarIndicadores() {
		RepositorioIndicadores.getInstancia().agregarListaDeObjetos(
			Arrays.asList(
				crearIndicadorDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"),
						"ArrorROE", 
						multiplicar(crearCuenta("EDITBA"), crearNumero(2))),
				crearIndicadorDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("qepd@rip.com"),
						"Shasha-Saludos",
						sumar(crearCuenta("FreeCashFlow"), dividir(crearCuenta("EDITBA"), crearNumero(10)))),
				crearIndicadorDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"),
						"VANcomoLasCamionetas",
						restar(crearCuenta("FCF"), crearCuenta("EDITBA")))));
		RepositorioIndicadores.getInstancia().agregarObjeto(
				crearIndicadorDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("qepd@rip.com"),
						"VAI-BYE",
						dividir(RepositorioIndicadores.getInstancia().buscarObjeto("ArrorROE"), crearNumero(5))));
				
	}
	
	public static void iniciarMetodologias() {
		RepositorioMetodologias.getInstancia().agregarListaDeObjetos(
			Arrays.asList(
				crearMetodologiaDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"),
						"MetodologiaAgil",
						crearMayorAEnPeriodos(RepositorioIndicadores.getInstancia().buscarObjeto("ArrorROE"), 10, 1)),
				crearMetodologiaDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com"),
						"MaomenoMaomeno2",
						crearMedianaMayorA(RepositorioIndicadores.getInstancia().buscarObjeto("VANcomoLasCamionetas"), 5000)),
				crearMetodologiaDeUsuario(
						RepositorioUsuarios.getInstancia().buscarObjeto("qepd@rip.com"),
						"GGWP",
						crearSumatoriaMayorA(RepositorioIndicadores.getInstancia().buscarObjeto("VAI-BYE"), 0))));
	}
}
