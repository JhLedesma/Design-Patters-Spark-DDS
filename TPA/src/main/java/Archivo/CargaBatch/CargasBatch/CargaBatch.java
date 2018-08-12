package Archivo.CargaBatch.CargasBatch;

import java.util.TimerTask;

import Archivo.CargaBatch.Cargadores.Cargador;
import Archivo.CargaBatch.Contenedores.Contenedor;
import Archivo.CargaBatch.Excepciones.NoHayNadaException;

public abstract class CargaBatch extends TimerTask {
	private Contenedor contenedor;
	private Cargador cargador;

	public Contenedor getContenedor() {
		return contenedor;
	}

	public void setContenedor(Contenedor contenedor) {
		this.contenedor = contenedor;
	}

	public Cargador getCargador() {
		return cargador;
	}

	public void setCargador(Cargador cargador) {
		this.cargador = cargador;
	}
	
	public abstract void cargar();

	@Override
	public void run() {
		try { this.cargar(); }
		
		catch (NoHayNadaException excepcion) { }
	}
}