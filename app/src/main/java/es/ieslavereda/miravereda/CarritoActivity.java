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
            showToast(String.valueOf(R.string.toastErrorID));
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

        // Cargar datos del carrito en background
        showProgress();
        executeCall(this);

        // Listener para botón comprar
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
                    showToast(String.valueOf(R.string.toastCompraExito));
                    // Recargar el carrito para reflejar los cambios reales desde el backend
                    executeCall(CarritoActivity.this);
                }

                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast(String.valueOf(R.string.toastErrorCompra));
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onResume se llama cada vez que la Activity vuelve a primer plano.
        showProgress();
        executeCall(this);
    }

    @Override
    public List<Contenido> doInBackground() throws Exception {
        try {
            List<Contenido> data = Connector.getConector().getAsList(Contenido.class, "carrito/" + clienteId);
            Log.d("Carrito", "Respuesta del backend: " + data);
            return data;
        } catch (Exception e) {
            Log.e("Carrito", "Error al cargar lista contenido", e);
            throw e;
        }
    }

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
            showToast(String.valueOf(R.string.toastCarritoVacio));
        } else {
            showToast(String.valueOf(R.string.toastErrorCargar));
            carritorvAdapter.actualizarTotal();
        }
    }

    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast(String.valueOf(R.string.toastErrorCargarCarrito));
        carritorvAdapter.actualizarTotal();
    }
}