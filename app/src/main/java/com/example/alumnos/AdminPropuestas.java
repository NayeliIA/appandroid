package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.alumnos.modelos.MaterialDeAyuda;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminPropuestas extends AppCompatActivity {

   final String[] opciones = {"SELECCIONA LA MATERIA", "MATEMATICAS", "GEOGRAFIA", "HISTORIA", "INGLES", "FILOSOFIA"};

    private Spinner materias;
    private Button btnBuscar;

    private Button btnAceptar;

    private Button btnRechazar;

    //private LinearLayout container;

    private ArrayList<MaterialDeAyuda> propuestas = new ArrayList<MaterialDeAyuda>();

    private RadioGroup radioGroup ;

    FirebaseFirestore db;

    private int materialSeleccionadoIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_propuestas);

        db = FirebaseFirestore.getInstance();

        this.materias = findViewById(R.id.listaMaterias);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        materias.setAdapter(adapter);

        this.btnBuscar = findViewById(R.id.btnBuscar);
        this.btnAceptar = findViewById(R.id.btnAceptar);
        this.btnRechazar = findViewById(R.id.btnRechazar);

        //container = findViewById(R.id.linearLayout);

        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Manejar el evento de selección
            RadioButton radioButton = findViewById(checkedId);
            if (radioButton != null) {
                // Acciones a realizar cuando se selecciona un RadioButton
                String textoSeleccionado = radioButton.getText().toString();

                System.out.println("Se selecciono "+ textoSeleccionado);

                final int seleccionado = group.indexOfChild(radioButton);

                materialSeleccionadoIndex = seleccionado;

                System.out.println("Se selecciono "+ seleccionado);

                openLink(propuestas.get(seleccionado).getLink());

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String materia = (String) materias.getSelectedItem();

                if(materia.equals("SELECCIONA LA MATERIA")) {

                    return;
                }

                cargarPropuestasMateria(materia);





            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String materia = (String) materias.getSelectedItem();

                if(materia.equals("SELECCIONA LA MATERIA")) {

                    return;
                }

                db.collection("material").add( propuestas.get(materialSeleccionadoIndex).registrarMap() )
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                db.collection("propuestas").document( propuestas.get(materialSeleccionadoIndex).getId() )
                                        .delete()
                                        .addOnSuccessListener(aVoid -> {

                                            System.out.println("Se elimino correctamente");

                                            //radioGroup.removeView( radioGroup.getChildAt(materialSeleccionadoIndex) );
                                            propuestas.clear();
                                            radioGroup.removeAllViews();


                                            cargarPropuestasMateria(materia);




                                        })
                                        .addOnFailureListener(e -> {

                                        });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error al registrar el documento
                                //Log.e("Firestore", "Error al registrar el documento", e);

                            }
                        });

                cargarPropuestasMateria(materia);


            }
        });

        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String materia = (String) materias.getSelectedItem();

                if(materia.equals("SELECCIONA LA MATERIA")) {

                    return;
                }

                db.collection("propuestas").document( propuestas.get(materialSeleccionadoIndex).getId() )
                        .delete()
                        .addOnSuccessListener(aVoid -> {

                            System.out.println("Se elimino correctamente");

                            //radioGroup.removeView( radioGroup.getChildAt(materialSeleccionadoIndex) );
                            propuestas.clear();
                            radioGroup.removeAllViews();

                            cargarPropuestasMateria(materia);


                        })
                        .addOnFailureListener(e -> {

                        });




            }
        });

    }



    private void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);

    }

    private void cargarPropuestasMateria(String materia){


        db.collection("propuestas")
                //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada
                .whereEqualTo("materia",materia)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        int index = 0 ;
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            propuestas.add( new MaterialDeAyuda(document.getId(), document.getData()) );

                            RadioButton radioButton = new RadioButton(AdminPropuestas.this);
                            radioButton.setText(propuestas.get(index).getTema());

                            // Agrega el RadioButton al grupo
                            radioGroup.addView(radioButton);

                            index++;
                        }
                    } else {
                        // Manejar errores
                        //Log.d(TAG, "Error getting documents: ", task.getException());
                        task.getException().printStackTrace();
                    }

                });



    }



}