package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue(value="mediana")
public class Mediana extends Condicion {

	protected BigDecimal valor;
	private int booleano;
	private String cadena;

	public Mediana(Indicador indicador, BigDecimal valor, int booleano, String cadena) {
		super(indicador);
		this.valor = valor;
		this.booleano = booleano;
		this.cadena = cadena;
	}

	public Mediana() {
	}

	@Override
	protected boolean fin(List<Try<BigDecimal>> success) {
		Double mediana = this.sum(success) / 2;
		return new BigDecimal(mediana).compareTo(valor) == booleano || new BigDecimal(mediana).compareTo(valor) == 0; //Comparacion de BigDecimal
	}
	
	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " tiene una mediana "+ cadena +" a " + valor.toString();
	}

}
