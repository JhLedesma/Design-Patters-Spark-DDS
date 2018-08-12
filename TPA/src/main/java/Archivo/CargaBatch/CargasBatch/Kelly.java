package Archivo.CargaBatch.CargasBatch;

public class Kelly extends CargaBatchDecorator {
	public Kelly(CargaBatch cargaBase) {
		super(cargaBase);
	}
	
	@Override
	public void cargar() {
		super.cargar();
		
		this.getContenedor().limpiarse();
	}
}
