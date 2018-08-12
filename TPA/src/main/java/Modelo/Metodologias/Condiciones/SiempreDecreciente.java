package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue(value="siempre_decreciente")
public class SiempreDecreciente extends Comportamiento {

	public SiempreDecreciente(Indicador indicador, int anios) {
		super(indicador, anios);
	}

	public SiempreDecreciente() {
	}

	@Override
	protected boolean satisface(List<Boolean> comportamiento) {
		
		return comportamiento.stream().allMatch(bool -> bool);
	}

	@Override
	protected boolean comparacion(Try<BigDecimal> try1, Try<BigDecimal> try2) {
		
		return this.esMayor(try1, try2);
	}

	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " es siempre decreciente en " + String.valueOf(anios) + " anios";
	}
	
}
