package Json;


import Individuo.Individuo;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Individuo.IndividuoAvanzado;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterIndividuo implements JsonDeserializer<Individuo>, JsonSerializer<Individuo> {
    @Override
    public Individuo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        //log.info("Deserializadno json a individuo");
        JsonObject individuoNuevo = jsonElement.getAsJsonObject();
        System.out.println(individuoNuevo.get("Tipo"));
        if (individuoNuevo.get("Tipo").getAsString().contains("LIndividuo/IndividuoBasico;")) {
            return jsonDeserializationContext.deserialize(individuoNuevo.get("Data"), IndividuoBasico.class);
        } else if (individuoNuevo.get("Tipo").getAsString().contains("LIndividuo/IndividuoNormal;")) {
            return jsonDeserializationContext.deserialize(individuoNuevo.get("Data"), IndividuoNormal.class);
        } else if (individuoNuevo.get("Tipo").getAsString().contains("LIndividuo/IndividuoAvanzado;")) {
            return jsonDeserializationContext.deserialize(individuoNuevo.get("Data"), IndividuoAvanzado.class);
        }else{
            // log.fatal("error al encontrar el tipo de individuo");
            throw new JsonParseException("El tipo de individuo no ha encontrado la deserializacion");
        }

    }
    @Override
    public JsonElement serialize(Individuo individuo , Type type , JsonSerializationContext jsonSerializationContext){
        // log.info("serializando individuo a json");
        JsonObject individuoNuevo = new JsonObject();
        individuoNuevo.addProperty("Tipo",individuo.getClass().descriptorString());
        individuoNuevo.add("Data",jsonSerializationContext.serialize(individuo));
        //log.info("individuo a json ");
        return individuoNuevo;
    }

}