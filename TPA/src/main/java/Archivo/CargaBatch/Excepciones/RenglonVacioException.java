package Archivo.CargaBatch.Excepciones;

public class RenglonVacioException extends ScannerException {
	@Override
	public String explicacionDelFallo() {
		return "No hay lnea";
	}
}
