package es.ieslavereda.miravereda.API;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

/**
 * Interfaz que define los métodos HTTP genéricos para interactuar con un servicio REST
 * utilizando Retrofit. Cada método acepta una URL dinámica y algunos aceptan un cuerpo de datos.
 */
public interface APIService {

    /**
     * Realiza una petición HTTP GET a la URL especificada.
     *
     * @param url URL completa del recurso al que se desea acceder.
     * @return Objeto {@code Call<ResponseBody>} que representa la respuesta de la petición.
     */
    @GET
    Call<ResponseBody> getCall(@Url String url);

    /**
     * Realiza una petición HTTP POST a la URL especificada, enviando un cuerpo de datos.
     *
     * @param url  URL completa del recurso al que se desea enviar la petición.
     * @param data Cuerpo de la petición en formato {@code RequestBody}.
     * @return Objeto {@code Call<ResponseBody>} que representa la respuesta del servidor.
     */
    @POST
    Call<ResponseBody> postCall(@Url String url, @Body RequestBody data);

    /**
     * Realiza una petición HTTP PUT a la URL especificada, enviando un cuerpo de datos.
     *
     * @param url  URL completa del recurso que se desea actualizar.
     * @param data Cuerpo de la petición en formato {@code RequestBody}.
     * @return Objeto {@code Call<ResponseBody>} que representa la respuesta del servidor.
     */
    @PUT
    Call<ResponseBody> putCall(@Url String url, @Body RequestBody data);

    /**
     * Realiza una petición HTTP DELETE a la URL especificada.
     *
     * @param url URL completa del recurso que se desea eliminar.
     * @return Objeto {@code Call<ResponseBody>} que representa la respuesta de la petición.
     */
    @DELETE
    Call<ResponseBody> deleteCall(@Url String url);
}