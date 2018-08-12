package Modelo.Excepciones.Empresas;

import Archivo.CargaBatch.Excepciones.ScannerException;

public class CuentaSinNombreException extends ScannerException {
	@Override
	public String explicacionDelFallo() {
		return "Le falta nombre a la cuenta";
	}
}
