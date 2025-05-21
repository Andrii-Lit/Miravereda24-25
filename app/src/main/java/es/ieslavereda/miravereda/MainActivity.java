package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private ImageView ivLogo;
    private ImageView ivPreference;
    private TextInputEditText username;
    private TextInputEditText password;
    private TextView tvContrasenya;
    private Button btIniciarSesion;
    private TextView tvCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ivLogo=findViewById(R.id.ivLogo);
        ivPreference=findViewById(R.id.ivPreference);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        tvContrasenya=findViewById(R.id.tvContrasenya);
        btIniciarSesion=findViewById(R.id.btIniciarSesion);
        tvCrearCuenta=findViewById(R.id.tvCrearCuenta);


        btIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CatalogoActivity.class);
                startActivity(intent);
            }
        });
    }
}