package Archivo.Empresa;

import java.util.List;
import Archivo.FileCleaner;
import Archivo.LectorDeArchivos;
import Archivo.Empresa.Excepciones.CSVSinEmpresasException;
import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Empresa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Instanciador_Bolsa_Empresas {
	
	public void instanciar() throws IOException {
		try
		{
			RepositorioEmpresas.getInstancia().agregarListaDeObjetos(this.obtenerEmpresas());
	    	this.vaciarCsv();
		}
		catch(CSVSinEmpresasException e){}
	}

	private List<Empresa> obtenerEmpresas() throws IOException {
	
		// Instancio el Lector de Archivos
		LectorDeArchivos miLector = new LectorDeArchivos();
		FileInputStream stream = miLector.getFile("cuentitasDeHector.csv");
		
		// Instancio el Parser
		CSVParser miParser = new CSVParser(",");
		List<Empresa> misEmpresas = new ArrayList<Empresa>();
		misEmpresas = miParser.parse(stream);
		
		if(misEmpresas.isEmpty()) throw new CSVSinEmpresasException();
		
		return misEmpresas;
	}
	
	private void vaciarCsv() {
		new FileCleaner().limpiar("cuentitasDeHector.csv");
	}
	
}
