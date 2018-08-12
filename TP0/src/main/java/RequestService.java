import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

public class RequestService {

    private static final String URL = "http://notitas.herokuapp.com";
    private static final String RESOURCE1 = "student";
    private static final String RESOURCE2 = "student/assignments";
    //private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMTEyMjIzMzMiLCJybmQiOiJ5SXNmZFIwN2lIR3BRRmVjYU9KT2VRPT0ifQ.9pVJGUXhrJPQ-TptNCt971l0h_1dWqWgMrHAWXJchho";

    private static String callService(String path, String token){
    String value = "Bearer " + token;
    WebResource recurso = Client.create().resource(URL).path(path);
    ClientResponse response = recurso.header("Authorization", value).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    String json = response.getEntity(String.class);
    return json;
    }

    public static AlumnoJson obtenerDatosDeAlumno(String token){
        AlumnoJson alu = new Gson().fromJson(callService(RESOURCE1, token), AlumnoJson.class);
        return alu;
    }

    public static ListaDeTareas obtenerNotasDeAlumno(String token){
    ListaDeTareas lista = new Gson().fromJson(callService(RESOURCE2, token), ListaDeTareas.class);
        return lista;
    }
}