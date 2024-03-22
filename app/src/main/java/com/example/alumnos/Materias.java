package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.alumnos.modelos.Materia;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class Materias extends AppCompatActivity implements View.OnClickListener{


    ImageView atras1;


    ScrollView materiasScroll;

    private LinearLayout layoutMaterias;

    private FirebaseFirestore db;

    private ArrayList<Materia> materias = new ArrayList<Materia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        atras1 = findViewById(R.id.atras1);

        db = FirebaseFirestore.getInstance();

//        filosofia = findViewById(R.id.filosofia);

        final String nivelEducacion = getIntent().getStringExtra("nivelEducacion");
        final String grado = getIntent().getStringExtra("grado");

        materiasScroll = findViewById(R.id.materiasScroll);

        layoutMaterias = findViewById(R.id.layoutMaterias);

        cargarMaterias("SECUNDARIA",1);

        final int imagenes[] = {R.drawable.filosofia, R.drawable.esp, R.drawable.biologia, R.drawable.fisica, R.drawable.geografia,R.drawable.historia, R.drawable.matematicas,
                R.drawable.logica, R.drawable.metodologia, R.drawable.quimica, R.drawable.ingles};


//        layoutMaterias.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Obtiene el índice del elemento seleccionado dentro del LinearLayout
//
//               int nmms = view.getId();
//               //String quePedo = view.getTag().toString();
//
//
//                int perro = materiasScroll.indexOfChild(view);
//
//                int index = layoutMaterias.indexOfChild(view);
//
//                View nmm = layoutMaterias.getFocusedChild();
//
//                int intento = layoutMaterias.indexOfChild(nmm);
//
//                if (view instanceof TextView) {
//                    String tag = (String) view.getTag();
//                    // Tu código aquí
//                }
//
//            }
//        });





        atras1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




//        filosofia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(Materias.this, MaterialDeAyuda.class);
//                i.putExtra("materia", "FILOSOFIA");
//                i.putExtra("nivelEducacion", nivelEducacion);
//                i.putExtra("grado", grado);
//                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(Materias.this).toBundle());
//
//
//            }
//
//
//        });

    }



    private void cargarMaterias(String nivelEducacion, int grado){
        db.collection("materias")
                //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada
                .whereEqualTo("nivelEducacion",nivelEducacion)
                .whereEqualTo("grado", grado)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        int index = 0;
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            materias.add( new Materia(document.getId(), document.getData() ) );

//                            TextView titulo = new TextView(MaterialDeAyuda.this);
//                            titulo.setText(materialDeAyuda.get(index).getTema());

                            //titulo.setId(index);
                            // Agrega el RadioButton al grupo
                            //materialesScroll.addView(titulo, index);
                            addMateria(materias.get(index).getNombre(), materias.get(index).getIdImagen(), index );



                            index++;
                        }
                    } else {
                        // Manejar errores
                        //Log.d(TAG, "Error getting documents: ", task.getException());
                        task.getException().printStackTrace();
                    }

                });
    }

    private void addMateria(String nombre, int idImagen, int index) {


        // Crear la imagen
        Drawable imgDrawable = getResources().getDrawable(idImagen);

        imgDrawable.setBounds(0, 0, 100, 100);

        // Crear un SpannableStringBuilder y añadir el texto y la imagen
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(nombre + "      ");
        builder.setSpan(new ImageSpan(imgDrawable, ImageSpan.ALIGN_BOTTOM), builder.length() - 1, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        AlignmentSpan alignmentSpan = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);

        builder.setSpan(alignmentSpan,0, builder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // Crear un TextView y establecer el SpannableStringBuilder como su texto
        TextView textView = new TextView(this);
        textView.setText(builder);
        textView.setTag(index);
        //textView.setId(index);



        //textView.setOnClickListener(this);
        textView.setTextColor(Color.rgb(15,51,65));
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        layoutMaterias.addView(textView);

        // Agregar el TextView al Layout
        // LayoutTopics.addView(textView);
    }


    @Override
    public void onClick(View view) {

       int indice = layoutMaterias.indexOfChild(view);

    }
}