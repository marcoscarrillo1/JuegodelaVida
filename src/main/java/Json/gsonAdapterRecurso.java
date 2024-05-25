package Json;


import Individuo.Individuo;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Individuo.IndividuoAvanzado;

import Recursos.Recursos;
import Recursos.Agua;
import Recursos.Montaña;
import Recursos.Pozo;
import Recursos.Comida;
import Recursos.Biblioteca;
import Recursos.Tesoro;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterRecurso implements JsonDeserializer<Recursos>, JsonSerializer<Recursos> {
    @Override
    public Recursos deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject recursoNuevo = jsonElement.getAsJsonObject();
        if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Agua;")) {
            return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"), Agua.class);
        } else if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Montaña")) {
            return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"), Montaña.class);
        } else if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Tesoro")) {
            return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"), Tesoro.class);
        }
        else if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Biblioteca")) {
            return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"), Biblioteca.class);
        }
        else if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Pozo")) {
                return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"), Pozo.class);
            }
        else if (recursoNuevo.get("Tipo").getAsString().contains("LRecursos/Comida")) {
            return jsonDeserializationContext.deserialize(recursoNuevo.get("Data"),Comida.class);
        }else{
            // log.fatal("error al encontrar el tipo de individuo");
            throw new JsonParseException("El tipo de individuo no ha encontrado la deserializacion");
        }

    }
    @Override
    public JsonElement serialize(Recursos recurso , Type type , JsonSerializationContext jsonSerializationContext){
        // log.info("serializando individuo a json");
        JsonObject recursoNuevo = new JsonObject();
        recursoNuevo.addProperty("Tipo",recurso.getClass().descriptorString());
        recursoNuevo.add("Data",jsonSerializationContext.serialize(recurso));
        //log.info("individuo a json ");
        return recursoNuevo;
    }

}