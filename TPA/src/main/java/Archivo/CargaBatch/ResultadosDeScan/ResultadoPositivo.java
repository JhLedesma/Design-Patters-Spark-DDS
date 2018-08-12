package Archivo.CargaBatch.ResultadosDeScan;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.TieneErroresDeScanException;

public class ResultadoPositivo implements ResultadoDeScan {
	private EmpresaToken token;
	
	public ResultadoPositivo(EmpresaToken token) {
		this.token = token;
	}

	@Override
	public EmpresaToken devolverResultado() throws TieneErroresDeScanException {
		return this.token;
	}

}
