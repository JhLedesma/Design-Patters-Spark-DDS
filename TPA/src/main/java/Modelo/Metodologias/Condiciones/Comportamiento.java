package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue("comportamiento")
public abstract class Comportamiento extends Condicion {

	protected int anios;

	public Comportamiento(Indicador indicador, int anios) {
		super(indicador);
		this.anios = anios;
	}

	public Comportamiento() {
	}

	@Override
	protected List<Periodo> inicio(List<Periodo> lista) {
		return lista.stream().filter(periodo -> periodo.estaEntre(anios, lista, periodo)).collect(Collectors.toList());
	}

	@Override
	protected  boolean fin(List<Try<BigDecimal>> success) {

		return this.satisface(this.getComportamiento(success));
	}

	protected abstract boolean satisface(List<Boolean> comportamiento);
	
	protected List<Boolean> getComportamiento(List<Try<BigDecimal>> list) {
		List<Boolean> listBoolean = new ArrayList<Boolean>(); 
		for(int i=0; i<list.size(); i++){
			if(i == list.size()-1) //caso en que sea el ultimo elemento, se debe comparar con el anterior
			{
				listBoolean.add(this.comparacion(list.get(i-1), list.get(i)));
			}else{ //caso normales
			listBoolean.add(this.comparacion(list.get(i), list.get(i+1)));
			}
		}
		return listBoolean;
	}
	
	protected abstract boolean comparacion(Try<BigDecimal> try1, Try<BigDecimal> try2);
	
	protected boolean esMenor(Try<BigDecimal> try1, Try<BigDecimal> try2) {
		
		return try1.get().compareTo(try2.get()) == -1;
	}
	
	protected boolean esMayor(Try<BigDecimal> try1, Try<BigDecimal> try2) {
	
		return try1.get().compareTo(try2.get()) == 1;
	}


}
