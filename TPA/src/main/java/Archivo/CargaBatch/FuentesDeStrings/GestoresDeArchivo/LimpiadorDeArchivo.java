package Archivo.CargaBatch.FuentesDeStrings.GestoresDeArchivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Archivo.CargaBatch.FuentesDeStrings.Archivo;

public class LimpiadorDeArchivo implements GestorDeArchivo {
	public void atender(Archivo archivo) {
		try
		{
			File archivoDeLimpieza = new File(archivo.getRuta());
			
			BufferedWriter bufferDeLimpieza = new BufferedWriter(new FileWriter(archivoDeLimpieza));

			bufferDeLimpieza.write("");

			bufferDeLimpieza.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
