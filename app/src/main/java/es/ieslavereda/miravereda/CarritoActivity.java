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

        // Configurar botón volver
        carrito_backButton.setOnClickListener(v -> finish());

        // Configurar RecyclerView y adaptador con Listener
        carritorvAdapter = new CarritoAdaptadorRV(this, contenidos_anyadidos, carrito_precioTV, new OnCarritoDeleteListener() {
            /**
             *
             * @param contenido
             * @param position
             * Metodo para eliminar del carrito el contenido.
             */
            @Override
            public void onDelete(Contenido contenido, int position) {
                showProgress();
                executeCall(new CallInterface<Void>() {
                    /**
                     *
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public Void doInBackground() throws Exception {
                        Connector.getConector().deleteVoid("carrito/" + clienteId + "/" + contenido.getId());
                        return null;
                    }

                    /**
                     *
                     * @param data
                     * Mostrar los cambios en el recycler view cuando se elimina
                     */
                    @Override
                    public void doInUI(Void data) {
                        contenidos_anyadidos.remove(position);
                        carritorvAdapter.notifyItemRemoved(position);
                        carritorvAdapter.actualizarTotal();
                        hideProgress();
                        Toast.makeText(CarritoActivity.this, R.string.toastEliminarCarrito, Toast.LENGTH_SHORT).show();
                    }

                    /**
                     *
                     * @param context
                     * @param e
                     * Cuando ocurre un error nos muestra el mensaje.
                     */
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

        // Cargar datos del carrito en background
        showProgress();
        executeCall(this);

        // Listener para botón comprar
        carrito_comprarButton.setOnClickListener(v -> {
            showProgress();
            executeCall(new CallInterface<Void>() {
                /**
                 *
                 * @return
                 * @throws Exception
                 * Llama a la api en el path comprar/ y le hace la llamada a la api para comprar un producto.
                 */
                @Override
                public Void doInBackground() throws Exception {
                    Connector.getConector().postVoid("comprar/" + clienteId);
                    return null;
                }

                /**
                 *
                 * @param data
                 *  Recargar el carrito para reflejar los cambios reales desde el backend
                 */

                @Override
                public void doInUI(Void data) {
                    hideProgress();
                    showToast(getString(R.string.toastCompraExito));

                    executeCall(CarritoActivity.this);
                }

                /**
                 *
                 * @param context
                 * @param e
                 * Muestra un toast con mensaje de error.
                 */
                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast(getString(R.string.toastErrorCompra));
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        executeCall(this);
    }

    /**
     *
     * @return
     * @throws Exception
     * Llama a la api para pedirle la lista del carrito del cliente.
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
     *
     * @param data
     * Añade los contenido a la lista cuando recibe la información de la API.
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
     *
     * @param context
     * @param e
     * Muestra un toast indicando el error de no poder cargar la información del carrito.
     */
    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast(getString(R.string.toastErrorCargarCarrito));
        carritorvAdapter.actualizarTotal();
    }
}