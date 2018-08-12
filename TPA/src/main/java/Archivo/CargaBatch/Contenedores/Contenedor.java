package Archivo.CargaBatch.Contenedores;

import java.util.List;

import Archivo.CargaBatch.ResultadosDeScan.ResultadoDeScan;

public interface Contenedor {
	List<ResultadoDeScan> serEscaneado();
	
	void limpiarse();
}
