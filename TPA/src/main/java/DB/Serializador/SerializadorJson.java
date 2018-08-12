package DB.Serializador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SerializadorJson {
    private static SerializadorJson ourInstance = new SerializadorJson();

    public static SerializadorJson getInstance() {
        return ourInstance;
    }

    private SerializadorJson() {
    }

    public String serializar(Object object) { //Recibo Object porque quiero que esta clase serialize cualquier objeto
        Type type = new TypeToken<Object>(){}.getType(); //uso type por si quiero serializar listas
        Gson gson = new AdaptadorJson().getAdaptador(); //Usamos un adaptador para poder luego deserializar sin probloemas, ya que usamos interfaces
        return gson.toJson(object, type);
    }

    public <T> T deserializar(String object, Class<T> clase) {
        @SuppressWarnings("unused")
		Type type = new TypeToken<Object>(){}.getType(); //uso type por si quiero serializar listas
        Gson gson = new AdaptadorJson().getAdaptador(); //Usamos un adaptador para poder luego deserializar sin probloemas, ya que usamos interfaces

        T objetoAEntregar = gson.fromJson(object, clase);

        return objetoAEntregar;
    }

}
