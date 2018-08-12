package Archivo.CargaBatch.Scanners;

import Archivo.CargaBatch.EmpresaToken;
import Archivo.CargaBatch.Excepciones.CantidadCamposIncorrectosException;
import Archivo.CargaBatch.Excepciones.RenglonVacioException;

public class CSV extends StringScanner {
	private String delimitador;
	
	public CSV(String delimitador) {
		this.delimitador = delimitador;
	}
	
	public String getDelimitador() {
		return delimitador;
	}

	public void setDelimitador(String delimitador) {
		this.delimitador = delimitador;
	}

	@Override
	public EmpresaToken intentarEscanear(String renglon) {
		if(renglon.isEmpty()) throw new RenglonVacioException();
		
		String[] vector = renglon.split(this.delimitador);
		
		if(vector.length != 4) throw new CantidadCamposIncorrectosException(vector.length);
		
		return new EmpresaToken(vector[0], vector[1], vector[2], vector[3]);
	}
	
}
