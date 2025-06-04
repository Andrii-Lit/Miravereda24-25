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

public  class BaseActivity extends AppCompatActivity {


        protected Connector connector;
        protected ExecutorService executor = Executors.newSingleThreadExecutor();
        protected Handler handler = new Handler(Looper.getMainLooper());
        protected MyProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            connector = Connector.getConector();
            progressBar = new MyProgressBar(this);
            hideSystemUI();
        }
        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
            if (hasFocus) {
                hideSystemUI();
            }
        }

        protected<T> void executeCall(CallInterface<T>callInterface){
            showProgress();
            executor.execute(() -> {
                try {
                   T  data = callInterface.doInBackground();
                    handler.post(() -> {
                        hideProgress();
                        callInterface.doInUI(data);
                    });
                } catch (Exception e){
                    handler.post(()->{
                        hideProgress();
                        callInterface.doInError(BaseActivity.this,e);
                    });
                }
            });
        }
    public  void showToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

        public void showProgress(){
            progressBar.show();
        }

        public void hideProgress(){
            progressBar.hide();
        }


        // Sobreescribimos el metodo para asociar a la barra de progreso al ContraintLayout o RelativeLayout
        // y asi poder centrarla y manipular la visibilidad del resto de componentes del ViewGroup
        @Override
        public void setContentView(int layout){
            super.setContentView(layout);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) this .findViewById(android.R.id.content)).getChildAt(0);
            progressBar.initControl(rootView);
            hideProgress();
        }
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

