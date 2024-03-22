package com.example.alumnos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnos.modelos.MaterialDeAyuda;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminCrearPropuesta extends AppCompatActivity {

    private EditText editTextTema;
    private EditText editTextLink;

    ImageView atras5;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    final String[] opciones = {"SELECCIONA LA MATERIA: ", "FILOSOFIA", "HISTORIA","GEOGRAFIA","INGLES","MATEMATICAS"};

    final String[] opcionesNivelEstudio = {"SECUNDARIA","PREPARATORIA"};



    private Spinner materias;

    private Spinner nivelEducacionSpinner;

    private Spinner gradoSpiner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_crear_propuesta);

        editTextTema = findViewById(R.id.nombreMateria);
        editTextLink = findViewById(R.id.editTextLink);

        atras5 = findViewById(R.id.atras5);





        Button buttonSubir = findViewById(R.id.button7);




        this.materias = findViewById(R.id.listaMateriasAdmin);

        nivelEducacionSpinner = findViewById(R.id.nivelEducacionSpinner);
        gradoSpiner = findViewById(R.id.gradoSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        materias.setAdapter(adapter);


        ArrayAdapter<String> adapterNivelEstudio = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesNivelEstudio);
        adapterNivelEstudio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nivelEducacionSpinner.setAdapter(adapterNivelEstudio);




        nivelEducacionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                final String[] grados = nivelEducacionSpinner.getSelectedItem().toString().equals("SECUNDARIA") ? new String[]{"1","2","3"} : new String[]{"1","2","3","4","5","6","7"};

                ArrayAdapter<String> adapterGrado = new ArrayAdapter<>(AdminCrearPropuesta.this, android.R.layout.simple_spinner_item, grados);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                gradoSpiner.setAdapter(adapterGrado);

                // Tu código aquí se ejecutará cuando se seleccione una opción del spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tu código aquí se ejecutará cuando no se seleccione ninguna opción del spinner
            }
        });



        buttonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tema = editTextTema.getText().toString();
                String link = editTextLink.getText().toString();

                final String nivelEducacion = nivelEducacionSpinner.getSelectedItem().toString();

                final int grado = Integer.parseInt(gradoSpiner.getSelectedItem().toString() ) ;

                db.collection("material").add(new MaterialDeAyuda(link,tema,materias.getSelectedItem().toString(), nivelEducacion, grado).registrarMap())
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                               final String id = documentReference.getId();

                                // El documento se registró correctamente
                                //Log.d("Firestore", "Documento registrado con ID: " + documentReference.getId());
                                System.out.println(id);

                                //Retornamos que se registro correctamante para recargar la informacion
                                setResult(Activity.RESULT_OK);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error al registrar el documento
                                //Log.e("Firestore", "Error al registrar el documento", e);
                            }
                        });



            }
        });

        atras5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);

                finish();
            }
        });
    }



}