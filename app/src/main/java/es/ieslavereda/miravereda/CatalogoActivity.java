package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    private ImageView ivLogo;
    private FloatingActionButton volver;
    private FloatingActionButton carrito;
    private Cliente cliente;
    private AdaptadorRV adaptadorRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.catalogo_layout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvUsername), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Leer datos de sesión
        SharedPreferences prefs = getSharedPreferences("cliente", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String contrasenya = prefs.getString("contrasenya", null);

        Log.d("PREFS", "Leído email: " + email);
        Log.d("PREFS", "Leído contrasenya: " + contrasenya);

        if (email != null && contrasenya != null) {
            cliente = new Cliente(email, contrasenya);
        } else {
            Toast.makeText(this, "No hay sesión iniciada", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        ivLogo = findViewById(R.id.ivLogo);
        volver = findViewById(R.id.Volver);
        carrito = findViewById(R.id.Carrito);
        recyclerView = findViewById(R.id.recycled);

        volver.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
        carrito.setOnClickListener(view -> startActivity(new Intent(this, CarritoActivity.class)));

        // Inicializa adaptador vacío, lo actualizaremos al recibir datos
        adaptadorRV = new AdaptadorRV(this, contenidos, this);
        recyclerView.setAdapter(adaptadorRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ejecuta la carga de datos en background
        executeCall(this);
        showProgress();
    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Contenido contenido = contenidos.get(pos);
        Intent intent = new Intent(this, DetalleContenidoActivity.class);
        intent.putExtra("contenido", contenido);
        Toast.makeText(this, "Clic en: " + contenido.getTitulo(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public Collection<Contenido> doInBackground() throws Exception {

        return (contenidos!=null)?
                Connector.getConector().getAsList(Contenido.class, "contenido/")
                :List.of();
    }

    @Override
    public void doInUI(Collection<Contenido> contenido) {
        hideProgress();
        if (contenido != null) {
            contenidos.clear();
            contenidos.addAll(contenido);
            adaptadorRV.notifyDataSetChanged();
        }
    }

    @Override
    public void doInError(Context context, Exception e) {
        hideProgress();
        Toast.makeText(context, "Error cargando contenidos: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
