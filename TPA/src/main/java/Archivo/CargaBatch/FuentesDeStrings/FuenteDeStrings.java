package Archivo.CargaBatch.FuentesDeStrings;

import java.util.List;

public interface FuenteDeStrings {
	List<String> darLineas();

	boolean noTieneLineas();

	void limpiarse();
}
