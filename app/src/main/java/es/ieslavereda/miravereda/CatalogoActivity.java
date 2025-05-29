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
import java.util.List;

import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Model.Cliente;
import es.ieslavereda.miravereda.Model.Contenido;

public class CatalogoActivity extends BaseActivity implements View.OnClickListener {

    private List<Contenido> contenidos;
    private RecyclerView recyclerView;
    private ImageView ivLogo;
    private FloatingActionButton volver;
    private FloatingActionButton carrito;
    private Cliente cliente;

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

        // Log para comprobar lectura
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

        // Configurar vistas
        Context context = this;
        ivLogo = findViewById(R.id.ivLogo);
        contenidos = new ArrayList<>();

        volver = findViewById(R.id.Volver);
        carrito = findViewById(R.id.Carrito);

        volver.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        carrito.setOnClickListener(view -> {
            startActivity(new Intent(this, CarritoActivity.class));
        });

        recyclerView = findViewById(R.id.recycled);
        AdaptadorRV adaptadorRV = new AdaptadorRV(context, contenidos, this);
        recyclerView.setAdapter(adaptadorRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}
