package Factories;

import Modelo.Usuarios.Usuario;

public class FactoryUsuario {
	public static Usuario crearUsuario(String email, String passwordHasehada) {
		return new Usuario(email, passwordHasehada);
	}
}
