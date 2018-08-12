package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue(value="booleana")
public class Booleana extends Condicion {

	protected BigDecimal valor;
	protected int anios;
	private int booleano;
	private String cadena;
	
	public Booleana(Indicador indicador, BigDecimal valor, int anios, int booleano, String cadena) {
		super(indicador);
		this.valor = valor;
		this.anios = anios;
		this.booleano = booleano;
		this.cadena = cadena;
	}

	public Booleana() {
	}

	@Override
	public List<Periodo> inicio(List<Periodo> lista) {
		return lista.stream().filter(periodo -> periodo.estaEntre(anios, lista, periodo)).collect(Collectors.toList());
	}
	
	@Override
	public boolean fin(List<Try<BigDecimal>> success) {
		return success.stream().allMatch(successMonad -> successMonad.get().compareTo(valor) == booleano || successMonad.get().compareTo(valor) == 0); // == 1 representa el "> valor", == 0 representa el "= valor"
	}
	
	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " es " + cadena + " a " + valor.toString() + " en " + String.valueOf(anios) + " anios";
	}
	
	

}
