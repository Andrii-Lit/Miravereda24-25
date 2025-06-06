package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.CarritoRequest;
import es.ieslavereda.miravereda.Model.Contenido;

/**
 * Actividad que muestra los detalles de un contenido multimedia,
 * permite valorar el contenido y añadirlo al carrito de compra.
 */
public class DetalleContenidoActivity extends BaseActivity implements CallInterface<Void> {

    /** Objeto contenido con los datos que se muestran en la pantalla */
    private Contenido contenido;

    /** Elementos gráficos de la interfaz */
    private ImageView poster;
    private TextView descripcion, nombreautor, preciovalor, notaMediaValor, titulo, anyo;
    private EditText notaCliente;
    private Button anyadirAlCarrito, valorar;
    private FloatingActionButton volver;

    /** Objeto que representa la petición para añadir al carrito */
    private CarritoRequest carritoRequest;

    /**
     * Método llamado al crear la actividad.
     * Inicializa la interfaz, recupera el contenido recibido y configura listeners.
     *
     * @param savedInstanceState Bundle con estado previo guardado (puede ser null).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        Bundle extra = getIntent().getExtras();
        contenido = (Contenido) extra.getSerializable("contenido");

        poster = findViewById(R.id.Poster);
        descripcion = findViewById(R.id.Descripcion);
        nombreautor = findViewById(R.id.NombreDirector);
        anyo = findViewById(R.id.Anyo);
        preciovalor = findViewById(R.id.precioValor);
        notaMediaValor = findViewById(R.id.NotaMediaNumero);
        notaCliente = findViewById(R.id.notaCliente);
        anyadirAlCarrito = findViewById(R.id.AddCarrito);
        valorar = findViewById(R.id.Valorar);
        volver = findViewById(R.id.floatingButtonReturn);
        titulo = findViewById(R.id.Titulo);

        Picasso.get().load(contenido.getPoster_path()).into(poster);
        titulo.setText(contenido.getTitulo());
        anyo.setText(String.valueOf(contenido.getFecha_estreno()));
        descripcion.setText(contenido.getDescripcion());
        notaMediaValor.setText(String.valueOf(contenido.getPuntuacion_media()));
        nombreautor.setText(contenido.getNombre_dir());
        preciovalor.setText(contenido.getPrecio() + " €");

        volver.setOnClickListener((View view) -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        valorar.setOnClickListener(v -> {
            int clienteId = obtenerClienteId();
            if (clienteId == -1) {
                showToast(getString(R.string.toastErrorObtenerID));
                return;
            }
            String notaStr = notaCliente.getText().toString();
            if (notaStr.isEmpty()) {
                showToast(getString(R.string.toastIntroducirNota));
                return;
            }
            float valor;
            try {
                valor = Float.parseFloat(notaStr);
            } catch (NumberFormatException e) {
                showToast(getString(R.string.toastNotaNumero));
                return;
            }

            Map<String, Object> valoracionRequest = new HashMap<>();
            valoracionRequest.put("clienteId", clienteId);
            valoracionRequest.put("contenidoId", contenido.getId());
            valoracionRequest.put("valor", valor);

            showProgress();
            executeCall(new CallInterface<Void>() {
                /**
                 * Ejecuta la petición POST para enviar la valoración.
                 *
                 * @return null
                 * @throws Exception si hay error en la comunicación
                 */
                @Override
                public Void doInBackground() throws Exception {
                    Connector.getConector().postVoid(valoracionRequest, "votar");
                    return null;
                }

                /**
                 * Se ejecuta al recibir respuesta correcta.
                 * Muestra mensaje y finaliza la actividad.
                 *
                 * @param data null
                 */
                @Override
                public void doInUI(Void data) {
                    hideProgress();
                    showToast(getString(R.string.toastValoracionenviada));
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

                /**
                 * Se ejecuta si hay error durante la petición.
                 *
                 * @param context Contexto actual
                 * @param e Excepción ocurrida
                 */
                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast(R.string.toastErrorValorar + e.getMessage());
                }
            });
        });

        anyadirAlCarrito.setOnClickListener(v -> {
            int clienteId = obtenerClienteId();
            if (clienteId == -1) {
                showToast(getString(R.string.toastErrorObtenerID));
                return;
            }
            carritoRequest = new CarritoRequest(clienteId, contenido.getId());
            showProgress();
            executeCall(this);
        });
    }

    /**
     * Obtiene el id del cliente almacenado en preferencias.
     *
     * @return id del cliente o -1 si no se encuentra.
     */
    private int obtenerClienteId() {
        SharedPreferences prefs = getSharedPreferences("cliente", MODE_PRIVATE);
        return prefs.getInt("clienteId", -1);
    }

    /**
     * Implementación de la llamada en segundo plano para añadir contenido al carrito.
     *
     * @return null
     * @throws Exception si falla la petición
     */
    @Override
    public Void doInBackground() throws Exception {
        Connector.getConector().postVoid(carritoRequest, "carrito/");
        return null;
    }

    /**
     * Acción a realizar en la interfaz al completar la petición correctamente.
     * Oculta progreso, muestra mensaje y finaliza la actividad.
     *
     * @param data null
     */
    @Override
    public void doInUI(Void data) {
        hideProgress();
        showToast(getString(R.string.toastContenidoAñadidoCarrito));
        finish();
    }

    /**
     * Acción a realizar en caso de error durante la petición.
     * Oculta progreso y muestra mensaje con la excepción.
     *
     * @param context Contexto actual
     * @param e Excepción ocurrida
     */
    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast(R.string.toastErrorAñadirContenido + e.getMessage());
    }
}