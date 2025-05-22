package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.miravereda.Model.Cliente;

public class MainActivity extends AppCompatActivity {
    private ImageView ivLogo;
    private ImageView ivPreference;
    private TextInputEditText username;
    private TextInputEditText password;
    private TextView tvContrasenya;
    private Button btIniciarSesion;
    private TextView tvCrearCuenta;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvUsername), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*
        Users simples para testingus
         */
        clientes = new ArrayList<>();
        clientes.add(new Cliente("usuario1@gmail.com", "password1"));
        clientes.add(new Cliente("usuario2@gmail.com", "password2"));

        ivLogo = findViewById(R.id.ivLogo);
        ivPreference = findViewById(R.id.ivPreference);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        tvContrasenya = findViewById(R.id.tvContrasenya);
        btIniciarSesion = findViewById(R.id.btIniciarSesion);
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta);


        btIniciarSesion.setOnClickListener(v -> {
            String email = username.getText().toString();
            String contrasenya = password.getText().toString();
            if (usuarioCorrecto(email,contrasenya)) {
                Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(this,"Usuario o contraseña incorrects",Toast.LENGTH_LONG).show();
        });

        //Entrar a preferencias
        ivPreference.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this, PreferenciasActivity.class);
            startActivity(intent);
        });
        //Ir a ver la info del usuario


        tvContrasenya.setOnClickListener(v->{
            String email= String.valueOf(username.getText());
            String contrasenya= String.valueOf(password.getText());
            Intent intent=new Intent(MainActivity.this,UserInfoActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("contrasenya",contrasenya);
                startActivity(intent);
                });

        btIniciarSesion.setOnClickListener(v -> {
            String email = username.getText().toString();
            String contrasenya = password.getText().toString();
            if (usuarioCorrecto(email,contrasenya)) {
                Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(this,"Usuario o contraseña incorrects",Toast.LENGTH_LONG).show();
        });


    }




    public Boolean usuarioCorrecto(String email, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getContrasenya().equals(password)) {
                return true;
            }
        }
        return false;
    }

}