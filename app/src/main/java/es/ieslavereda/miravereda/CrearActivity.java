package es.ieslavereda.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import es.ieslavereda.miravereda.Base.BaseActivity;

public class CrearActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        int tema = preferences.getInt("tema", 0);
        setTheme(tema);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


    }
}
