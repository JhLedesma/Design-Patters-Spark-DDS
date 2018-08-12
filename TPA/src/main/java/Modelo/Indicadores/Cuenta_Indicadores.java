package Modelo.Indicadores;

import java.math.BigDecimal;

public class Cuenta_Indicadores implements Expresion{

	String nombre;
	//No olvidarse de cambiar en la UI para que en el proceso de crear indicadores se use esta clase y no la de la del paquete empresas
	
	public Cuenta_Indicadores(String nombre){
		this.nombre = nombre;
	}
	
	public Cuenta_Indicadores(){};

	//Estan vacios ya que es solo para la creacion de los indicadores, al momento de evaluar se usa la clase cuenta de el paquete empresa que tiene los valores
	@Override
	public BigDecimal calcular(Query query) {
		return new BigDecimal(query.getEmpresa().buscarPeriodo(query.getPeriodo()).buscarCuenta(this.nombre).getValor());
	}

	@Override
	public void addOperando(Expresion operando) {
		
	}

	@Override
	public String imprimirFormula() {
		return nombre;
	}

}
