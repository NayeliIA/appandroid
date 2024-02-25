package com.example.alumnos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    final String[] opciones = {"SELECCIONA LA MATERIA: ", "FILOSOFIA", "HISTORIA","GEOGRAFIA","INGLES","MATEMATICAS",};

    private Spinner materias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_crear_propuesta);

        editTextTema = findViewById(R.id.editTextTema);
        editTextLink = findViewById(R.id.editTextLink);

        atras5 = findViewById(R.id.atras5);





        Button buttonSubir = findViewById(R.id.button7);




        this.materias = findViewById(R.id.listaMateriasAdmin);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        materias.setAdapter(adapter);




        buttonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tema = editTextTema.getText().toString();
                String link = editTextLink.getText().toString();



                db.collection("material").add(new MaterialDeAyuda(link,tema,materias.getSelectedItem().toString()).registrarMap())
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