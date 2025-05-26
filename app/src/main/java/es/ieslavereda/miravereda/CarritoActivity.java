package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Model.Contenido;

public class CarritoActivity extends AppCompatActivity{
    private List<Contenido> contenidos_anyadidos;
    private FloatingActionButton carrito_backButton;
    private Button carrito_comprarButton;
    private RecyclerView carrito_recyclerView;
    private TextView carrito_totalTV, carrito_precioTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);
        //pillar el endpoint que devuelva la lista
        try {
            contenidos_anyadidos = new ArrayList<>(
                    Connector.getConector().getAsList(Contenido.class, "")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //comentar el recycler en caso de no funcionar
        CarritoAdaptadorRV adaptadorRV = new CarritoAdaptadorRV(this, contenidos_anyadidos);
        carrito_recyclerView.setAdapter(adaptadorRV);
        carrito_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carrito_backButton = findViewById(R.id.carrito_backButton);
        carrito_comprarButton = findViewById(R.id.carrito_comprarButton);
        carrito_recyclerView = findViewById(R.id.carrito_recyclerView);
        carrito_totalTV = findViewById(R.id.carrito_totalTV);
        carrito_precioTV = findViewById(R.id.carrito_precioTV);

        carrito_precioTV.setText(""
                // implementar la llamada a la tabla carrito
        );

        carrito_backButton.setOnClickListener((View view)->{
            finish();
        });
    }


}
