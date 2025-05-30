package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import es.ieslavereda.miravereda.Base.BaseActivity;

public class UserInfoActivity extends BaseActivity {
    private TextView tvEmail;
    private TextView tvPassword;
    private FloatingActionButton fbtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);

        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        fbtBack = findViewById(R.id.fbtBack);
        Intent intent = getIntent();

        String email = intent.getStringExtra("email");
        String contrasenya = intent.getStringExtra("contrasenya");
        tvEmail.setText(email);
        tvPassword.setText(contrasenya);
        fbtBack.setOnClickListener(v -> {
            finish();
        });
    }
}