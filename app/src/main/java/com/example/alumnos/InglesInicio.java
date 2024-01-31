package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InglesInicio extends AppCompatActivity {
    ImageView atras7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingles_inicio);
        atras7 = findViewById(R.id.atras7);
        atras7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InglesInicio.this, Materias.class);
                startActivity(i);
            }
        });
    }
}