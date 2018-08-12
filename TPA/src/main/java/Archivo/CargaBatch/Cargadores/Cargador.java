package Archivo.CargaBatch.Cargadores;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.TieneErroresDeScanException;
import Archivo.CargaBatch.ResultadosDeScan.ResultadoDeScan;

public abstract class Cargador {
	public void cargar(ResultadoDeScan resultado) {
		try
		{
			EmpresaToken token = resultado.devolverResultado();
			
			if(this.existeLaCarga(token)) this.realizarModificacion(token);
			
			else this.realizarAlta(token);
		}
		catch (TieneErroresDeScanException excepcion)
		{
			
		}
	}
	
	public abstract boolean existeLaCarga(EmpresaToken token);

	public abstract void realizarAlta(EmpresaToken token);

	public abstract void realizarModificacion(EmpresaToken token);
}
