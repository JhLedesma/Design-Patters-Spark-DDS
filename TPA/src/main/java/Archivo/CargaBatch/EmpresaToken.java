package Archivo.CargaBatch;

import java.util.Arrays;
import java.util.List;

import Archivo.CargaBatch.Excepciones.FormatoAnioIncorrectoException;
import Archivo.CargaBatch.Excepciones.FormatoValorIncorrectoException;
import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Excepciones.Empresas.CuentaSinNombreException;
import Modelo.Excepciones.Empresas.EmpresaException;
import Modelo.Excepciones.Empresas.EmpresaSinNombreException;

public class EmpresaToken {
	private long idEmpresa = -1;
	private long idPeriodo = -1;
	private long idCuenta = -1;
	private String nombreEmpresa;
	private String nombreCuenta;
	private int anioPeriodo;
	private int valor;
	
	public EmpresaToken(String nombreEmpresa, String nombreCuenta, int anioPeriodo, int valor) {
		this.setNombreEmpresa(nombreEmpresa);
		this.setNombreCuenta(nombreCuenta);
		this.setAnioPeriodo(anioPeriodo);
		this.setValor(valor);
	}

	public EmpresaToken(String nombreEmpresa, String nombreCuenta, String stringPeriodo, String stringValor) {
		this.setNombreEmpresa(nombreEmpresa);
		this.setNombreCuenta(nombreCuenta);
		try
		{
			this.setAnioPeriodo(Integer.parseInt(stringPeriodo));
		}
		catch(NumberFormatException excepcion)
		{
			throw new FormatoAnioIncorrectoException(stringPeriodo);
		}
		try
		{
			this.setValor(Integer.parseInt(stringValor));
		}
		catch(NumberFormatException excepcion)
		{
			throw new FormatoValorIncorrectoException(stringValor);
		}
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public long getIdPeriodo() {
		return idPeriodo;
	}

	public long getIdCuenta() {
		return idCuenta;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public void setNombreEmpresa(String nombreEmpresa) {
		if(nombreEmpresa.replaceAll("\\s+","").isEmpty()) throw new EmpresaSinNombreException();
		
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	
	public void setNombreCuenta(String nombreCuenta) {
		if(nombreCuenta.replaceAll("\\s+","").isEmpty()) throw new CuentaSinNombreException();
		
		this.nombreCuenta = nombreCuenta;
	}
	
	public int getAnioPeriodo() {
		return anioPeriodo;
	}
	
	public void setAnioPeriodo(int anioPeriodo) {
		this.anioPeriodo = anioPeriodo;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public Cuenta getCuenta() {
		Cuenta cuenta = new Cuenta(this.nombreCuenta, this.valor);
		
		return cuenta;
	}
	
	public Periodo getPeriodo() {
		List<Cuenta> cuenta = Arrays.asList(this.getCuenta());
		
		return new Periodo(this.anioPeriodo, cuenta);
	}

	public Empresa generarEmpresa() {
		List<Periodo> periodo = Arrays.asList(this.getPeriodo());
		
		return new Empresa(nombreEmpresa, periodo);
	}
	
	public void obtenerIdentidad(Empresa empresa) {
		try
		{
			this.idEmpresa = empresa.getId();
			this.idPeriodo = empresa.getIdDePeriodo(anioPeriodo);
			this.idCuenta = empresa.getIdDeCuentaEnPeriodo(anioPeriodo, nombreCuenta);
		}
		catch (EmpresaException excepcion)
		{
			
		}
	}
	
	@Override
	public boolean equals(Object otroPosta)
	{
		EmpresaToken otro = (EmpresaToken) otroPosta;
		
		boolean mismaEmpresa = this.nombreEmpresa.equals(otro.getNombreEmpresa());
		boolean mismaCuenta = this.nombreCuenta.equals(otro.getNombreCuenta());
		boolean mismoPeriodo = this.anioPeriodo == otro.getAnioPeriodo();
		boolean mismoValor = this.valor == otro.getValor();
		
		return mismaEmpresa && mismaCuenta && mismoPeriodo && mismoValor;
	}
}
