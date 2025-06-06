package es.ieslavereda.miravereda.Base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

/**
 * Clase utilitaria para descargar imágenes y mostrarlas en un ImageView.
 * Usa Picasso para descargas simples y Volley para descargas personalizadas.
 */
public class ImageDownloader {

    private static RequestQueue colaPeticiones;
    private static final String TAG = ImageDownloader.class.getName();

    /**
     * Descarga una imagen desde una URL y la carga en el ImageView usando Picasso.
     *
     * @param url       URL de la imagen a descargar.
     * @param imageView ImageView donde se mostrará la imagen.
     */
    public static void downloadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    /**
     * Descarga una imagen desde una URL y la carga en el ImageView usando Picasso.
     * Muestra un Drawable por defecto en caso de error.
     *
     * @param url           URL de la imagen a descargar.
     * @param errorDrawable Drawable que se mostrará en caso de error.
     * @param imageView     ImageView donde se mostrará la imagen.
     */
    public static void downloadImage(String url, Drawable errorDrawable, ImageView imageView) {
        Picasso.get().load(url).error(errorDrawable).into(imageView);
    }

    /**
     * Descarga una imagen desde una URL usando Volley y la carga en el ImageView.
     * En caso de error muestra una imagen por defecto.
     *
     * @param context         Contexto para crear la cola de peticiones.
     * @param url             URL de la imagen a descargar.
     * @param imageView       ImageView donde se mostrará la imagen.
     * @param defaultDrawable Recurso drawable que se mostrará en caso de error.
     */
    public static void downloadImage(Context context, String url, ImageView imageView, int defaultDrawable) {
        ImageRequest peticion = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                },
                0, 0, null, // maxWidth, maxHeight, decodeConfig
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(defaultDrawable);
                        Log.e(TAG, error.getMessage(), error);
                    }
                }
        );
        getRequestQueue(context).add(peticion);
    }

    /**
     * Obtiene o crea la cola de peticiones Volley.
     *
     * @param context Contexto para crear la cola si no existe.
     * @return Instancia singleton de la cola de peticiones.
     */
    private static RequestQueue getRequestQueue(Context context) {
        if (colaPeticiones == null)
            colaPeticiones = Volley.newRequestQueue(context);
        return colaPeticiones;
    }
}