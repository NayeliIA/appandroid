package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
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


public class Materias extends AppCompatActivity {


    ImageView atras1;


    ScrollView materiasScroll;

    private LinearLayout layoutMaterias;

    private FirebaseFirestore db;

    private ArrayList<Materia> materias = new ArrayList<Materia>();

    private String grado;

    private String nivelEducacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        atras1 = findViewById(R.id.atras1);

        db = FirebaseFirestore.getInstance();


         nivelEducacion = getIntent().getStringExtra("nivelEducacion");
         grado = getIntent().getStringExtra("grado");

//        nivelEducacion = "SECUNDARIA";
//        grado = "1";

        materiasScroll = findViewById(R.id.materiasScroll);

        layoutMaterias = findViewById(R.id.layoutMaterias);

        cargarMaterias(nivelEducacion, Integer.parseInt(grado) );

        final int imagenes[] = {R.drawable.filosofia, R.drawable.esp, R.drawable.biologia, R.drawable.fisica, R.drawable.geografia,R.drawable.historia, R.drawable.matematicas,
                R.drawable.logica, R.drawable.metodologia, R.drawable.quimica, R.drawable.ingles};








        atras1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






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

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recupera el índice del elemento desde la etiqueta de la vista
                int index = (int) v.getTag();

                Intent i = new Intent(Materias.this, MaterialDeAyuda.class);
                i.putExtra("materia", materias.get(index).getNombre() );
                i.putExtra("nivelEducacion", nivelEducacion);
                i.putExtra("grado", grado);
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(Materias.this).toBundle());


                // Ahora puedes utilizar el índice del elemento seleccionado
                // ...
            }
        });



        //textView.setOnClickListener(this);
        textView.setTextColor(Color.rgb(255,255,255));
        textView.setBackgroundColor(Color.rgb(15,51,65)); // Fon
        textView.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        layoutMaterias.addView(textView);

        // Agregar el TextView al Layout
        // LayoutTopics.addView(textView);
    }


}