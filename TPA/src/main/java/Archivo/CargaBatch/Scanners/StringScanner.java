package Archivo.CargaBatch.Scanners;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.ScannerException;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoDeScan;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoNegativo;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoPositivo;

public abstract class StringScanner {
	public ResultadoDeScan escanear(String renglon) {
		try
		{
			EmpresaToken token = this.intentarEscanear(renglon);
			
			return new ResultadoPositivo(token);
		}
		catch (ScannerException excepcion)
		{
			return new ResultadoNegativo(renglon, excepcion);
		}
	}

	public abstract EmpresaToken intentarEscanear(String renglon);
}
