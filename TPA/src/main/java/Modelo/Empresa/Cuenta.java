package Modelo.Empresa;

import Modelo.Excepciones.Empresas.CuentaConValorNegativoException;
import Modelo.Excepciones.Empresas.CuentaSinNombreException;

import org.uqbar.commons.utils.Observable;

import javax.persistence.*;

@Entity
@Observable
@Table(name = "cuenta")

public class Cuenta {
	
	@Id
	@GeneratedValue
	@Column(name = "cuenta_id")
	private Long Id;
	
	@Column(name = "cuenta_nombre")
	String nombre;
	@Column(name = "cuenta_valor")
	Integer valor;
	
	@SuppressWarnings("unused")
	private Cuenta(){};

	public Cuenta(String nombre, Integer nuevoValor) {
		this.setNombre(nombre);
		this.setValor(nuevoValor);
	}

	public Cuenta(String nuevoNombre) {
		this.setNombre(nuevoNombre);
	}
	
	public long getId() {
		return Id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nuevoNombre) {
		if(nuevoNombre.equals("")) throw new CuentaSinNombreException();
		this.nombre = nuevoNombre;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		if(valor < 0) throw new CuentaConValorNegativoException();
		this.valor = valor;
	}

	public void actualizar(Integer valorNuevo) {
		this.setValor(valorNuevo);
	}


}
