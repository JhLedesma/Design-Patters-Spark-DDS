package DB.Serializador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Modelo.Indicadores.*;

public class AdaptadorJson {

    private RuntimeTypeAdapterFactory<Expresion> adapter(){
        RuntimeTypeAdapterFactory<Expresion> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Expresion.class, "type")
                .registerSubtype(Numero.class, "Numero")
                .registerSubtype(Cuenta_Indicadores.class, "Cuenta")
                .registerSubtype(Operacion.class, "Operacion")
                .registerSubtype(Suma.class, "Suma")
                .registerSubtype(Resta.class, "Resta")
                .registerSubtype(Division.class, "Division")
                .registerSubtype(Multiplicacion.class, "Multiplicacion")
                .registerSubtype(Indicador.class, "Indicador");
                
        return runtimeTypeAdapterFactory;
    }

    public Gson getAdaptador(){
        return new GsonBuilder().registerTypeAdapterFactory(this.adapter()).create();
    }

}
