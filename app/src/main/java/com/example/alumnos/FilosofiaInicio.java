package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FilosofiaInicio extends AppCompatActivity {

    ImageView atras2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filosofia_inicio);
        atras2 = findViewById(R.id.atras4);
        atras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FilosofiaInicio.this, Materias.class);
                startActivity(i);
            }
        });
    }
}