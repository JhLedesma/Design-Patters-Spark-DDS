package Modelo.Metodologias;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DB.Repositorios.RepositorioMetodologias;
import Modelo.Excepciones.Metodologias.MetodologiaSinNombreException;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Condiciones.Condicion;
import Modelo.Metodologias.Condiciones.Condiciones;

public class MetodologiaBuilder {

	private String nombreMetodologia;
	private Indicador indicadorSeleccionado;
	public List<Condiciones> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condiciones> condiciones) {
		this.condiciones = condiciones;
	}

	
	private List<Condiciones> condiciones = new ArrayList<Condiciones>();
	
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public String getNombreMetodologia() {
		return nombreMetodologia;
	}

	public void setNombreMetodologia(String nombreMetodologia) {
		if(nombreMetodologia.isEmpty()) throw new MetodologiaSinNombreException();
		this.nombreMetodologia = nombreMetodologia;
	}
	
	public void agregarCondicion(Condicion c){
		condiciones.add(c);
	}

	public String mostrarCadena() {
		return String.join(" && ", condiciones.stream().map(c -> c.mostrarCadena()).collect(Collectors.toList()));
	}
	
	public Metodologia crearMetodologia(){ 
		Metodologia miNuevaMetodologia = new Metodologia(nombreMetodologia,condiciones);
		RepositorioMetodologias.getInstancia().agregarObjeto(miNuevaMetodologia);
		return miNuevaMetodologia;
	}
	
}
