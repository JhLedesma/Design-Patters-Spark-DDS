package DB.Converter;

import DB.Serializador.SerializadorJson;
import Modelo.Indicadores.Expresion;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IndicadorConverter implements AttributeConverter<Expresion, String> {

    @Override
    public String convertToDatabaseColumn(Expresion expresiones) {

        return SerializadorJson.getInstance().serializar(expresiones);

    }

    @Override
    public Expresion convertToEntityAttribute(String expresiones) {

        return SerializadorJson.getInstance().deserializar(expresiones, Expresion.class);

    }

}