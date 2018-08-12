package Modelo.Indicadores;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import DB.Converter.IndicadorConverter;
import DB.TiposDeRepositorios.ElementoPrivado;
import Modelo.Excepciones.Indicadores.IndicadorSinFormulaException;
import Modelo.Excepciones.Indicadores.IndicadorSinNombreException;
import Modelo.Usuarios.Usuario;

@Entity
@Table(name = "indicador")
public class Indicador implements Expresion, ElementoPrivado {
	@Id
	@GeneratedValue
	@Column(name = "indicador_id")
	private long id;
	
	@Column(name = "indicador_nombre")
	private String nombre;

	@Column(name = "indicador_formula", columnDefinition = "LONGTEXT")
	@Convert(converter = IndicadorConverter.class)
	private Expresion formula;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_fk_id")
	private Usuario usuario;
	
	public Indicador(Usuario usuario, String nombre, Expresion formula) {
		this.setUsuario(usuario);
		this.setNombre(nombre);
		this.setFormula(formula);
	}

	public Indicador(String nombre, Expresion formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
	}
	
	public Indicador(){}

	public BigDecimal calcular(Query query) {
		return formula.calcular(query);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == "") throw new IndicadorSinNombreException();
		this.nombre = nombre;
	}

	public Expresion getFormula() {
		return formula;
	}

	public void setFormula(Expresion formula) {
		if(formula == null) throw new IndicadorSinFormulaException();
		this.formula = formula;
	}
	
	public void addOperando(Expresion operando){
	}
	
	public String imprimirFormula() {
		return "( " + nombre + " = " + formula.imprimirFormula() + " )";
	}

	
	
}
