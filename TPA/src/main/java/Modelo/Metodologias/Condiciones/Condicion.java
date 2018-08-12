package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import Modelo.Empresa.Deserializa;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Indicador;
import Modelo.Indicadores.Query;
import Modelo.Metodologias.Try;

@Entity
@DiscriminatorValue("condicion")
public abstract class Condicion extends Condiciones implements Deserializa {

	@OneToOne(cascade = {CascadeType.PERSIST})
	protected Indicador indicador;

	@Transient
	protected Empresa empresa;

	public Condicion() {
	}

	public Condicion(Indicador indicador) {
		this.indicador = indicador;
	}
	
	public boolean cumple(Empresa empresa){
		this.empresa = empresa;
		List<Periodo> listaPeriodos = this.inicio(empresa.getPeriodos());
		List<Try<BigDecimal>> listaSuccess = this.cuerpo(listaPeriodos);
		if (listaSuccess.size() > 0){
			return fin(listaSuccess);
		}else{
			return false;
		}
	}
	
	protected List<Periodo> inicio(List<Periodo> lista){
		return lista;
	}
	
	protected List<Try<BigDecimal>> cuerpo(List<Periodo> lista){
		return lista.stream()
		.map(periodo -> Try.ofFailable(() -> indicador.calcular(new Query(empresa,periodo.getAnio()))))
		.filter(evaluacion -> evaluacion.isSuccess()).collect(Collectors.toList());
	}
	
	protected abstract boolean fin(List<Try<BigDecimal>> success);

	protected double sum(List<Try<BigDecimal>> success){
		return success.stream()
			.map(elem -> elem.get().doubleValue()) //Trasforma a doubles la lista de Try<BigDecimal>
			.reduce((a, b) -> a+b).get(); // Suma la lista, no puedo usar sum()
	}
	
}
