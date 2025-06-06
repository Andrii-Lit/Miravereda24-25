package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;
import es.ieslavereda.miravereda.Model.Contenido;
import es.ieslavereda.miravereda.Model.OnCarritoDeleteListener;

/**
 * Actividad que gestiona el carrito de compras.
 * Implementa la interfaz CallInterface para manejar llamadas asíncronas que devuelven una lista de Contenido.
 */
public class CarritoActivity extends BaseActivity implements CallInterface<List<Contenido>> {

    private List<Contenido> contenidos_anyadidos = new ArrayList<>();
    private FloatingActionButton carrito_backButton;
    private Button carrito_comprarButton;
    private RecyclerView carrito_recyclerView;
    private CarritoAdaptadorRV carritorvAdapter;
    private TextView carrito_precioTV;
    private Cliente cliente;
    private int clienteId = -1;

    /**
     * Método llamado al crear la actividad.
     * Inicializa vistas, configura listeners y carga el carrito desde backend.
     *
     * @param savedInstanceState Bundle con estado guardado de la actividad (si existe).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        carrito_backButton = findViewById(R.id.carrito_backButton);
        carrito_comprarButton = findViewById(R.id.carrito_comprarButton);
        carrito_recyclerView = findViewById(R.id.carrito_recyclerView);
        carrito_precioTV = findViewById(R.id.carrito_precioTV);

        // Leer clienteId desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("cliente", MODE_PRIVATE);
        clienteId = prefs.getInt("clienteId", -1);

        if (clienteId == -1) {
            showToast(getString(R.string.toastErrorID));
            finish();
            return;
        }

        String email = prefs.getString("email", null);
        String contrasenya = prefs.getString("contrasenya", null);
        cliente = new Cliente(email, contrasenya);

        // Configurar botón volver para cerrar la actividad
        carrito_backButton.setOnClickListener(v -> finish());

        // Configurar RecyclerView con adaptador y listener para eliminar items
        carritorvAdapter = new CarritoAdaptadorRV(this, contenidos_anyadidos, carrito_precioTV, new OnCarritoDeleteListener() {
            /**
             * Método que se ejecuta cuando se elimina un contenido del carrito.
             *
             * @param contenido Contenido a eliminar.
             * @param position Posición del contenido en la lista.
             */
            @Override
            public void onDelete(Contenido contenido, int position) {
                showProgress();
                executeCall(new CallInterface<Void>() {
                    @Override
                    public Void doInBackground() throws Exception {
                        Connector.getConector().deleteVoid("carrito/" + clienteId + "/" + contenido.getId());
                        return null;
                    }

                    @Override
                    public void doInUI(Void data) {
                        contenidos_anyadidos.remove(position);
                        carritorvAdapter.notifyItemRemoved(position);
                        carritorvAdapter.actualizarTotal();
                        hideProgress();
                        Toast.makeText(CarritoActivity.this, R.string.toastEliminarCarrito, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doInError(Context context, Exception e) {
                        hideProgress();
                        Toast.makeText(CarritoActivity.this,
                                getString(R.string.toastErrorEliminar) +
                                        (e.getMessage() != null ? e.getMessage()
                                                : getString(R.string.toastErrorDesconocido)),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        carrito_recyclerView.setAdapter(carritorvAdapter);
        carrito_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos del carrito al iniciar
        showProgress();
        executeCall(this);

        // Listener para el botón comprar
        carrito_comprarButton.setOnClickListener(v -> {
            showProgress();
            executeCall(new CallInterface<Void>() {
                @Override
                public Void doInBackground() throws Exception {
                    Connector.getConector().postVoid("comprar/" + clienteId);
                    return null;
                }

                @Override
                public void doInUI(Void data) {
                    hideProgress();
                    showToast(getString(R.string.toastCompraExito));
                    executeCall(CarritoActivity.this);
                }

                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast(getString(R.string.toastErrorCompra));
                }
            });
        });
    }

    /**
     * Método llamado al reanudar la actividad.
     * Recarga la información del carrito.
     */
    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        executeCall(this);
    }

    /**
     * Llamada en segundo plano para obtener la lista de contenidos del carrito desde el backend.
     *
     * @return Lista de contenidos añadidos al carrito.
     * @throws Exception En caso de error en la llamada.
     */
    @Override
    public List<Contenido> doInBackground() throws Exception {
        try {
            List<Contenido> data = Connector.getConector().getAsList(Contenido.class, "carrito/" + clienteId);
            Log.d("Carrito", "Respuesta del backend: " + data);
            return data;
        } catch (Exception e) {
            Log.e("carrito/", "Error al cargar lista contenido", e);
            throw e;
        }
    }

    /**
     * Actualiza la interfaz de usuario con la lista de contenidos recibida.
     *
     * @param data Lista de contenidos obtenida del backend.
     */
    @Override
    public void doInUI(List<Contenido> data) {
        hideProgress();
        contenidos_anyadidos.clear();
        if (data != null && !data.isEmpty()) {
            for (Contenido c : data) {
                if (c != null) contenidos_anyadidos.add(c);
            }
            carritorvAdapter.notifyDataSetChanged();
            carritorvAdapter.actualizarTotal();
        } else if (data != null && data.isEmpty()) {
            carritorvAdapter.notifyDataSetChanged();
            carritorvAdapter.actualizarTotal();
            showToast(getString(R.string.toastCarritoVacio));
        } else {
            showToast(getString(R.string.toastErrorCargar));
            carritorvAdapter.actualizarTotal();
        }
    }

    /**
     * Muestra un mensaje de error en caso de que falle la carga del carrito.
     *
     * @param context Contexto de la aplicación.
     * @param e       Excepción ocurrida.
     */
    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast(getString(R.string.toastErrorCargarCarrito));
        carritorvAdapter.actualizarTotal();
    }
}