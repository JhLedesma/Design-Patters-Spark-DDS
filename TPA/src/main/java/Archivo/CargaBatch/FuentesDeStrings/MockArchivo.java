package Archivo.CargaBatch.FuentesDeStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockArchivo implements FuenteDeStrings {
	private List<String> lineas;
	
	public MockArchivo(String... texto) {
		lineas = Arrays.asList(texto);
	}

	@Override
	public List<String> darLineas() {
		return lineas;
	}

	@Override
	public boolean noTieneLineas() {
		return lineas.isEmpty();
	}

	@Override
	public void limpiarse() {
		lineas = new ArrayList<String>();
	}
}
