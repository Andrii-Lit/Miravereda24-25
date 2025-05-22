package es.ieslavereda.miravereda.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Conversor {
    private static Gson gson;
    private static Conversor conversor;

    public static Conversor getConversor() {
        if (conversor == null) {
            gson = new Gson();
            conversor = new Conversor();
        }
        return conversor;
    }

    public <T> String toJson(T data) {
        String json = gson.toJson(data);
        return json;
    }

    public <T> String toJson(List<T> data) {
        String json = gson.toJson(data);
        return json;
    }

    public <T> T fromJson(String json, Class<T> clazz) throws Exception {
        try {
            T object = gson.fromJson(json, clazz);
            return object;
        } catch (Exception e) {
            throw new Exception(json);
        }
    }

    public <T> List<T> fromJsonList(String json, Class<T> clazz) throws Exception {
        try {
            Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
            List<T> object = gson.fromJson(json, typeOfT);
            return object;
        } catch (Exception e) {
            throw new Exception(json);
        }
    }

    public <K, V> Map<K, V> fromJsonMap(String json, Class<K> clazzK, Class<V> clazzV) throws Exception {
        try {
            Type typeOfT = TypeToken.getParameterized(Map.class, clazzK, clazzV).getType();
            Map<K, V> object = gson.fromJson(json, typeOfT);
            return object;
        } catch (Exception e) {
            throw new Exception(json);
        }
    }

}
