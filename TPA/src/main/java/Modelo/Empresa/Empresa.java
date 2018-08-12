package Modelo.Empresa;

import Modelo.Excepciones.Empresas.EmpresaSinIdentidadException;
import Modelo.Excepciones.Empresas.EmpresaSinNombreException;
import Modelo.Excepciones.Empresas.EmpresaSinPeriodoException;
import Modelo.Excepciones.Empresas.NoEsLaMismaEmpresaException;
import Modelo.Excepciones.Empresas.NoExisteElPeriodoException;
import Modelo.Excepciones.Empresas.YaExisteElPeriodoException;

import org.uqbar.commons.utils.Observable;

import Archivo.CargaBatch.EmpresaToken;
import DB.TiposDeRepositorios.TipoDeRepositorio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Observable
@Table(name = "empresa")
public class Empresa implements TipoDeRepositorio {
	
	@Id
	@GeneratedValue
	@Column(name = "empresa_id")
	private long id;
	
	@Column(name = "empresa_nombre", columnDefinition = "VARCHAR(255) BINARY")
	private String nombre;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "empresa_fk_id", referencedColumnName = "empresa_id")
	private List<Periodo> periodos = new ArrayList<Periodo>();

	public long getId() {
		if(id == 0) throw new EmpresaSinIdentidadException();
		
		return id;
	}

	@SuppressWarnings("unused")
	private Empresa(){};
	
	// Esto solo lo usa el Parser para crear la empresa r�pidamente
	public Empresa(String nombre, String nombreCuenta, String anio, String valor) {
		 this.setNombre(nombre);	
		 periodos.add(new Periodo(Integer.parseInt(anio), new Cuenta(nombreCuenta, Integer.parseInt(valor))));
		 }
	
	public Empresa(String nuevoNombre, List<Periodo> nuevosPeriodos) {
		this.setNombre(nuevoNombre);
		this.setPeriodos(nuevosPeriodos);
	}

	public Empresa(String nuevoNombre, Periodo nuevoPeriodo) { // Agregar un periodo
		this.setNombre(nuevoNombre);
		periodos.add(nuevoPeriodo);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == "") throw new EmpresaSinNombreException();
		this.nombre = nombre;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		if(periodos.size() == 0) throw new EmpresaSinPeriodoException();
		this.periodos = periodos;
	}
	
	public List<Integer> obtenerAniosDeTodosLosPeriodos(){
		List<Integer> periodosEmpresa = this.getPeriodos().stream().map(p -> p.getAnio()).collect(Collectors.toList());
		return periodosEmpresa;
	}
	
	public boolean tienePeriodo(Periodo periodo){
		return this.periodos.stream().anyMatch(p -> p.getAnio().equals(periodo.getAnio()));
	}
	
	public Periodo buscarPeriodo(Integer periodo){
	    return this.periodos.stream().filter(p -> p.getAnio().equals(periodo)).findFirst().orElseThrow(()-> new NoExisteElPeriodoException());
    }

	public void agregarPeriodo(Periodo periodo) {
		if(this.getPeriodos().contains(periodo)) throw new YaExisteElPeriodoException();
		
		List<Periodo> nuevosPeriodos = new ArrayList<Periodo>();
		
		nuevosPeriodos.addAll(this.getPeriodos());
		
		nuevosPeriodos.add(periodo);
		
		this.periodos = nuevosPeriodos;
	}

	public void actualizar(EmpresaToken empresaToken) {
		if(!this.getNombre().equals(empresaToken.getNombreEmpresa())) throw new NoEsLaMismaEmpresaException();
		
		if (this.tienePeriodo(empresaToken.getPeriodo()))
			this.buscarPeriodo(empresaToken.getAnioPeriodo()).actualizar(empresaToken.getPeriodo());
		else
			this.agregarPeriodo(empresaToken.getPeriodo());
	}

	public long getIdDePeriodo(int anioPeriodo) {
		return this.buscarPeriodo(anioPeriodo).getId();
	}

	public long getIdDeCuentaEnPeriodo(int anioPeriodo, String nombreCuenta) {
		return this.buscarPeriodo(anioPeriodo).buscarCuenta(nombreCuenta).getId();
	}

}

