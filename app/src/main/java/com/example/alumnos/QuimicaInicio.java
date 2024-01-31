package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class QuimicaInicio extends AppCompatActivity {

    ImageView atras5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quimica_inicio);
        atras5 = findViewById(R.id.atras5);
        atras5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuimicaInicio.this, Materias.class);
                startActivity(i);
            }
        });
    }
}