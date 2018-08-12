package TestCargaBatch;

import static Factories.FactoryCuenta.crearCuentaConValor;
import static Factories.FactoryEmpresa.crearEmpresa;
import static Factories.FactoryPeriodo.crearPeriodo;

import org.junit.Before;

import DB.Proveedores.ProveedorMock;
import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;

public abstract class RepositorioDePruebaCargaBatch {
	public Cuenta cuenta1, cuenta2, cuenta3, cuenta4;
	public Periodo periodo1, periodo2, periodo3;
	public Empresa empresa1, empresa2;
	public RepositorioEmpresas repositorio = RepositorioEmpresas.getInstancia();
	
	public void iniciarCuentas() {
		cuenta1 = crearCuentaConValor("A", 2000);
		cuenta2 = crearCuentaConValor("A", 500);
		cuenta3 = crearCuentaConValor("B", 20);
		cuenta4 = crearCuentaConValor("C", 55);
	}
	
	public void iniciarPeriodos() {
		periodo1 = crearPeriodo(2006, cuenta1, cuenta3);
		periodo2 = crearPeriodo(2007, cuenta2);
		periodo3 = crearPeriodo(2008, cuenta4);
	}
	
	public void iniciarEmpresa() {
		empresa1 = crearEmpresa("XD", periodo1, periodo2);
		empresa2 = crearEmpresa("DX", periodo3);
	}
	
	public void iniciarRepositorio() {
		repositorio.setProveedor(new ProveedorMock<Empresa>());
		repositorio.agregarObjeto(empresa1);
		repositorio.agregarObjeto(empresa2);
	}
	
	@Before
	public void iniciarTodo() {
		iniciarCuentas();
		iniciarPeriodos();
		iniciarEmpresa();
		iniciarRepositorio();
	}
}
