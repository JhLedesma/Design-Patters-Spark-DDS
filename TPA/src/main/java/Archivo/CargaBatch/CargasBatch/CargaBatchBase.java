package Archivo.CargaBatch.CargasBatch;

import java.util.List;

import Archivo.CargaBatch.Cargadores.Cargador;
import Archivo.CargaBatch.Contenedores.Contenedor;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoDeScan;

public class CargaBatchBase extends CargaBatch {
	public CargaBatchBase(Contenedor contenedor, Cargador cargador) {
		this.setContenedor(contenedor);
		this.setCargador(cargador);
	}

	public void cargar() {
		List<ResultadoDeScan> resultados = this.getContenedor().serEscaneado();
		
		resultados.forEach(resultado -> this.getCargador().cargar(resultado));
	}
}
