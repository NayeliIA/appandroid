package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.alumnos.modelos.Materia;
import com.example.alumnos.modelos.MaterialDeAyuda;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminMaterias extends AppCompatActivity {



    private EditText nombreMateria ;
    final String[] opcionesNivelEstudio = {"SECUNDARIA","PREPARATORIA"};

    private ImageView btnAtras;

    private Spinner nivelEducacionSpinner;

    private Spinner gradoSpiner;

    private ViewFlipper imageFlipper;

    private Button btnIzquierda;

    private Button btnDerecha;

    private Button btnRegistrar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_materias);

        nombreMateria = findViewById(R.id.nombreMateria);

        btnAtras = findViewById(R.id.atras5);

        nivelEducacionSpinner = findViewById(R.id.nivelEducacionSpinner);
        gradoSpiner = findViewById(R.id.gradoSpinner);

        imageFlipper = findViewById(R.id.imageFlipper);

        btnIzquierda = findViewById(R.id.btnIzquierda);
        btnDerecha = findViewById(R.id.btnDerecha);

        btnRegistrar = findViewById(R.id.btnRegistrar);


        ArrayAdapter<String> adapterNivelEstudio = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesNivelEstudio);
        adapterNivelEstudio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nivelEducacionSpinner.setAdapter(adapterNivelEstudio);


       final int imagenes[] = {R.drawable.filosofia, R.drawable.esp, R.drawable.biologia, R.drawable.fisica, R.drawable.geografia,R.drawable.historia, R.drawable.matematicas,
        R.drawable.logica, R.drawable.metodologia, R.drawable.quimica, R.drawable.ingles};


        for(int a = 0; a< imagenes.length ; a++){


            ImageView img = new ImageView(this);
            img.setBackgroundResource(imagenes[a]);


            imageFlipper.addView(img,a);
            imageFlipper.setAutoStart(false);

            imageFlipper.setInAnimation(this, android.R.anim.slide_out_right);
            imageFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        }



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




              final String nivelEducacion = nivelEducacionSpinner.getSelectedItem().toString();

              final int grado = Integer.parseInt(gradoSpiner.getSelectedItem().toString() ) ;

                final String nombre = nombreMateria.getText().toString();

                final int index = imageFlipper.getDisplayedChild();



                db.collection("materias").add(new Materia(nombre, imagenes[index] , nivelEducacion, grado ).registrarMap())
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                final String id = documentReference.getId();
                                Toast.makeText(AdminMaterias.this, "Se agregó la materia correctamente", Toast.LENGTH_SHORT).show();
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

        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageFlipper.showPrevious();

            }
        });

        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageFlipper.showNext();

            }
        });



        nivelEducacionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                final String[] grados = nivelEducacionSpinner.getSelectedItem().toString().equals("SECUNDARIA") ? new String[]{"1","2","3"} : new String[]{"1","2","3","4","5","6","7"};

                ArrayAdapter<String> adapterGrado = new ArrayAdapter<>(AdminMaterias.this, android.R.layout.simple_spinner_item, grados);
                adapterGrado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                gradoSpiner.setAdapter(adapterGrado);

                // Tu código aquí se ejecutará cuando se seleccione una opción del spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tu código aquí se ejecutará cuando no se seleccione ninguna opción del spinner
            }
        });


        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);

                finish();
            }
        });
    }


}