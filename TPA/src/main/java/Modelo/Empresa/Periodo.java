package Modelo.Empresa;

import Modelo.Excepciones.Empresas.NoEsElMismoPeriodoException;
import Modelo.Excepciones.Empresas.PeriodoSinCuentasException;
import Modelo.Excepciones.Empresas.YaExisteLaCuentaException;
import Modelo.Excepciones.Indicadores.NoTieneLaCuentaException;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "periodo")
public class Periodo {
	
	@Id
	@GeneratedValue
	@Column(name = "periodo_id")
	private long id;
	
	@Column(name = "periodo_anio")
	Integer anio;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "periodo_fk_id", referencedColumnName = "periodo_id")
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	@SuppressWarnings("unused")
	private Periodo(){};
	
	public Periodo(Integer nuevoAnio, List<Cuenta> nuevasCuentas) {
		this.setAnio(nuevoAnio);
		this.setCuentas(nuevasCuentas);
	}
	
	// Esto solo lo usa el Parser para crear la empresa r�pidamente
	public Periodo(Integer nuevoAnio, Cuenta nuevaCuenta) {
		this.setAnio(nuevoAnio);
		cuentas.add(nuevaCuenta);
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		if(cuentas.size() == 0) throw new PeriodoSinCuentasException();
		this.cuentas = cuentas;
	}
	
	public void agregarCuenta(Cuenta miCuenta) {
		if(this.cuentas.contains(miCuenta)) throw new YaExisteLaCuentaException();
		
		List<Cuenta> nuevasCuentas = new ArrayList<Cuenta>();
		
		nuevasCuentas.addAll(this.getCuentas());
		
		nuevasCuentas.add(miCuenta);
		
		this.cuentas = nuevasCuentas;
	}
	
	public boolean estaEntre(int anios, List<Periodo> lista, Periodo periodo){
		if(lista.size() > anios){
			return lista.subList(lista.size()-1-anios, lista.size()-1).contains(periodo);
		}
		else if(lista.size() == anios){
			return lista.contains(periodo);
		}
		else{
			return false;
		}
	}
	

	public boolean tieneCuenta(Cuenta cuenta) {
		return this.cuentas.stream().anyMatch(c -> c.getNombre().equals(cuenta.getNombre()));
	}
	
	public Cuenta buscarCuenta(String nombreCuenta){
	    return this.cuentas.stream().filter(c -> c.getNombre().equals(nombreCuenta)).findFirst().orElseThrow(()-> new NoTieneLaCuentaException());
	}

	public long getId() {
		return id;
	}

	public void actualizar(Periodo periodo) {
		if(this.anio.intValue() != periodo.getAnio()) throw new NoEsElMismoPeriodoException();
		
		Cuenta cuenta = periodo.getCuentas().get(0);
		
		if (this.tieneCuenta(cuenta))
			this.buscarCuenta(cuenta.getNombre()).actualizar(cuenta.getValor());
		else
			this.agregarCuenta(cuenta);
	}
}
