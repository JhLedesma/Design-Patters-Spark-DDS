package Modelo.Indicadores;

import java.math.BigDecimal;

public interface Expresion {

	public BigDecimal calcular(Query query);
	
	public void addOperando(Expresion operando);

	public String imprimirFormula();
	
}
