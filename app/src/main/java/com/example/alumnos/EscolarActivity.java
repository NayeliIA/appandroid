package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EscolarActivity extends AppCompatActivity {

    private Spinner spinnernivel;
    Button buttonok;
    ImageView atras4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolar);
        atras4 = findViewById(R.id.atras5);
        spinnernivel = findViewById(R.id.spinner4);


        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(EscolarActivity.this, PaginaunoActivity.class);
                //startActivity(i);
                finish();
            }
        });
        // Crea el adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nivel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernivel.setAdapter(adapter);

       /* ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.grado, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);*/

        // Encuentra la vista del botón
        buttonok = findViewById(R.id.buttonok);

        // Agrega el listener para el botón
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nivelSeleccionado = (String) spinnernivel.getSelectedItem();

                    /*if (nivelSeleccionado.equals("Secundaria")) {
                        Intent i = new Intent(EscolarActivity.this, Secundaria.class);

                        i.putExtra("nivelEducacion","SECUNDARIA");

                        startActivity(i);*/
                    /*} else { */

                        if (nivelSeleccionado.equals("Preparatoria")) {
                            Intent i = new Intent(EscolarActivity.this, SemestreActivity.class);

                            i.putExtra("nivelEducacion","PREPARATORIA");
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(EscolarActivity.this, "Por el momento solo se esta trabajando con preparatoria", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            //});
        }
}