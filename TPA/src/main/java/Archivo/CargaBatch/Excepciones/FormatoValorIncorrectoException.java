package Archivo.CargaBatch.Excepciones;

public class FormatoValorIncorrectoException extends ScannerException {
	String valorErroneo;

	public FormatoValorIncorrectoException(String valorErroneo) {
		this.valorErroneo = valorErroneo;
	}

	@Override
	public String explicacionDelFallo() {
		return "El campo " + valorErroneo + " no sigue un formato numrico para un valor de cuenta.";
	}
}
