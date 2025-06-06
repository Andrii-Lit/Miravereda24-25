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

/**
 * Actividad que muestra un catálogo de contenidos disponibles.
 * Permite navegar a detalles del contenido y gestionar carrito de compras.
 * Implementa {@link CallInterface} para manejar llamadas asíncronas a la API.
 */
public class CatalogoActivity extends BaseActivity implements View.OnClickListener, CallInterface<Collection<Contenido>> {

    private List<Contenido> contenidos = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton volver;
    private FloatingActionButton carrito;
    private Cliente cliente;
    private AdaptadorRV adaptadorRV;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * Método llamado al crear la actividad.
     * Inicializa vistas, recupera sesión, configura RecyclerView y lanza carga de contenidos.
     *
     * @param savedInstanceState Bundle con el estado previo guardado (puede ser null).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.catalogo_layout);

        // Ajuste para respetar áreas del sistema (barras, notch, etc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Registrar launcher para resultados de actividades (detalles contenido)
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK || result.getResultCode() == RESULT_CANCELED) {
                        executeCall(this);
                        showProgress();
                    }
                }
        );

        // Recuperar sesión cliente de SharedPreferences
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

        // Inicializar vistas y listeners
        volver = findViewById(R.id.Volver);
        carrito = findViewById(R.id.Carrito);
        recyclerView = findViewById(R.id.recycled);

        volver.setOnClickListener((View view) -> finish());
        carrito.setOnClickListener(view -> {
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        });

        // Configurar RecyclerView con adaptador y layout manager
        adaptadorRV = new AdaptadorRV(this, contenidos, this);
        recyclerView.setAdapter(adaptadorRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar contenidos desde API
        executeCall(this);
        showProgress();
    }

    /**
     * Manejador de clics sobre elementos del RecyclerView.
     * Abre la actividad de detalle del contenido seleccionado.
     *
     * @param v Vista clicada.
     */
    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Contenido contenido = contenidos.get(pos);
        Intent intent = new Intent(this, DetalleContenidoActivity.class);
        intent.putExtra("contenido", contenido);
        activityResultLauncher.launch(intent);
    }

    /**
     * Lógica para ejecutar en segundo plano y obtener la lista de contenidos desde la API.
     *
     * @return Colección de contenidos recibidos.
     * @throws Exception Si hay error en la llamada.
     */
    @Override
    public Collection<Contenido> doInBackground() throws Exception {
        return (contenidos != null) ?
                Connector.getConector().getAsList(Contenido.class, "contenido/")
                : List.of();
    }

    /**
     * Actualiza la UI con la lista de contenidos obtenida.
     *
     * @param contenido Colección de contenidos recibida.
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
     * Muestra mensaje de error en la UI si la carga de contenidos falla.
     *
     * @param context Contexto para mostrar el Toast.
     * @param e       Excepción ocurrida.
     */
    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        Toast.makeText(context, R.string.toastErrorCargandoContenidos, Toast.LENGTH_LONG).show();
    }
}