package es.ieslavereda.miravereda.API;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class Conversor {

    private static Gson gson;
    private static Conversor conversor;

    /**
     * Devuelve una instancia singleton del Conversor.
     * Inicializa el objeto Gson con adaptadores si es necesario.
     *
     * @return Instancia única de Conversor.
     */
    public static Conversor getConversor() {
        if (conversor == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();
            } else {
                gson = new GsonBuilder().create();
            }
            conversor = new Conversor();
        }
        return conversor;
    }

    /**
     * Convierte un objeto a su representación JSON.
     *
     * @param data Objeto a convertir.
     * @param <T> Tipo del objeto.
     * @return JSON en forma de String.
     */
    public <T> String toJson(T data) {
        return gson.toJson(data);
    }

    /**
     * Convierte una lista a su representación JSON.
     *
     * @param data Lista de objetos a convertir.
     * @param <T> Tipo de los objetos en la lista.
     * @return JSON en forma de String.
     */
    public <T> String toJson(List<T> data) {
        return gson.toJson(data);
    }

    /**
     * Convierte un JSON en un objeto del tipo especificado.
     *
     * @param json  Cadena JSON a convertir.
     * @param clazz Clase del objeto destino.
     * @param <T>   Tipo del objeto.
     * @return Objeto convertido.
     * @throws Exception Si ocurre un error al parsear.
     */
    public <T> T fromJson(String json, Class<T> clazz) throws Exception {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new Exception("Error al parsear JSON: " + json, e);
        }
    }

    /**
     * Convierte un JSON en una lista del tipo especificado.
     *
     * @param json  Cadena JSON a convertir.
     * @param clazz Clase de los elementos de la lista.
     * @param <T>   Tipo de los elementos.
     * @return Lista convertida.
     * @throws Exception Si ocurre un error al parsear.
     */
    public <T> List<T> fromJsonList(String json, Class<T> clazz) throws Exception {
        try {
            Log.d("Conversor", "JSON a parsear: " + json);
            Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            throw new Exception("Error al parsear lista JSON: " + json, e);
        }
    }

    /**
     * Convierte un JSON en un mapa con tipos especificados.
     *
     * @param json   Cadena JSON a convertir.
     * @param clazzK Clase de las claves del mapa.
     * @param clazzV Clase de los valores del mapa.
     * @param <K>    Tipo de las claves.
     * @param <V>    Tipo de los valores.
     * @return Mapa convertido.
     * @throws Exception Si ocurre un error al parsear.
     */
    public <K, V> Map<K, V> fromJsonMap(String json, Class<K> clazzK, Class<V> clazzV) throws Exception {
        try {
            Type typeOfT = TypeToken.getParameterized(Map.class, clazzK, clazzV).getType();
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            throw new Exception("Error al parsear mapa JSON: " + json, e);
        }
    }
}