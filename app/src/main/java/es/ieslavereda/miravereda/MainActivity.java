package es.ieslavereda.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.google.android.material.textfield.TextInputEditText;

import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;

public class MainActivity extends BaseActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button btIniciarSesion;
    private ImageView ivPreference;
    private TextView tvCrearCuenta, tvContrasenya;

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
        ivPreference = findViewById(R.id.ivPreference);
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta);
        tvContrasenya = findViewById(R.id.tvContrasenya);

        tvCrearCuenta.setOnClickListener((View view)->{
            Intent intent = new Intent(this, CrearActivity.class);
            startActivity(intent);
        });
        btIniciarSesion.setOnClickListener(v -> login());

        ivPreference.setOnClickListener(v -> {
            startActivity(new Intent(this, PreferenciasActivity.class));
        });

        tvContrasenya.setOnClickListener(v -> {
            startActivity(new Intent(this, ResetActivity.class));
        });
    }

    private void login() {
        final String email = username.getText().toString();
        final String contrasenya = password.getText().toString();

        if (email.isEmpty() || contrasenya.isEmpty()) {
            Toast.makeText(this, R.string.tostadaRellenarCampos, Toast.LENGTH_LONG).show();
            return;
        }

        final Cliente cliente = new Cliente(email, contrasenya);

        executeCall(new CallInterface<Cliente>() {
            @Override
            public Cliente doInBackground() throws Exception {

                try {
                    return connector.post(Cliente.class, cliente, "login/");
                } catch (Exception e) {
                    throw new Exception("Error al iniciar sesion");
                }
            }

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