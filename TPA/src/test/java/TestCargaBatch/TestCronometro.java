package TestCargaBatch;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Archivo.CargaBatch.Cronometro;
import Archivo.CargaBatch.Cargadores.Cargador;
import Archivo.CargaBatch.Cargadores.CargadorDeRepositorio;
import Archivo.CargaBatch.CargasBatch.CargaBatch;
import Archivo.CargaBatch.CargasBatch.CargaBatchBase;
import Archivo.CargaBatch.CargasBatch.Kelly;
import Archivo.CargaBatch.Contenedores.Contenedor;
import Archivo.CargaBatch.Contenedores.ContenedorDeStrings;
import Archivo.CargaBatch.FuentesDeStrings.MockArchivo;
import Archivo.CargaBatch.Scanners.CSV;

public class TestCronometro extends RepositorioDePruebaCargaBatch {
	CargaBatch cargaBatch;
	Cronometro cron;
	
	@Before
	public void iniciarFuentes() throws FileNotFoundException {
		Contenedor contenedor = new ContenedorDeStrings(new MockArchivo("XD,A,2006,105020", "Khe,Khe,200,20"), new CSV(","));

		Cargador cargador = new CargadorDeRepositorio();

		cargaBatch = new Kelly(new CargaBatchBase(contenedor, cargador));
		
		cron = new Cronometro();
		
		cron.ejecutaPeriodicamente(cargaBatch, 50);
	}
	
	@After
	public void terminarTareas() {
		cron.terminarTareas();
	}
	
	@Test
	public void puedoAgregarUnaEmpresaTrasUnLapsoDeTiempo() throws InterruptedException {
		Thread.sleep(100);

		assertEquals(3, repositorio.buscarListaDeObjetos().size());
	}
	
	@Test
	public void puedoReescribirElContenedorYQueTomeLoNuevo() throws InterruptedException {
		Thread.sleep(200);
		
		cargaBatch.setContenedor(new ContenedorDeStrings(new MockArchivo("XD,Yeah,2010,5700", "Ah,SiSi,500,8000", "QEPD,RIP,500,600"), new CSV(",")));
		
		Thread.sleep(200);
		
		assertEquals(5, repositorio.buscarListaDeObjetos().size());
	}
}
