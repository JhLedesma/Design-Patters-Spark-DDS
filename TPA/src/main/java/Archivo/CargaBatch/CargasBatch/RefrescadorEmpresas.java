package Archivo.CargaBatch.CargasBatch;

import DB.Repositorios.RepositorioEmpresas;

public class RefrescadorEmpresas extends CargaBatchDecorator {
	public RefrescadorEmpresas(CargaBatch cargaBase) {
		super(cargaBase);
	}
	
	@Override
	public void cargar() {
		super.cargar();
		
		RepositorioEmpresas.getInstancia().refrescar();
	}

}
