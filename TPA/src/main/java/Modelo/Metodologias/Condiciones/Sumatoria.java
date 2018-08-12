package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue(value="sumatoria")
public class Sumatoria extends Condicion {

	protected BigDecimal valor;
	private int booleano;
	private String cadena;

	public Sumatoria(Indicador indicador, BigDecimal valor, int booleano, String cadena) {
		super(indicador);
		this.valor = valor;
		this.booleano = booleano;
		this.cadena = cadena;
	}

	public Sumatoria() {
	}

	@Override
	protected boolean fin(List<Try<BigDecimal>> success) {
		double sumatoria = this.sum(success); 
		return new BigDecimal(sumatoria).compareTo(valor) == booleano || new BigDecimal(sumatoria).compareTo(valor) == 0; //Comparacion de BigDecimal
	}
	
	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " tiene una sumatoria "+ cadena +" que " + valor.toString();
	}

}
