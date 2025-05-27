package es.ieslavereda.miravereda.Model;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import es.ieslavereda.miravereda.R;

public class DetalleContenidoActivity extends AppCompatActivity {
    private ImageView poster;
    private TextView descripcion,nombreautor,preciovalor,notaMediaValor,titulo,anyo;
    private EditText notaCliente;
    private Button anyadirAlCarrito,valorar;
    private FloatingActionButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        Bundle extra = getIntent().getExtras();
        Contenido contenido=(Contenido) extra.getSerializable("contenido");

        poster =findViewById(R.id.Poster);
        descripcion=findViewById(R.id.Descripcion);
        nombreautor=findViewById(R.id.NombreDirector);
        anyo=findViewById(R.id.Anyo);
        preciovalor=findViewById(R.id.precioValor);
        notaMediaValor=findViewById(R.id.NotaMediaNumero);
        notaCliente=findViewById(R.id.notaCliente);
        anyadirAlCarrito=findViewById(R.id.AddCarrito);
        valorar=findViewById(R.id.Valorar);
        volver=findViewById(R.id.floatingButtonReturn);
        titulo=findViewById(R.id.Titulo);
        nombreautor=findViewById(R.id.NombreDirector);

        Picasso.get().load(contenido.getPoster_path()).into(poster);
        titulo.setText(contenido.getTitulo());
        anyo.setText(String.valueOf(contenido.getFecha_estreno()));
        descripcion.setText(contenido.getDescripcion());
        notaMediaValor.setText(String.valueOf(contenido.getPuntuacion_media()));
        nombreautor.setText(contenido.getNombre_dir());


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        anyadirAlCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
