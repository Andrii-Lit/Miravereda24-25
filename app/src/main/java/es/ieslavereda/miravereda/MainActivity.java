package es.ieslavereda.miravereda;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btIniciarSesion = findViewById(R.id.btIniciarSesion);
        ivPreference = findViewById(R.id.ivPreference);
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta);
        tvContrasenya = findViewById(R.id.tvContrasenya);

        btIniciarSesion.setOnClickListener(v -> login());

        ivPreference.setOnClickListener(v -> {
            startActivity(new Intent(this, PreferenciasActivity.class));
        });

//        tvCrearCuenta.setOnClickListener(v -> {
//            startActivity(new Intent(this, NewUserActivity.class));
//        });

        tvContrasenya.setOnClickListener(v -> {
            startActivity(new Intent(this, UserInfoActivity.class));
        });
    }

    private void login() {
        final String email = username.getText().toString();
        final String contrasenya = password.getText().toString();

        Cliente cliente = new Cliente(email, contrasenya);

        executeCall(new CallInterface<Cliente>() {
            @Override
            public Cliente doInBackground() throws Exception {
                return connector.post(Cliente.class, cliente, "login/");
            }

            @Override
            public void doInUI(Cliente clienteResponse) {
                if (clienteResponse != null) {
                    SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", clienteResponse.getEmail());
                    editor.putString("nombre", clienteResponse.getNombre());
                    editor.putString("contrasenya", cliente.getContrasenya()); // O no guardarla
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
