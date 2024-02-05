package com.example.alumnos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Set;

public class MaterialDeAyuda extends AppCompatActivity implements View.OnClickListener {



    ImageView atras4;

    Button agregar;



    private ArrayList<com.example.alumnos.modelos.MaterialDeAyuda> materialDeAyuda = new ArrayList<com.example.alumnos.modelos.MaterialDeAyuda>();


    private Set<String> linkSet;
    private final static String YOUTUBE_URL = "https://www.youtube.com/playlist?list=PLeySRPnY35dHyUzy-YVDD9ZllhtXfcQ4_&si=mD36rtY4espFZ85r";
    private final static String YOUTUBE1_URL = "https://www.youtube.com/playlist?list=PLlQ8HaUPX-NJFJf2LTRQ1qtI-2J8ocW8k&si=lCn3-_3kT0l8gqTl";
    private final static String YOUTUBE2_URL = "https://www.youtube.com/playlist?list=PLeySRPnY35dE48tg5rvN5UyO8pxXNv61L";

    private static final int REQUEST_CODE = 1;

    private FirebaseFirestore db;

    private LinearLayout materialesScroll;
    private ScrollView scrollview;

    private String materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_de_ayuda);

        db = FirebaseFirestore.getInstance();


        materia = getIntent().getStringExtra("materia");

        atras4 = findViewById(R.id.atras4);

        agregar = findViewById(R.id.button);


        materialesScroll = findViewById(R.id.materialesScroll);

        scrollview = findViewById(R.id.scroll);

        cargarMaterial(materia);



        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MaterialDeAyuda.this, CrearPropuesta.class);
                i.putExtra("materia",materia);
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MaterialDeAyuda.this).toBundle());


            }
        });

        materialesScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Determinar qué TextView fue seleccionado y obtener su índice
                int index = scrollview.indexOfChild(view);

                String texto = ((TextView) view).getText().toString();

              // openLink(materialDeAyuda.get(index).getLink());

            }
        });







        // Mostrar los enlaces de YouTube almacenados
        addLinkView(YOUTUBE_URL, "Logaritmos y ecuaciones", Color.BLUE,20);
        addLinkView(YOUTUBE1_URL, "Curso general de matemáticas financieras",Color.BLUE,20 );
        addLinkView(YOUTUBE2_URL, "Interés simple y compuesto", Color.BLUE,20);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Verificar el código de resultado
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Obtener los datos
//                //String resultado = data.getStringExtra("resultado");
//
//                // Mostrar el resultado
//
//            } else {
//                // La Activity finalizó sin completar la tarea
//            }
//        }
//    }

    private void cargarMaterial(String materia){

        materialDeAyuda.clear();
        materialesScroll.removeAllViewsInLayout();

        db.collection("material")
                //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada
                .whereEqualTo("materia",materia)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        int index = 0 ;
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            materialDeAyuda.add( new com.example.alumnos.modelos.MaterialDeAyuda(document.getId(), document.getData()) );

                            TextView titulo = new TextView(MaterialDeAyuda.this);
                            titulo.setText(materialDeAyuda.get(index).getTema());

                            // Agrega el RadioButton al grupo
                            materialesScroll.addView(titulo, index);

                            index++;
                        }
                    } else {
                        // Manejar errores
                        //Log.d(TAG, "Error getting documents: ", task.getException());
                        task.getException().printStackTrace();
                    }

                });



    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    //Esto valida si la CrearPropuesta registro una propuesta
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        System.out.println("Resultado wey");
                    }else{
                        System.out.println("Resultado wey");
                    }
                }
            });


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK) {
//            // Obtén el tema y el enlace del formulario
//            String tema = data.getStringExtra("tema");
//            String link = data.getStringExtra("link");
//            linkSet.add(link);
//
//            // Guardar el conjunto en SharedPreferences
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putStringSet("links", linkSet);
//            editor.putString(link, tema);
//            editor.apply();
//            addLinkView(link, tema,Color.BLUE,20);
//        }
//    }



    private void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);

    }

    /*private boolean isYouTubeLink(String link) {
        return link.contains("youtube.com") || link.contains("youtu.be");
    }*/

    private void addLinkView(String link, String tema, int color, float textSize) {
        String guion = "-" + " " + " " + tema + " " + " ";

        // Crear la imagen
        Drawable imgDrawable;
        if (link.contains("https://www.youtube.com") || link.contains("youtu.be")) {
            imgDrawable = getResources().getDrawable(R.drawable.youtube);
        } else if (link.endsWith(".pdf")) {
            imgDrawable = getResources().getDrawable(R.drawable.pdf);
        } else {
            imgDrawable = getResources().getDrawable(R.drawable.google);
        }
        imgDrawable.setBounds(0, 0, imgDrawable.getIntrinsicWidth(), imgDrawable.getIntrinsicHeight());

        // Crear un SpannableStringBuilder y añadir el texto y la imagen
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(guion);
        builder.setSpan(new ImageSpan(imgDrawable, ImageSpan.ALIGN_BASELINE), builder.length() - 1, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Crear un TextView y establecer el SpannableStringBuilder como su texto
        TextView textView = new TextView(this);
        textView.setText(builder);
        textView.setTag(link);
        textView.setOnClickListener(this);
        textView.setTextColor(color);
        textView.setTextSize(textSize);

        // Agregar el TextView al Layout
       // LayoutTopics.addView(textView);
    }

    @Override
    public void onClick(View view) {

    }
}