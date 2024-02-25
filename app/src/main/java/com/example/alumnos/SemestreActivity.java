package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
                String semestreSeleccionado = (String) spinnerLanguages.getSelectedItem();
                if(semestreSeleccionado.equals("5° Semestre")) {
                    Intent i = new Intent(SemestreActivity.this, Materias.class);
                    startActivity(i);
                }
            }
        });
    }
}