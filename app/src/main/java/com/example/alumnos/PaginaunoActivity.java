package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PaginaunoActivity extends AppCompatActivity {
    Button btnMaterias;
    ImageView atras8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginauno);
        btnMaterias = findViewById(R.id.btnMaterias);
        btnMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaginaunoActivity.this, SemestreActivity.class);
                startActivity(i);
            }
        });
        atras8 = findViewById(R.id.atras8);
        atras8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaginaunoActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

    }
}