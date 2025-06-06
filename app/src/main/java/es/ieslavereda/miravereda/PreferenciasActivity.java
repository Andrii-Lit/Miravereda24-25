package es.ieslavereda.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ieslavereda.miravereda.Base.BaseActivity;

/**
 * Actividad para la gestión de preferencias de usuario,
 * como idioma y tema (modo noche/día).
 */
public class PreferenciasActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner bSpinner, aSpinner;
    private boolean userSelect = false;
    private FloatingActionButton botonVoladorMagico;

    /**
     * Clase interna para gestionar cambios de idioma en la aplicación.
     */
    public class LocaleHelper {
        /**
         * Cambia el idioma de la aplicación para el contexto dado.
         * @param context Contexto donde aplicar el cambio
         * @param languageCode Código de idioma (ejemplo: "es", "en", "ca")
         */
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

    /**
     * Método llamado al crear la actividad.
     * Configura la interfaz, carga preferencias previas y establece listeners para interacción.
     *
     * @param savedInstanceState Bundle con estado guardado (puede ser null).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loadLocale();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preferencias);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botonVoladorMagico = findViewById(R.id.floatingButtonReturnPreferences);
        bSpinner = findViewById(R.id.bSpinner);
        aSpinner = findViewById(R.id.aSpinner);

        // Adaptador para idiomas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spinner_items_idiomas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpinner.setAdapter(adapter);

        setSpinnerSelection();

        aSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Captura la selección de idioma y cambia idioma si es distinto al actual.
             * @param parent AdapterView donde ocurrió la selección
             * @param view Vista que fue clicada
             * @param position Posición seleccionada
             * @param id ID del elemento seleccionado
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if (!userSelect) {
                    userSelect = true;
                    return;
                }

                String selectedLanguage = parent.getItemAtPosition(position).toString();
                String currentLang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "es");

                String newLang = currentLang;

                if (selectedLanguage.equalsIgnoreCase("Castellano") ||
                        selectedLanguage.equalsIgnoreCase("Castella") ||
                        selectedLanguage.equalsIgnoreCase("Spanish")) {
                    newLang = "es";
                } else if (selectedLanguage.equalsIgnoreCase("Inglés") ||
                        selectedLanguage.equalsIgnoreCase("Anglés") ||
                        selectedLanguage.equalsIgnoreCase("English")) {
                    newLang = "en";
                } else if (selectedLanguage.equalsIgnoreCase("Valenciano") ||
                        selectedLanguage.equalsIgnoreCase("Valencia") ||
                        selectedLanguage.equalsIgnoreCase("Valencian")) {
                    newLang = "ca";
                }

                if (!newLang.equals(currentLang)) {
                    changeLanguage(newLang);
                }
            }

            /**
             * Método requerido por la interfaz, no implementado.
             * @param parent AdapterView que no tiene ningún ítem seleccionado
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Configuración de selección de tema guardado
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        int temaGuardado = preferences.getInt("tema", 0);
        bSpinner.setSelection(temaGuardado);

        bSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Cambia el tema (modo noche/día) según selección.
             * @param adapterView AdapterView donde ocurrió la selección
             * @param view Vista clicada
             * @param position Posición seleccionada
             * @param id ID del ítem seleccionado
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("tema", position);
                editor.apply();

                if (position == 0) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else if (position == 1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

            /**
             * Método requerido por la interfaz, no implementado.
             * @param parent AdapterView que no tiene ítem seleccionado
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Botón flotante que regresa a MainActivity
        botonVoladorMagico.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener que inicia MainActivity y termina la actual.
             * @param v Vista clicada
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferenciasActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Método requerido por AdapterView.OnItemSelectedListener (no usado aquí).
     * @param adapterView AdapterView donde ocurrió la selección
     * @param view Vista clicada
     * @param position Posición seleccionada
     * @param id ID del ítem seleccionado
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) { }

    /**
     * Método requerido por AdapterView.OnItemSelectedListener (no usado aquí).
     * @param parent AdapterView sin selección
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    /**
     * Selecciona el idioma actual en el spinner.
     */
    private void setSpinnerSelection() {
        String lang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "es");
        int position = 0;

        if (lang.equals("es")) {
            position = 0;
        } else if (lang.equals("ca")) {
            position = 1;
        } else if (lang.equals("en")) {
            position = 2;
        }

        aSpinner.setSelection(position);
    }

    /**
     * Cambia el idioma de la aplicación y guarda la selección en preferencias.
     * @param langCode Código de idioma (ejemplo "es", "en", "ca")
     */
    private void changeLanguage(String langCode) {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", langCode);
        editor.apply();

        setLocale(langCode);

        recreate();
    }

    /**
     * Carga el idioma guardado en preferencias al iniciar la actividad.
     */
    private void loadLocale() {
        String lang = getSharedPreferences("Settings", MODE_PRIVATE).getString("My_Lang", "");
        if (!lang.equals("")) {
            setLocale(lang);
        }
    }

    /**
     * Aplica el idioma seleccionado en la configuración de la aplicación.
     * @param langCode Código de idioma
     */
    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}