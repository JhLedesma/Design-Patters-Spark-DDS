package Archivo.Empresa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;

public class CSVParser {
	
	private String separador;
	
	public CSVParser(String miSeparador) {
		separador = miSeparador;
	}
		
	public List<Empresa> parse(FileInputStream stream) throws IOException{
		List<Empresa> empresas = new ArrayList<Empresa>();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
		empresas = this.aEmpresas(this.devolverLineas(buffer));

		
		return this.combinarEmpresas(empresas);
		
	}
	
	//Tuve que hacer esto lamentablemente porque buffer.lines es de tipo Stream<String> y al momento de mapearlo no lo tranformaba a una lista de empresas, ni siquiera casteando
	private List<List<String>> devolverLineas(BufferedReader lineas) throws IOException{
		List<List<String>> listFinal = new ArrayList<List<String>>();
		String linea = lineas.readLine();
		while(linea != null){
			String[] array = linea.split(separador);
			List<String> lista = new ArrayList<String>();
			for (int i= 0; i < array.length; i++ ){
            	lista.add(array[i]);
            }
			listFinal.add(lista);
			linea = lineas.readLine();
		}
		return listFinal;
	}
	
	private List<Empresa> aEmpresas(List<List<String>> lineas){
		List<Empresa> list = lineas.stream().map(line -> new Empresa(line.get(0), line.get(1), line.get(2), line.get(3))).collect(Collectors.toList());
		return list;
	}

	/*private List<Empresa> aEmpresas(Stream<String> lineas){
		
		return (List<Empresa>) lineas.map(line -> Arrays.asList(line.split(this.separador)))   //<aca se ve clamente como no funciona el mapeo
				.map(line -> new Empresa(line.get(0), line.get(1), line.get(2), line.get(3)) 
						.collect(Collectors.toList()));
		
	}*/
	
	
	private List<Empresa> combinarEmpresas(List<Empresa> empresasAParsear) {
		List<Empresa> misEmpresas = new ArrayList<Empresa>();
		
		List<String> nombresDeEmpresa = new ArrayList<String>();
		
		nombresDeEmpresa = this.obtenerNombresDeEmpresa(empresasAParsear);
		
		misEmpresas = this.formarEmpresasDefinitivas(nombresDeEmpresa, empresasAParsear);
		
		return misEmpresas;
	}
	
	private List<String> obtenerNombresDeEmpresa(List<Empresa> empresasAParsear) {
		List<String> nombresDeEmpresa = new ArrayList<String>();
		
		for(int i = 0; i < empresasAParsear.size(); i++) {
			String nombreDeLaEmpresa = empresasAParsear.get(i).getNombre();
			
			if(!nombresDeEmpresa.contains(nombreDeLaEmpresa)) {
				nombresDeEmpresa.add(nombreDeLaEmpresa);
			}
		}
		
		return nombresDeEmpresa;
	}
	
	private List<Empresa> formarEmpresasDefinitivas(List<String> nombresDeEmpresa, List<Empresa> empresasAParsear) {
		List<Empresa> misEmpresas = new ArrayList<Empresa>();
		
		for(int i = 0; i < nombresDeEmpresa.size(); i++) {
			String nombreDeEmpresa = nombresDeEmpresa.get(i);
			List<Empresa> empresasConMismoNombre = new ArrayList<Empresa>();
			
			empresasConMismoNombre = empresasAParsear.stream().filter(e -> e.getNombre().equals(nombreDeEmpresa)).collect(Collectors.toList());
			
			Empresa empresaDefinitiva = this.formarEmpresaDefinitiva(empresasConMismoNombre);
			
			misEmpresas.add(empresaDefinitiva);
		}
		
		return misEmpresas;
	}
	
	private Empresa formarEmpresaDefinitiva(List<Empresa> empresasConMismoNombre) {
		Empresa empresaDefinitiva = new Empresa(empresasConMismoNombre.get(0).getNombre(), this.formarPeriodos(empresasConMismoNombre));
		
		return empresaDefinitiva;
	}
	
	private List<Periodo> formarPeriodos(List<Empresa> empresasConMismoNombre) {
		List<Periodo> misPeriodos = new ArrayList<Periodo>();

		for(int i = 0; i < empresasConMismoNombre.size(); i++) {
			
			Periodo periodoActual = empresasConMismoNombre.get(i).getPeriodos().get(0); // .get(0) porque en este momento todas las empresas tienen un solo periodo
			
			Cuenta cuentaActual = periodoActual.getCuentas().get(0); // .get(0) porque en este momento todas las empresas tienen una sola cuenta
			
			boolean contieneElAnio = misPeriodos.stream().anyMatch(p -> p.getAnio().intValue() == periodoActual.getAnio().intValue());
						
			if(contieneElAnio) {
				misPeriodos.get(this.posicionDeCuentaRespectoAlAnio(misPeriodos, periodoActual.getAnio())).agregarCuenta(cuentaActual);
			} else {
				misPeriodos.add(new Periodo(periodoActual.getAnio(), periodoActual.getCuentas()));
			}
			
		}
		
		return misPeriodos;
	}
	
	private Integer posicionDeCuentaRespectoAlAnio(List<Periodo> misPeriodos, Integer anio) {
		Integer posicion;
		
		List<Integer> periodosMapeados = new ArrayList<>();
		
		periodosMapeados = misPeriodos.stream().map(p -> p.getAnio()).collect(Collectors.toList());
		
		posicion = periodosMapeados.indexOf(anio);
		
		return posicion;
	}
	
}
