package es.ieslavereda.miravereda.API;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import es.ieslavereda.miravereda.Base.Parameters;
import es.ieslavereda.miravereda.Model.Cliente;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Clase encargada de conectar con la API del backend a través de los métodos definidos
 * en {@link CallMethods} y realizar la conversión de objetos con {@link Conversor}.
 * Implementa el patrón Singleton.
 */
public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    /**
     * Devuelve la instancia singleton de {@code Connector}.
     * Inicializa las dependencias si aún no han sido creadas.
     *
     * @return Instancia única de {@code Connector}.
     */
    public static Connector getConector() {
        if (connector == null) {
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethodsObject();
        }
        return connector;
    }

    /**
     * Realiza una petición GET y convierte la respuesta en una lista de objetos del tipo indicado.
     *
     * @param clazz Clase del tipo de los objetos de la lista.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo de los elementos de la lista.
     * @return Lista de objetos del tipo indicado o {@code null} si la respuesta es vacía.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> List<T> getAsList(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    /**
     * Realiza una petición GET y convierte la respuesta en un mapa de objetos de los tipos indicados.
     *
     * @param clazzK Clase del tipo de las claves del mapa.
     * @param clazzV Clase del tipo de los valores del mapa.
     * @param path Ruta del endpoint de la API.
     * @param <K> Tipo de las claves del mapa.
     * @param <V> Tipo de los valores del mapa.
     * @return Mapa de objetos o {@code null} si la respuesta es vacía.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <K, V> Map<K, V> getAsMap(Class<K> clazzK, Class<V> clazzV, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonMap(jsonResponse, clazzK, clazzV);
        return null;
    }

    /**
     * Realiza una petición POST sin esperar respuesta, enviando un objeto como cuerpo.
     *
     * @param data Objeto que se enviará en formato JSON.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo del objeto enviado.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> void postVoid(T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        callMethodsObject.post(url, body);
    }

    /**
     * Realiza una petición POST sin cuerpo (vacío).
     *
     * @param path Ruta del endpoint de la API.
     * @throws Exception Si ocurre un error en la llamada.
     */
    public void postVoid(String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        RequestBody emptyBody = RequestBody.create(null, new byte[0]);
        callMethodsObject.post(url, emptyBody);
    }

    /**
     * Realiza una petición DELETE sin respuesta esperada.
     *
     * @param path Ruta del endpoint de la API.
     * @throws Exception Si ocurre un error en la llamada.
     */
    public void deleteVoid(String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        callMethodsObject.delete(url);
    }

    /**
     * Realiza una petición GET y convierte la respuesta en un objeto del tipo indicado.
     *
     * @param clazz Clase del tipo del objeto.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo del objeto.
     * @return Objeto del tipo indicado o {@code null} si la respuesta es vacía.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> T get(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     * Realiza una petición POST con un objeto como cuerpo y espera una respuesta del mismo tipo.
     *
     * @param clazz Clase del tipo del objeto de respuesta.
     * @param data Objeto que se enviará como JSON.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo del objeto.
     * @return Objeto convertido desde la respuesta o {@code null}.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> T post(Class<T> clazz, T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     * Realiza una petición POST con un objeto como cuerpo y espera una lista como respuesta.
     *
     * @param clazz Clase del tipo de los elementos de la lista de respuesta.
     * @param data Objeto que se enviará como JSON.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo de los elementos de la lista.
     * @param <T2> Tipo del objeto enviado.
     * @return Lista de objetos o {@code null} si la respuesta es vacía.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T, T2> List<T> postAsList(Class<T> clazz, T2 data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if (jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    /**
     * Realiza una petición PUT con un objeto como cuerpo y espera una respuesta del mismo tipo.
     *
     * @param clazz Clase del tipo del objeto de respuesta.
     * @param data Objeto que se enviará como JSON.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo del objeto.
     * @return Objeto convertido desde la respuesta o {@code null}.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> T put(Class<T> clazz, T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.put(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     * Realiza una petición DELETE y espera una respuesta convertida al tipo indicado.
     *
     * @param clazz Clase del tipo del objeto de respuesta.
     * @param path Ruta del endpoint de la API.
     * @param <T> Tipo del objeto.
     * @return Objeto convertido desde la respuesta o {@code null}.
     * @throws Exception Si ocurre un error en la conversión o llamada.
     */
    public <T> T delete(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.delete(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

}