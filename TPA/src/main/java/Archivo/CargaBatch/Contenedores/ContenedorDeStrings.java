package Archivo.CargaBatch.Contenedores;

import java.util.List;
import java.util.stream.Collectors;

import Archivo.CargaBatch.Excepciones.NoHayNadaException;
import Archivo.CargaBatch.FuentesDeStrings.FuenteDeStrings;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoDeScan;
import Archivo.CargaBatch.Scanners.StringScanner;

public class ContenedorDeStrings implements Contenedor {
	private FuenteDeStrings fuente;
	private StringScanner scanner;
	
	public ContenedorDeStrings(FuenteDeStrings fuente, StringScanner scanner)
	{
		this.fuente = fuente;
		this.scanner = scanner;
	}

	@Override
	public List<ResultadoDeScan> serEscaneado() {
		if(fuente.noTieneLineas()) throw new NoHayNadaException();
			
		List<String> lineas = fuente.darLineas();
		
		List<ResultadoDeScan> resultados = lineas.stream().map(linea -> scanner.escanear(linea)).collect(Collectors.toList());
		
		return resultados;
	}

	@Override
	public void limpiarse() {
		fuente.limpiarse();
	}

}
