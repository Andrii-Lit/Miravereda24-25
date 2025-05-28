package es.ieslavereda.miravereda;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.lang.reflect.Array;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PreferenciasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner bSpinner, aSpinner;

    private FloatingActionButton botonVoladorMagico;

    int  rar;
    public class LocaleHelper {
        public void setLocale(Context context, String languageCode) {
            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }

            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preferencias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvUsername), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botonVoladorMagico = findViewById(R.id.floatingButtonReturnPreferences);
        bSpinner=findViewById(R.id.bSpinner);
        aSpinner = findViewById(R.id.aSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Spinner_items_idiomas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpinner.setAdapter(adapter);

        bSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (position == 0){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else if (position == 1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
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

        String lang= adapterView.getItemAtPosition(position).toString();
        String languageToLoad = null;

        if(lang.equals("Castellano")){
            languageToLoad="es";
        } else if (lang.equals("Valenciano")) {
            languageToLoad="ca";
        } else if (lang.equals("Inglés")) {
            languageToLoad="en";
        }
        if(languageToLoad!=null){
            Locale locale = new Locale (languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}
