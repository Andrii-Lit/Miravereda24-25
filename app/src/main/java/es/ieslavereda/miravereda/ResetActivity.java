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

/**
 * Actividad que permite al usuario cambiar la contraseña
 * mediante la introducción de un correo electrónico y nueva contraseña.
 */
public class ResetActivity extends BaseActivity {

    private TextInputEditText reset_correoTIET, reset_contrasenyaTIET;
    private FloatingActionButton fbtBack;
    private Button reset_cambiarBTN;
    private Cliente clienteActualizado = null;

    /**
     * Método llamado al crear la actividad.
     * Inicializa vistas, listeners y gestiona la lógica para restablecer la contraseña.
     *
     * @param savedInstanceState Bundle con estado guardado (puede ser null).
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

        // Botón para volver atrás, cierra la actividad con RESULT_CANCELED
        fbtBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        // Botón para cambiar la contraseña
        reset_cambiarBTN.setOnClickListener((View view) -> {
            String email = reset_correoTIET.getText().toString().trim();
            String nuevaContrasenya = reset_contrasenyaTIET.getText().toString().trim();

            // Validación de campos vacíos
            if (email.isEmpty() || nuevaContrasenya.isEmpty()) {
                Toast.makeText(ResetActivity.this, R.string.ResetRellenarCampos, Toast.LENGTH_SHORT).show();
                return;
            }

            // Buscar cliente por email en segundo plano
            executeCall(new CallInterface<Cliente>() {
                /**
                 * Método que se ejecuta en segundo plano para obtener el cliente por email.
                 * @return Cliente encontrado o null si no existe
                 * @throws Exception si ocurre un error durante la conexión
                 */
                @Override
                public Cliente doInBackground() throws Exception {
                    return Connector.getConector().get(Cliente.class, "cliente?email=" + email);
                }

                /**
                 * Método que se ejecuta en UIThread tras obtener el resultado.
                 * Actualiza la contraseña y realiza la llamada PUT para guardar.
                 * @param data Cliente obtenido (o null si no existe)
                 */
                @Override
                public void doInUI(Cliente data) {
                    if (data != null) {
                        // Cliente encontrado, se actualiza la contraseña
                        data.setContrasenya(nuevaContrasenya);
                        clienteActualizado = data;

                        // Llamada PUT para actualizar el cliente
                        executeCall(new CallInterface<Cliente>() {
                            /**
                             * Actualiza el cliente con la nueva contraseña en segundo plano.
                             * @return Cliente actualizado
                             * @throws Exception si ocurre un error en la conexión
                             */
                            @Override
                            public Cliente doInBackground() throws Exception {
                                return Connector.getConector().put(Cliente.class, clienteActualizado, "cliente");
                            }

                            /**
                             * Método que se ejecuta en UIThread tras actualizar el cliente.
                             * Muestra mensaje de éxito y termina la actividad con RESULT_OK.
                             * @param updated Cliente actualizado
                             */
                            @Override
                            public void doInUI(Cliente updated) {
                                Toast.makeText(ResetActivity.this, R.string.toastContraseñaCambiada, Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK, new Intent());
                                finish();
                            }
                        });

                    } else {
                        // Cliente no encontrado con ese email
                        Toast.makeText(ResetActivity.this, R.string.toastCorreoNoEncontrado, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}