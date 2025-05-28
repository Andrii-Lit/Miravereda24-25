package es.ieslavereda.miravereda.Base;
import android.content.Context;
import android.widget.Toast;

public interface CallInterface<T> {

    T doInBackground() throws Exception;

    void doInUI(T data);

    default void doInError(Context context, Exception e) {
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}





