package es.ieslavereda.miravereda.API;

import java.util.List;
import java.util.Map;

import es.ieslavereda.miravereda.Base.Parameters;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    public static Connector getConector() {
        if (connector == null) {
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethodsObject();
        }
        return connector;
    }

    public <T> List<T> getAsList(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    public <K, V> Map<K, V> getAsMap(Class<K> clazzK, Class<V> clazzV, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonMap(jsonResponse, clazzK, clazzV);
        return null;
    }


    public <T> T get(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T post(Class<T> clazz, T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T put(Class<T> clazz, T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.put(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T delete(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.delete(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

}
