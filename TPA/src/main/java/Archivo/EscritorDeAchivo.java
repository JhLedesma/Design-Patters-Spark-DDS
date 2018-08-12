package Archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorDeAchivo {
	
	public void escribir(String ruta, String texto){
		
		try{
		File archivo = new File(ruta);			
		BufferedWriter buffer = new BufferedWriter(new FileWriter(archivo));

		buffer.write(texto);
    
		buffer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
