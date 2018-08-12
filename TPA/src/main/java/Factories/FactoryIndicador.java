package Factories;

import Modelo.Indicadores.Expresion;
import Modelo.Indicadores.Indicador;
import Modelo.Usuarios.Usuario;

public class FactoryIndicador {
	public static Indicador crearIndicador(String nombre, Expresion expresion) {
		return new Indicador(nombre, expresion);
	}
	
	public static Indicador crearIndicadorDeUsuario(Usuario usuario, String nombre, Expresion expresion) {
		return new Indicador(usuario, nombre, expresion);
	}
}
