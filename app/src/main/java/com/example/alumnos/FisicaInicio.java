package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FisicaInicio extends AppCompatActivity {

    ImageView atras3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica_inicio);
        atras3 = findViewById(R.id.atras3);
        atras3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FisicaInicio.this, Materias.class);
                startActivity(i);
            }
        });
    }
}