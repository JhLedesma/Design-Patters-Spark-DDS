package Modelo.Excepciones.Empresas;

import Archivo.CargaBatch.Excepciones.ScannerException;

public class EmpresaSinNombreException extends ScannerException {
	@Override
	public String explicacionDelFallo() {
		return "Le falta nombre a la empresa.";
	}
}
