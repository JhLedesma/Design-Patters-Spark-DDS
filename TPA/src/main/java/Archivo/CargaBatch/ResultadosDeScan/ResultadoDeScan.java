package Archivo.CargaBatch.ResultadosDeScan;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.TieneErroresDeScanException;

public interface ResultadoDeScan {
	EmpresaToken devolverResultado() throws TieneErroresDeScanException;
}
