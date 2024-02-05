package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class Secundaria extends AppCompatActivity {

    private Spinner spinnergrado;
    Button buttonok;
    ImageView atras4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        atras4 = findViewById(R.id.atras4);
        spinnergrado = findViewById(R.id.spinner2);

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
                Intent i = new Intent(Secundaria.this, EscolarActivity.class);
                startActivity(i);
            }
        });
        // Crea el adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergrado.setAdapter(adapter);

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
                String gradoSeleccionado = (String) spinnergrado.getSelectedItem();
                if(gradoSeleccionado.equals("5° S")) {
                    Intent i = new Intent(Secundaria.this, Materias.class);
                    //startActivity(i);
                }
            }
        });
    }
}