package Json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Json {

    public static <T> void guardarObjetoEnArchivo(String rutaArchivo, T objeto) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(Recursos.class, new gsonAdapterRecurso())
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para cargar un objeto desde un archivo JSON
    public static <T> T cargarObjetoDesdeArchivo(String rutaArchivo, Class<T> clase) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(Recursos.class, new gsonAdapterRecurso())
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .setPrettyPrinting()
                .create();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
