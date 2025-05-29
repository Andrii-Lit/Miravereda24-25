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
import java.time.LocalDateTime;

public class Conversor {
    private static Gson gson;
    private static Conversor conversor;

    public static Conversor getConversor() {
        if (conversor == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android Oreo o superior: gson con adaptadores para fechas
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


    public <T> String toJson(T data) {
        return gson.toJson(data);
    }

    public <T> String toJson(List<T> data) {
        return gson.toJson(data);
    }

    public <T> T fromJson(String json, Class<T> clazz) throws Exception {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new Exception("Error al parsear JSON: " + json, e);
        }
    }

    public <T> List<T> fromJsonList(String json, Class<T> clazz) throws Exception {
        try {
            Log.d("Conversor", "JSON a parsear: " + json);
            Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            throw new Exception("Error al parsear lista JSON: " + json, e);
        }
    }

    public <K, V> Map<K, V> fromJsonMap(String json, Class<K> clazzK, Class<V> clazzV) throws Exception {
        try {
            Type typeOfT = TypeToken.getParameterized(Map.class, clazzK, clazzV).getType();
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            throw new Exception("Error al parsear mapa JSON: " + json, e);
        }
    }
}
