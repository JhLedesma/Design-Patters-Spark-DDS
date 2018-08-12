package Archivo.CargaBatch.Excepciones;

public class CantidadCamposIncorrectosException extends ScannerException {
	private int cantidadActualCampos;
	
	public CantidadCamposIncorrectosException(int unaCantidadDeCampos) {
		cantidadActualCampos = unaCantidadDeCampos;
	}
	
	@Override
	public String explicacionDelFallo() {
		return "tiene " + camposDeMasOMenos() + "de " + masOMenos() + ".";
	}
	
	private String camposDeMasOMenos() {
		return new Integer(Math.abs(4 - cantidadActualCampos)).toString();
	}
	
	private String masOMenos() {
		return (4 < cantidadActualCampos) ? "menos" : "ms";
	}
}
