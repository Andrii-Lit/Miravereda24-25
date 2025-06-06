package es.ieslavereda.miravereda.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.ieslavereda.miravereda.API.Connector;

public class BaseActivity extends AppCompatActivity {

    protected Connector connector;
    protected ExecutorService executor = Executors.newSingleThreadExecutor();
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected MyProgressBar progressBar;

    /**
     * Inicializa la actividad, el conector y la barra de progreso.
     * También oculta la barra de sistema para modo inmersivo.
     *
     * @param savedInstanceState Estado previo de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connector = Connector.getConector();
        progressBar = new MyProgressBar(this);
        hideSystemUI();
    }

    /**
     * Oculta la UI del sistema cuando la ventana de la actividad tiene foco.
     *
     * @param hasFocus Indica si la ventana tiene foco.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    /**
     * Ejecuta una tarea en segundo plano y actualiza la UI con los resultados o maneja errores.
     *
     * @param callInterface Interfaz con métodos para tarea en background, UI y manejo de errores.
     * @param <T> Tipo de dato manejado.
     */
    protected <T> void executeCall(CallInterface<T> callInterface) {
        showProgress();
        executor.execute(() -> {
            try {
                T data = callInterface.doInBackground();
                handler.post(() -> {
                    hideProgress();
                    callInterface.doInUI(data);
                });
            } catch (Exception e) {
                handler.post(() -> {
                    hideProgress();
                    callInterface.doInError(BaseActivity.this, e);
                });
            }
        });
    }

    /**
     * Muestra un mensaje Toast corto en pantalla.
     *
     * @param mensaje Texto a mostrar.
     */
    public void showToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Muestra la barra de progreso.
     */
    public void showProgress() {
        progressBar.show();
    }

    /**
     * Oculta la barra de progreso.
     */
    public void hideProgress() {
        progressBar.hide();
    }

    /**
     * Asocia la barra de progreso al layout raíz y la oculta inicialmente.
     *
     * @param layout Resource ID del layout a inflar.
     */
    @Override
    public void setContentView(int layout) {
        super.setContentView(layout);
        ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        progressBar.initControl(rootView);
        hideProgress();
    }

    /**
     * Oculta la barra de navegación y la barra de estado para un modo inmersivo.
     */
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}

