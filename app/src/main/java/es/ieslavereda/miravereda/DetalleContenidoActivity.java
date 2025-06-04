package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.CarritoRequest;
import es.ieslavereda.miravereda.Model.Contenido;

public class DetalleContenidoActivity extends BaseActivity implements CallInterface<Void> {

    private Contenido contenido;
    private ImageView poster;
    private TextView descripcion, nombreautor, preciovalor, notaMediaValor, titulo, anyo;
    private EditText notaCliente;
    private Button anyadirAlCarrito, valorar;
    private FloatingActionButton volver;

    private CarritoRequest carritoRequest;

    private ActivityResultLauncher<Intent> carritoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        carritoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                }
        );

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
        preciovalor.setText(String.valueOf(contenido.getPrecio())+" €");
        volver.setOnClickListener(v -> finish());

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
                @Override
                public Void doInBackground() throws Exception {
                    Connector.getConector().postVoid(valoracionRequest, "votar");
                    return null;
                }

                @Override
                public void doInUI(Void data) {
                    hideProgress();
                    showToast(getString(R.string.toastValoracionenviada));
                    recargarContenido(); // <-- ACTUALIZA LA NOTA MEDIA TRAS VOTAR
                }

                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast("Error al valorar: " + e.getMessage());
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

    private int obtenerClienteId() {
        SharedPreferences prefs = getSharedPreferences("cliente", MODE_PRIVATE);
        return prefs.getInt("clienteId", -1);
    }


    private void recargarContenido() {
        showProgress();
        executeCall(new CallInterface<Contenido>() {
            @Override
            public Contenido doInBackground() throws Exception {
                return Connector.getConector().get(Contenido.class, "contenido/" + contenido.getId());
            }
            @Override
            public void doInUI(Contenido nuevoContenido) {
                hideProgress();
                if (nuevoContenido != null) {
                    contenido = nuevoContenido;
                    notaMediaValor.setText(String.valueOf(contenido.getPuntuacion_media()));
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                hideProgress();
                showToast("Error al actualizar la nota media: " + e.getMessage());
            }
        });
    }

    @Override
    public Void doInBackground() throws Exception {
        Connector.getConector().postVoid(carritoRequest, "carrito/");
        return null;
    }

    @Override
    public void doInUI(Void data) {
        hideProgress();
        showToast(getString(R.string.toastContenidoAñadidoCarrito));
        finish();
    }

    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast("Error al añadir contenido al carrito: " + e.getMessage());
    }
}