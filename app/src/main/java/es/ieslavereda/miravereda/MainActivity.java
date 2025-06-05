package es.ieslavereda.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;

/**
 * @author Andrii,Cristobal,Dario,Leonardo,Ivan
 */
public class MainActivity extends BaseActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button btIniciarSesion;
    private FloatingActionButton fabPreference;
    private TextView tvCrearCuenta, tvContrasenya;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        int tema = preferences.getInt("tema", 0);
        setTheme(tema);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btIniciarSesion = findViewById(R.id.btIniciarSesion);
        fabPreference = findViewById(R.id.fabPreference);
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta);
        tvContrasenya = findViewById(R.id.tvContrasenya);

        tvCrearCuenta.setOnClickListener((View view)->{
            Intent intent = new Intent(this, CrearActivity.class);
            startActivity(intent);
        });
        btIniciarSesion.setOnClickListener(v -> login());

        fabPreference.setOnClickListener(v -> {
            startActivity(new Intent(this, PreferenciasActivity.class));
        });

        tvContrasenya.setOnClickListener(v -> {
            startActivity(new Intent(this, ResetActivity.class));
        });
    }

    /**
     * Metodo que llama a la api para comprobar que el cliente esta en la base de datos
     * y que acceda si pone sus credenciales correctamente.
     */

    private void login() {
        final String email = username.getText().toString();
        final String contrasenya = password.getText().toString();

        if (email.isEmpty() || contrasenya.isEmpty()) {
            Toast.makeText(this, R.string.tostadaRellenarCampos, Toast.LENGTH_LONG).show();
            return;
        }

        final Cliente cliente = new Cliente(email, contrasenya);

        executeCall(new CallInterface<Cliente>() {
            /**
             *
             * @return
             * @throws Exception
             * Realiza la llamada a la Api en segundo plano para que le diga si las creedenciales son correctas.
             * Si no lo son lanza una excepcion.
             */
            @Override
            public Cliente doInBackground() throws Exception {

                try {
                    return connector.post(Cliente.class, cliente, "login/");
                } catch (Exception e) {
                    throw new Exception("Error al iniciar sesion");
                }
            }

            /**
             *
             * @param clienteResponse
             * Si la llamda a la api le devuelve las credenciales como correctas lo manda con un intent
             * a la actividad catalogo.
             */
            @Override
            public void doInUI(Cliente clienteResponse) {
                if (clienteResponse != null) {
                    Toast.makeText(MainActivity.this, R.string.tostadaLogin, Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = getSharedPreferences("cliente", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", clienteResponse.getEmail());
                    editor.putString("contrasenya", password.getText().toString());
                    editor.putInt("clienteId",clienteResponse.getId());
                    editor.apply();
                    Log.d("LOGIN", "Voy a lanzar catalogo activity");
                    Toast.makeText(MainActivity.this, "Lanzando catalogo...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, R.string.tostadaCredencialesIncorrectas, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}