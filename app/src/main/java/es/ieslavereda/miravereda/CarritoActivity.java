package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.CarritoAdaptadorRV;
import es.ieslavereda.miravereda.Model.Cliente;
import es.ieslavereda.miravereda.Model.Contenido;

public class CarritoActivity extends BaseActivity implements CallInterface<List<Contenido>> {

    private List<Contenido> contenidos_anyadidos = new ArrayList<>();
    private FloatingActionButton carrito_backButton;
    private Button carrito_comprarButton;
    private RecyclerView carrito_recyclerView;
    private CarritoAdaptadorRV carritorvAdapter;
    private TextView carrito_precioTV;
    private Cliente cliente;
    private int clienteId = -1;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        carrito_backButton = findViewById(R.id.carrito_backButton);
        carrito_comprarButton = findViewById(R.id.carrito_comprarButton);
        carrito_recyclerView = findViewById(R.id.carrito_recyclerView);
        carrito_precioTV = findViewById(R.id.carrito_precioTV);

        for (Contenido contenido : contenidos_anyadidos){
            total += contenido.getPrecio();
        }
        carrito_precioTV.setText(String.valueOf(total));
        // Leer clienteId desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("cliente", MODE_PRIVATE);
        clienteId = prefs.getInt("clienteId", -1);

        if (clienteId == -1) {
            showToast("No se encontró el ID del cliente. Por favor inicia sesión.");
            finish();
            return;
        }

        String email = prefs.getString("email", null);
        String contrasenya = prefs.getString("contrasenya", null);
        cliente = new Cliente(email, contrasenya);

        // Configurar botón volver
        carrito_backButton.setOnClickListener(v -> finish());

        // Configurar RecyclerView y adaptador
        carritorvAdapter = new CarritoAdaptadorRV(this, contenidos_anyadidos);
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
                    showToast("Compra realizada con éxito");

                    // Recargar el carrito para reflejar los cambios reales desde el backend
                    executeCall(CarritoActivity.this);
                }


                @Override
                public void doInError(Context context, Exception e) {
                    hideProgress();
                    showToast("Error en la compra: " + e.getMessage());
                }
            });
        });
    }

    @Override
    public List<Contenido> doInBackground() throws Exception {
        try {
            return Connector.getConector().getAsList(Contenido.class, "carrito/" + clienteId);
        } catch (Exception e) {
            Log.e("Carrito", "Error al cargar lista contenido", e);
            return null;
        }
    }

    @Override
    public void doInUI(List<Contenido> data) {
        hideProgress();
        contenidos_anyadidos.clear();
        if (data != null) {
            contenidos_anyadidos.addAll(data);
            carritorvAdapter.notifyDataSetChanged();

    } else {
            showToast("No se pudieron cargar los contenidos del carrito.");
        }
    }

    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        showToast("Error cargando carrito: " + e.getMessage());
    }
}
