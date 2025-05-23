package es.ieslavereda.miravereda;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PreferenciasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner bSpinner;
    private Spinner aSpinner;
    private FloatingActionButton botonVoladorMagico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preferencias);
        botonVoladorMagico = findViewById(R.id.floatingButtonReturnPreferences);
        bSpinner=findViewById(R.id.bSpinner);
        aSpinner=findViewById(R.id.aSpinner);

       SharedPreferences sharedPreferences=getSharedPreferences("Preferencias",MODE_PRIVATE);
        int tema = sharedPreferences.getInt("tema", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        //Aplica el tema segun la preferencia
        AppCompatDelegate.setDefaultNightMode(tema);
        if (tema==AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
            bSpinner.setSelection(0);

        }else if(tema==AppCompatDelegate.MODE_NIGHT_YES){
            bSpinner.setSelection(1);
        }else {
            bSpinner.setSelection(2);
        }
        String idioma=sharedPreferences.getString("idioma","es");
        if (idioma.equals("es"))
            aSpinner.setSelection(0);
        else if (idioma.equals("en"))
            aSpinner.setSelection(1);
        else
            aSpinner.setSelection(2);


        bSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            SharedPreferences sharedPreferences=getSharedPreferences("Preferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Cambiar el tema segun la selección
                if (position == 0){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    editor.putInt("tema",AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else if (position == 1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("tema",AppCompatDelegate.MODE_NIGHT_YES);
                } else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("tema",AppCompatDelegate.MODE_NIGHT_NO);
                }
                //Guardar la preferencia
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        botonVoladorMagico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferenciasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Spinner aSpinner=findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}