package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Cliente;
import es.ieslavereda.miravereda.Model.Contenido;

public class CatalogoActivity extends BaseActivity implements View.OnClickListener, CallInterface<Collection<Contenido>> {

    private List<Contenido> contenidos = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton volver;
    private FloatingActionButton carrito;
    private Cliente cliente;
    private AdaptadorRV adaptadorRV;

    private ActivityResultLauncher<Intent> activityResultLauncher;

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
        setContentView(R.layout.catalogo_layout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK || result.getResultCode() == RESULT_CANCELED) {
                        executeCall(this);
                        showProgress();

                    }
                }
        );

        SharedPreferences prefs = getSharedPreferences("cliente", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String contrasenya = prefs.getString("contrasenya", null);
        int cliente_id = prefs.getInt("clienteId", -1);

        if (email != null && contrasenya != null && cliente_id != -1) {
            cliente = new Cliente(email, contrasenya);
            cliente.setId(cliente_id);
        } else {
            Log.d("CATALOGO", "No hay sesión: vuelvo a MainActivity");
            Toast.makeText(this, R.string.toastSesionNoIniciada, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        Log.d("PREFS", "Leído email: " + email);
        Log.d("PREFS", "Leído contrasenya: " + contrasenya);

        volver = findViewById(R.id.Volver);
        carrito = findViewById(R.id.Carrito);
        recyclerView = findViewById(R.id.recycled);

        volver.setOnClickListener((View view)->{finish();});
        carrito.setOnClickListener(view -> {
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        });

        adaptadorRV = new AdaptadorRV(this, contenidos, this);
        recyclerView.setAdapter(adaptadorRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        executeCall(this);
        showProgress();
    }

    /**
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Contenido contenido = contenidos.get(pos);
        Intent intent = new Intent(this, DetalleContenidoActivity.class);
        intent.putExtra("contenido", contenido);
        // Usa el launcher para el detalle
        activityResultLauncher.launch(intent);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Contenido> doInBackground() throws Exception {
        return (contenidos != null) ?
                Connector.getConector().getAsList(Contenido.class, "contenido/")
                : List.of();
    }

    /**
     *
     * @param contenido
     */
    @Override
    public void doInUI(Collection<Contenido> contenido) {
        hideProgress();
        if (contenido != null) {
            contenidos.clear();
            contenidos.addAll(contenido);
            adaptadorRV.notifyDataSetChanged();
        }
    }

    /**
     *
     * @param context
     * @param e
     */
    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        Toast.makeText(context, R.string.toastErrorCargandoContenidos, Toast.LENGTH_LONG).show();
    }
}