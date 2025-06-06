package es.ieslavereda.miravereda.Base;

import android.content.Context;
import android.widget.Toast;

/**
 * Interfaz para ejecutar tareas en segundo plano con actualización de UI y manejo de errores.
 *
 * @param <T> Tipo de dato devuelto por la tarea en segundo plano.
 */
public interface CallInterface<T> {

    /**
     * Método que se ejecuta en segundo plano.
     *
     * @return Resultado de la tarea.
     * @throws Exception En caso de error durante la ejecución.
     */
    T doInBackground() throws Exception;

    /**
     * Método que se ejecuta en el hilo de UI con el resultado de la tarea.
     *
     * @param data Resultado de la tarea.
     */
    void doInUI(T data);

    /**
     * Método para manejar errores ocurridos en la ejecución en segundo plano.
     * Implementación por defecto muestra un Toast con el mensaje de error.
     *
     * @param context Contexto para mostrar el Toast.
     * @param e       Excepción ocurrida.
     */
    default void doInError(Context context, Exception e) {
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}