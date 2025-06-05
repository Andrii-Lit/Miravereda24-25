package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;

public class ResetActivity extends BaseActivity {
    private TextInputEditText reset_correoTIET, reset_contrasenyaTIET;
    private FloatingActionButton fbtBack;
    private Button reset_cambiarBTN;
    private Cliente clienteActualizado = null;
    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset);
        reset_correoTIET = findViewById(R.id.reset_correoTIET);
        reset_contrasenyaTIET = findViewById(R.id.reset_contrasenyaTIET);
        reset_cambiarBTN = findViewById(R.id.reset_cambiarBTN);
        fbtBack = findViewById(R.id.fbtBack);
        fbtBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });
        reset_cambiarBTN.setOnClickListener((View view) -> {
            String email = reset_correoTIET.getText().toString().trim();
            String nuevaContrasenya = reset_contrasenyaTIET.getText().toString().trim();

            if (email.isEmpty() || nuevaContrasenya.isEmpty()) {
                Toast.makeText(ResetActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            executeCall(new CallInterface<Cliente>() {
                @Override
                public Cliente doInBackground() throws Exception {
                    return Connector.getConector().get(Cliente.class, "cliente?email=" + email);
                }

                @Override
                public void doInUI(Cliente data) {
                    if (data != null) {
                        //encontramos el cliente por email, le seteamos la nueva contraseña
                        data.setContrasenya(nuevaContrasenya);
                        clienteActualizado = data;

                        //una vez comprobado que recibimos el cliente hacemos el PUT
                        executeCall(new CallInterface<Cliente>() {
                            @Override
                            public Cliente doInBackground() throws Exception {
                                return Connector.getConector().put(Cliente.class, clienteActualizado, "cliente");
                            }
                            @Override
                            public void doInUI(Cliente updated) {
                                Toast.makeText(ResetActivity.this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK, new Intent());
                                finish();
                            }
                        });

                    } else {
                        Toast.makeText(ResetActivity.this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });



    }


}