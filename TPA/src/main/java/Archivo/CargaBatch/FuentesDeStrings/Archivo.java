package Archivo.CargaBatch.FuentesDeStrings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo.LimpiadorDeArchivo;

public class Archivo implements FuenteDeStrings {
	private String ruta;
	private FileReader reader;
	private BufferedReader buffer;
	
	public Archivo(String ruta) {
		this.ruta = ruta;
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public List<String> darLineas() {
		abrirse();
		
		List<String> lineas = obtenerLineas();
		
		cerrarse();
		
		return lineas;
	}
	
	@Override
	public boolean noTieneLineas() {
		boolean valorDeVerdad = false;
		
		abrirse();
		
		try
		{
			valorDeVerdad = buffer.read() == -1;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		cerrarse();
		
		return valorDeVerdad;
	}
	
	private List<String> obtenerLineas() {
		String lineaActual = "";
		
		List<String> lineas = new LinkedList<String>();
		
		do
		{
			if(!lineaActual.isEmpty()) lineas.add(lineaActual);
			
			try
			{
				lineaActual = buffer.readLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		while (lineaActual != null);
		
		
		return lineas;
	}

	private void abrirse() {
		try
		{
			reader = new FileReader(ruta);
			buffer = new BufferedReader(reader);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private void cerrarse() {
		try
		{
			buffer.close();
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void limpiarse() {
		new LimpiadorDeArchivo().atender(this);
	}
}
