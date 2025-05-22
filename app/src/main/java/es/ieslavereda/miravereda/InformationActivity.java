package es.ieslavereda.miravereda;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InformationActivity extends AppCompatActivity {

    private FloatingActionButton volver;
    private ImageView poster;
    private TextView titulo,nota,notaMedia,sinopsisTitulo,sinopsis, author;
    private RatingBar puntuacion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.catalogo_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvUsername), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
