package Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Archivo.CargaBatch.FuentesDeStrings.Archivo;

public class RestauradorDeArchivo implements GestorDeArchivo {
	private String rutaRestauradora;

	public RestauradorDeArchivo(String rutaRestauradora) {
		this.rutaRestauradora = rutaRestauradora;
	}
	
	public void atender(Archivo archivo)  {
		try
		{
			BufferedReader bufferRestauracion = new BufferedReader(new FileReader(this.rutaRestauradora));
			BufferedWriter writerRestauracion = new BufferedWriter(new FileWriter(new File(archivo.getRuta()))); 
			
			String lineaActual;
		    List<String> lineas = new LinkedList<String>();
		    
		    while ((lineaActual = bufferRestauracion.readLine()) != null) lineas.add(lineaActual);
		    
		    for(int i = 0; i < lineas.size(); i++)
		    {
		    	writerRestauracion.write(lineas.get(i));
		    	if(i != lineas.size() - 1) writerRestauracion.newLine();
		    }
		    
		    writerRestauracion.flush();
		    writerRestauracion.close();
		    
		    bufferRestauracion.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
