package es.ieslavereda.miravereda;

import android.os.Bundle;
import android.widget.Button;

import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

import es.ieslavereda.miravereda.API.Connector;
import es.ieslavereda.miravereda.Base.BaseActivity;
import es.ieslavereda.miravereda.Base.CallInterface;
import es.ieslavereda.miravereda.Model.Contenido;

public class CarritoActivity extends BaseActivity implements CallInterface<List<Contenido>> {

    private List<Contenido> contenidosAnyadidos;
    private RecyclerView carritoRecyclerView;
    private Button carritoComprarButton;
    private TextView carritoTotalTV, carritoPrecioTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        carritoRecyclerView = findViewById(R.id.carrito_recyclerView);
        carritoComprarButton = findViewById(R.id.carrito_comprarButton);
        carritoTotalTV = findViewById(R.id.carrito_totalTV);
        carritoPrecioTV = findViewById(R.id.carrito_precioTV);

        carritoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        carritoComprarButton.setOnClickListener(v -> {
            // Aquí puedes implementar la acción de compra, ej. enviar pedido al servidor
        });

        executeCall(this);
        showProgress();
    }

    @Override
    public List<Contenido> doInBackground() throws Exception {
        Collection<Contenido> contenidos = Connector.getConector().getAsList(Contenido.class, "contenido/");
        return (contenidos != null) ? List.copyOf(contenidos) : List.of();
    }

    @Override
    public void doInUI(List<Contenido> data) {
        hideProgress();
        contenidosAnyadidos = data;

        if (carritoRecyclerView.getAdapter() == null) {
            carritoRecyclerView.setAdapter(new CarritoAdaptadorRV(this, contenidosAnyadidos));
        } else {
            carritoRecyclerView.getAdapter().notifyDataSetChanged();
        }


    }
}

