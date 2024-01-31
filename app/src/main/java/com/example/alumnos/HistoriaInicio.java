package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class HistoriaInicio extends AppCompatActivity {

    ImageView atras4;
    Spinner spinnerBloque1;
    private Spinner spinnerLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_inicio);

        atras4 = findViewById(R.id.atras4);
        spinnerBloque1 = findViewById(R.id.spinnerbloque1);

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoriaInicio.this, Materias.class);
                startActivity(i);
            }
        });

        // Encuentra la vista Spinner
        spinnerLanguages = findViewById(R.id.spinnerbloque1);

        // Crea el adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bloque1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);

        // Agrega el escuchador de eventos para el spinner
        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temaSeleccionado = (String) parent.getItemAtPosition(position);
                if (temaSeleccionado.equals("Primera Guerra Mundial")) {
                    Intent i = new Intent(HistoriaInicio.this, GuerramundialActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se ha seleccionado ningún tema
            }
        });
    }
}
