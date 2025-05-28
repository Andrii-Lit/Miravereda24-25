package es.ieslavereda.miravereda;


import android.content.Intent;
import android.content.SharedPreferences;
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

import es.ieslavereda.miravereda.Base.BaseActivity;

public class PreferenciasActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner bSpinner, aSpinner;
    private boolean userSelect = false;
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

        loadLocale();
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Spinner_items_idiomas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpinner.setAdapter(adapter);
        setSpinnerSelection();
        aSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if (!userSelect) {
                    userSelect = true;
                    return;
                }

                String selectedLanguage = parent.getItemAtPosition(position).toString();
                String currentLang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "es");

                String newLang = currentLang;

                if (selectedLanguage.equals("Castellano")) {
                    newLang = "es";
                } else if (selectedLanguage.equals("Inglés")) {
                    newLang = "en";
                } else if (selectedLanguage.equals("Valenciano")) {
                    newLang = "ca";
                }

                if (!newLang.equals(currentLang)) {
                    changeLanguage(newLang);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


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



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void setSpinnerSelection() {
        String lang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "es");
        int position = 0; // Default to English

        if (lang.equals("es")) {
            position = 0;
        } else if (lang.equals("ca")) {
            position = 1;
        } else if (lang.equals("en")) {
            position = 2;
        }

        aSpinner.setSelection(position);
    }
    private void changeLanguage(String langCode) {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", langCode);
        editor.apply();

        setLocale(langCode);


        recreate();
    }
    private void loadLocale() {
        String lang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "");
        if (!lang.equals("")) {
            setLocale(lang);
        }
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }


}
