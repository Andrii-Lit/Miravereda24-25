package es.ieslavereda.miravereda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.miravereda.Model.Posicion;

public class CatalogoActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Posicion> posicions;
    private RecyclerView recyclerView;
    private ImageView ivLogo;
    private FloatingActionButton volver;
    private FloatingActionButton carrito;
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
        Context context=this;
        ivLogo = findViewById(R.id.ivLogo);
//        ivlogo.setImageResource(R.mipmap.miravereda_logo_foreground);

        posicions = new ArrayList<>(List.of(


        ));

        volver=findViewById(R.id.Volver);
        carrito=findViewById(R.id.Carrito);
        volver.setOnClickListener((View view)->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        carrito.setOnClickListener((View view)->{
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        });

        recyclerView=findViewById(R.id.recycled);
        AdaptadorRV adaptadorRV = new AdaptadorRV(context, posicions, this);
        recyclerView.setAdapter(adaptadorRV);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Posicion posicion = posicions.get(pos);
        Toast.makeText(this, "Clic en: " + posicion.getTitulo(), Toast.LENGTH_SHORT).show();
    }


}
