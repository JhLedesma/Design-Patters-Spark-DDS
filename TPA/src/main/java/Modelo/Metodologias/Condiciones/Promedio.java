package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue(value="promedio")
public class Promedio extends Condicion {

		private BigDecimal valor;
		private int booleano;
		private String cadena;

	public Promedio() {
	}

	public Promedio(Indicador indicador, BigDecimal valor, int booleano, String cadena) {
			super(indicador);
			this.valor = valor;
			this.booleano = booleano;
			this.cadena = cadena;
	}
		
		public boolean fin(List<Try<BigDecimal>> success){
			 Double promedio = this.sum(success) // Suma la lista, no puedo usar sum()
					 			/ empresa.getPeriodos().size(); // Obtiene el promedio
			 return new BigDecimal(promedio).compareTo(valor) == booleano || new BigDecimal(promedio).compareTo(valor) == 0; //Comparacion de BigDecimal
		}
		
		@Override
		public String mostrarCadena() {
			return "El indicador " + indicador.getNombre() + " tiene un promedio " + cadena + " a " + valor.toString();
		}

	
}
