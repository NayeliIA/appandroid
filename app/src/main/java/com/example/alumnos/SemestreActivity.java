package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SemestreActivity extends AppCompatActivity {
    private Spinner spinnerLanguages, spinner2;
    Button buttonok;
    ImageView atras4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semestre);
        atras4 = findViewById(R.id.atras5);
        spinnerLanguages = findViewById(R.id.spinner4);

        final String nivelEducacion = getIntent().getStringExtra("nivelEducacion");
       // spinner2 = findViewById(R.id.spinner2);

       /* // Recuperar el correo electrónico
        Intent intent = getIntent();
        String correoElectronico = intent.getStringExtra("CORREO_ELECTRONICO");
        if (correoElectronico != null && correoElectronico.equals("nayeli.garcia0103@gmail.com")) {
            ImageView imagenEliminar = findViewById(R.id.eliminar);
            imagenEliminar.setVisibility(View.VISIBLE); // Hace visible la imagen
        }*/

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(SemestreActivity.this, EscolarActivity.class);
               // startActivity(i);
                finish();
            }
        });
        // Crea el adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);

        /*ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.grado, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);*/

        // Encuentra la vista del botón
        buttonok = findViewById(R.id.buttonok);

        // Agrega el listener para el botón
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grado = gradoSpinner();
    if(grado.equals("1")|| grado.equals("2")|| grado.equals("3")|| grado.equals("4")||grado.equals("5") || grado.equals("6") || grado.equals("7")) {
    Intent i = new Intent(SemestreActivity.this, Materias.class);

    final String nivelEducacion = getIntent().getStringExtra("nivelEducacion");

    i.putExtra("nivelEducacion", nivelEducacion);

    i.putExtra("grado", gradoSpinner());
    startActivity(i);
}else{
        Toast.makeText(SemestreActivity.this, "Por el momento solo se esta trabajando con 5to semestre", Toast.LENGTH_SHORT).show();
    }
            }
        });
    }


    private String gradoSpinner() {

        final String opcion = spinnerLanguages.getSelectedItem().toString();

        if (opcion.startsWith("1")) {
            return "1";
        } else if (opcion.startsWith("2")) {
            return "2";
        } else if (opcion.startsWith("3")) {
            return "3";
        } else if (opcion.startsWith("4")) {
            return "4";
        } else if (opcion.startsWith("5")) {
            return "5";
        } else if (opcion.startsWith("6")) {
            return "6";
        } else if (opcion.startsWith("7")) {
            return "7";
        }
            return "";

    }
}