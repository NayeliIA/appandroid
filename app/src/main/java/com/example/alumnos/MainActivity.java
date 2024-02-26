package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alumnos.modelos.MaterialDeAyuda;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, InicioActivity.class);
                //Intent i = new Intent(MainActivity.this, Materias.class);

                //Intent i = new Intent(MainActivity.this, InicioActivity.class);
               // Intent i = new Intent(MainActivity.this, InicioActivity.class);

                startActivity(i);

                finish();
            }
        });

        FirebaseApp.initializeApp(this);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        for(int a=0 ; a< 21; a++){
//            db.collection("propuestas").add(new MaterialDeAyuda("https://google.com","Prueba for "+a,"HISTORIA").registrarMap())
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//
//                            final String id = documentReference.getId();
//
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Error al registrar el documento
//                            //Log.e("Firestore", "Error al registrar el documento", e);
//                        }
//                    });
//        }


    }
}