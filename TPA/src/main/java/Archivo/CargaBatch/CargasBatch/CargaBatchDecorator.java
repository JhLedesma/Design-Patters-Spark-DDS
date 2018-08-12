package Archivo.CargaBatch.CargasBatch;

import Archivo.CargaBatch.Cargadores.Cargador;
import Archivo.CargaBatch.Contenedores.Contenedor;

public class CargaBatchDecorator extends CargaBatch {
	private CargaBatch cargaBase;
	
	public CargaBatchDecorator(CargaBatch cargaBase) {
		this.cargaBase = cargaBase;
	}
	
	public CargaBatch getCargaBase() {
		return cargaBase;
	}

	public void setCargaBase(CargaBatch cargaBase) {
		this.cargaBase = cargaBase;
	}
	
	@Override
	public Contenedor getContenedor() {
		return this.cargaBase.getContenedor();
	}
	
	@Override
	public void setContenedor(Contenedor contenedor) {
		this.cargaBase.setContenedor(contenedor);
	}
	
	@Override
	public Cargador getCargador() {
		return this.cargaBase.getCargador();
	}
	
	@Override
	public void setCargador(Cargador cargador) {
		this.cargaBase.setCargador(cargador);
	}

	@Override
	public void cargar() {
		this.cargaBase.cargar();
	}

}
