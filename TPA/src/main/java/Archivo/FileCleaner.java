package Archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCleaner {
	
public void limpiar(String ruta){
		
		try{
		File archivo = new File(ruta);			
		BufferedWriter buffer = new BufferedWriter(new FileWriter(archivo));

		buffer.write("");
    
		buffer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}
