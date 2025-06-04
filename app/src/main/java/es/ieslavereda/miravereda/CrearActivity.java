package es.ieslavereda.miravereda;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;

public class CrearActivity extends BaseActivity {
    private Cliente cliente = null;
    private EditText crear_nombreET, crear_apellidosET, crear_fecha_nacET,
            crear_cod_postalET,crear_direccionET, crear_num_tarjetaET, crear_emailET, crear_contrasenyaET;
    private FloatingActionButton crear_backBTN;
    private Button crear_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        int tema = preferences.getInt("tema", 0);
        setTheme(tema);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        EdgeToEdge.enable(this);

        crear_nombreET = findViewById(R.id.crear_nombreET);
        crear_apellidosET = findViewById(R.id.crear_apellidosET);
        crear_fecha_nacET = findViewById(R.id.crear_fecha_nacET);
        crear_cod_postalET = findViewById(R.id.crear_cod_postalET);
        crear_direccionET = findViewById(R.id.crear_direccionET);
        crear_num_tarjetaET = findViewById(R.id.crear_num_tarjetaET);
        crear_emailET = findViewById(R.id.crear_emailET);
        crear_contrasenyaET = findViewById(R.id.crear_contrasenyaET);

        crear_backBTN = findViewById(R.id.crear_backBTN);
        crear_BTN = findViewById(R.id.crear_BTN);
        crear_BTN.setOnClickListener((View view)->{crearCuenta();});
        crear_backBTN.setOnClickListener((View view)->{
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            Toast.makeText(CrearActivity.this, "Acción cancelada", LENGTH_SHORT).show();
            finish();
        });

    }

    public void crearCuenta(){
        cliente = new Cliente(
                    crear_contrasenyaET.getText().toString(),
                    crear_nombreET.getText().toString(),
                    crear_apellidosET.getText().toString(),
                    crear_direccionET.getText().toString(),
                    crear_cod_postalET.getText().toString(),
                    crear_emailET.getText().toString(),
                    LocalDate.parse(crear_fecha_nacET.getText().toString()),
                    crear_num_tarjetaET.getText().toString()
                    );
        executeCall(new CallInterface<Cliente>() {
            @Override
            public Cliente doInBackground() throws Exception {
                return Connector.getConector().post(Cliente.class, cliente, "cliente/");
            }

            @Override
            public void doInUI(Cliente data) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                Toast.makeText(CrearActivity.this, "Cuenta creada con éxito", LENGTH_LONG).show();
                finish();
            }
        });
    }

}
