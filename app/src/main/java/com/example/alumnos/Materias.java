package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Materias extends AppCompatActivity {
ImageView ingles;
ImageView historia;
ImageView filosofia;
ImageView matematicas;
ImageView geografia;


ImageView atras1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        atras1 = findViewById(R.id.atras1);
        atras1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, PaginaunoActivity.class);
                startActivity(i);
            }
        });
      /*  ingles = findViewById(R.id.ingles);
        ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, InglesInicio.class);
                startActivity(i);
            }
        });*/
        historia= findViewById(R.id.historia);
        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, MaterialDeAyuda.class);
                i.putExtra("materia","HISTORIA");
                startActivity(i);
            }
        });
        matematicas = findViewById(R.id.matematicas);
        matematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, MaterialDeAyuda.class);
                i.putExtra("materia","MATEMATICAS");
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(Materias.this).toBundle());
            }
        });
        filosofia = findViewById(R.id.filosofia);
        filosofia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this,MaterialDeAyuda.class);
                i.putExtra("materia","FILOSOFIA");
                startActivity(i);
            }
        });
        geografia = findViewById(R.id.geografia);
        geografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this,MaterialDeAyuda.class);
                i.putExtra("materia","GEOGRAFIA");
                startActivity(i);
            }
        });

        ingles = findViewById(R.id.ingles);
        ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this,MaterialDeAyuda.class);
                i.putExtra("materia","INGLES");
                startActivity(i);
            }
        });
       /* esp = findViewById(R.id.esp);
        esp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, EspanolInicio.class);
                startActivity(i);
            }
        });
        fisica = findViewById(R.id.fisica);
        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, FisicaInicio.class);
                startActivity(i);
            }
        });
        matematicas = findViewById(R.id.matematicas);
        matematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, MatematicasInicio.class);
                startActivity(i);
            }
        });
        quimica = findViewById(R.id.quimica);
        quimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Materias.this, QuimicaInicio.class);
                startActivity(i);
            }
        });*/
    }
}