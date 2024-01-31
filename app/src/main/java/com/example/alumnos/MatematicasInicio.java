package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MatematicasInicio extends AppCompatActivity {

    ImageView atras6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematicas_inicio);
        atras6 = findViewById(R.id.atras4);
        atras6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatematicasInicio.this, Materias.class);
                startActivity(i);
            }
        });
    }
}