package Modelo.Usuarios;

import spark.Request;

import DB.Repositorios.RepositorioUsuarios;
import Modelo.Usuarios.Excepciones.PasswordIncorrectaException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GestorDeUsuarios {
    private static GestorDeUsuarios ourInstance = new GestorDeUsuarios();

    public static GestorDeUsuarios getInstance() {
        return ourInstance;
    }

    private GestorDeUsuarios() {
    }

    private Map<Integer, UsuarioLogeado> usuariosEnLinea = new HashMap<>();

    private Integer idLibre = 0;

    private Long tiempoDeVidaAsignable = 15L;

    public Integer obtenerId(String email, String passwordHasheada) {

    	if(RepositorioUsuarios.getInstancia().buscarObjeto(email).chequearPassword(passwordHasheada)) return this.idUsuarioLogeado(email);
    	
    	else throw new PasswordIncorrectaException();

    }

    private Integer idUsuarioLogeado(String email) {
        Integer idAlmacenado = this.idLibre;

        this.usuariosEnLinea.put(idAlmacenado, new UsuarioLogeado(email, LocalDateTime.now().plusMinutes(this.tiempoDeVidaAsignable)));

        idLibre++;

        return idAlmacenado;

    }

    public Map<Object, Object> obtenerMapa(Request request) {
        Map<Object, Object> mapaUsuarioLogeado = new HashMap<>();

        String idUser = request.cookie("idUser");
        String email = request.cookie("email");

        if(idUser == null || email == null) {
            return mapaUsuarioLogeado;
        }

        mapaUsuarioLogeado.put("email", GestorDeUsuarios.getInstance().correo(Integer.parseInt(idUser), email));

        return mapaUsuarioLogeado;
    }

    private String correo(Integer id, String email) {

        if(this.usuariosEnLinea.get(id) != null) {
            if (this.usuariosEnLinea.get(id).getEmail().equals(email))
                return email;
        }

        return null;

    }
}
