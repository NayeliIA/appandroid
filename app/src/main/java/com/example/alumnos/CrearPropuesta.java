package com.example.alumnos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.alumnos.modelos.MaterialDeAyuda;

public class CrearPropuesta extends AppCompatActivity {

    private EditText editTextTema;
    private EditText editTextLink;

    ImageView atras4;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_propuesta);

        editTextTema = findViewById(R.id.editTextTema);
        editTextLink = findViewById(R.id.editTextLink);

        atras4 = findViewById(R.id.atras4);

        final String materia = getIntent().getStringExtra("materia");



        Button buttonSubir = findViewById(R.id.button7);

        buttonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tema = editTextTema.getText().toString();
                String link = editTextLink.getText().toString();



                db.collection("propuestas").add(new MaterialDeAyuda(link,tema,materia).registrarMap())
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

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);

                finish();
            }
        });
    }



}