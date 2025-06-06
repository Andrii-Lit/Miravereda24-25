package es.ieslavereda.miravereda.API;

import es.ieslavereda.miravereda.Base.Parameters;
import java.io.IOException;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Clase que proporciona métodos estáticos para realizar llamadas HTTP (GET, POST, PUT, DELETE)
 * a través de Retrofit y la interfaz {@link APIService}. Sigue el patrón singleton.
 *
 * @param <T> Tipo genérico no utilizado directamente en esta clase, pero incluido por compatibilidad futura.
 */
public class CallMethods<T> {

    // Instancia de Retrofit construida con la URL base definida en Parameters
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(Parameters.URL_API_BASE).build();

    // Instancia del servicio que define las llamadas HTTP
    private APIService service = retrofit.create(APIService.class);

    // Instancia singleton de esta clase
    private static CallMethods callMethods;

    /**
     * Devuelve la instancia singleton de {@code CallMethods}.
     *
     * @return Instancia única de {@code CallMethods}.
     */
    public static CallMethods getCallMethodsObject() {
        if (callMethods == null) {
            callMethods = new CallMethods();
        }
        return callMethods;
    }

    /**
     * Realiza una llamada HTTP GET a la URL especificada.
     *
     * @param url URL del recurso al que se desea acceder.
     * @return Respuesta del servidor en forma de cadena, o un mensaje indicando que no se ha devuelto nada.
     */
    public String get(String url) {
        Call<ResponseBody> call = service.getCall(url);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
            if (response.body() != null)
                return response.body().string();
            else if (response.errorBody() != null)
                return response.errorBody().string();
            return "No se ha devuelto nada";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Realiza una llamada HTTP POST a la URL especificada con un cuerpo de datos.
     *
     * @param url  URL del recurso al que se desea enviar la solicitud.
     * @param data Cuerpo de la solicitud en formato {@link RequestBody}.
     * @return Respuesta del servidor como cadena, o un mensaje si no se devolvió nada.
     */
    public String post(String url, RequestBody data) {
        Call<ResponseBody> call = service.postCall(url, data);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
            if (response.body() != null)
                return response.body().string();
            else if (response.errorBody() != null)
                return response.errorBody().string();
            return "No se ha devuelto nada";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Realiza una llamada HTTP PUT a la URL especificada con un cuerpo de datos.
     *
     * @param url  URL del recurso que se desea actualizar.
     * @param data Cuerpo de la solicitud en formato {@link RequestBody}.
     * @return Respuesta del servidor como cadena, o un mensaje si no se devolvió nada.
     */
    public String put(String url, RequestBody data) {
        Call<ResponseBody> call = service.putCall(url, data);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
            if (response.body() != null)
                return response.body().string();
            else if (response.errorBody() != null)
                return response.errorBody().string();
            return "No se ha devuelto nada";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Realiza una llamada HTTP DELETE a la URL especificada.
     *
     * @param url URL del recurso que se desea eliminar.
     * @return Respuesta del servidor como cadena, o un mensaje si no se devolvió nada.
     */
    public String delete(String url) {
        Call<ResponseBody> call = service.deleteCall(url);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
            if (response.body() != null)
                return response.body().string();
            else if (response.errorBody() != null)
                return response.errorBody().string();
            return "No se ha devuelto nada";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}