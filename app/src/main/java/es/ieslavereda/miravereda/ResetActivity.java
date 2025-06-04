package es.ieslavereda.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ieslavereda.miravereda.Base.BaseActivity;

public class ResetActivity extends BaseActivity {
    private TextView tvEmail;
    private TextView tvPassword;
    private FloatingActionButton fbtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset);

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