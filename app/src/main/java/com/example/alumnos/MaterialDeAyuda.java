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



       // cargarMaterialDefinido(materia);



    }

    @Override
    public void onClick(View view) {


        int seleccionado = materialesScroll.indexOfChild(view);

        openLink( materialDeAyuda.get(seleccionado).getLink() );



    }



    private void cargarMaterialDefinido(String materia){

        switch (materia){

            case "GEOGRAFIA":

                addLinkView("https://docs.google.com/presentation/d/1Z9WWWZam_wZDKclYJh8tUm9Mta4Rh6HteU7lCalww3M/edit?usp=sharing", "Temario General", Color.BLUE,20);
                addLinkView("https://drive.google.com/file/d/1RFwEEhBPmKSVqlmGypcn8EWu7OPbJTMO/view?usp=drive_link", "Resumen del Temario",Color.BLUE,20 );
                addLinkView("https://youtu.be/mvsOl8s2aJM?si=R3cbPmZ6oc6ZZJ6s", "Tipos de erosión", Color.BLUE,20);
                addLinkView("https://youtu.be/c1H8ulqOO3A?si=X104OJ7krKXZCR0_", "Fenómeno niño y niña", Color.BLUE,20);
                addLinkView("https://youtu.be/b2QCAxkBlX4?si=GngXSmjzP_yVDZU4", "Equinoccios y Solsticios", Color.BLUE,20);

                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://docs.google.com/presentation/d/1Z9WWWZam_wZDKclYJh8tUm9Mta4Rh6HteU7lCalww3M/edit?usp=sharing","Temario General","GEOGRAFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://drive.google.com/file/d/1RFwEEhBPmKSVqlmGypcn8EWu7OPbJTMO/view?usp=drive_link","Resumen del Temario","GEOGRAFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/mvsOl8s2aJM?si=R3cbPmZ6oc6ZZJ6s","Tipos de erosión","GEOGRAFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/c1H8ulqOO3A?si=X104OJ7krKXZCR0_","Fenómeno niño y niña","GEOGRAFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/b2QCAxkBlX4?si=GngXSmjzP_yVDZU4","Equinoccios y Solsticios","GEOGRAFIA"));

                break;

            case "INGLES":

                addLinkView("https://www.britishcouncil.org.mx/blog/irregular-verbs", "Irregular Verbs", Color.BLUE,20);
                addLinkView("https://youtu.be/Nql1Y_TuIEM?si=zaRapJC9P3Y8DpSQ", "Condicionales",Color.BLUE,20 );
                addLinkView("https://youtu.be/Sg5lg5ohzMc?si=mMOJDQA-uYIviWHT", "Reported Speech", Color.BLUE,20);
                addLinkView("https://www.perfect-english-grammar.com/gerunds-and-infinitives.html", "Gerunds and infinitives", Color.BLUE,20);
                addLinkView("https://youtu.be/Yo3-SS79Atk?si=kj1N6awM2_nZP9Rm", "Gerunds and infinitives Part 2", Color.BLUE,20);
                addLinkView("https://youtu.be/FM8xxpFS0wk?si=Gp0RJarzkTyHdPob", "Comparative vs superlative", Color.BLUE,20);
                addLinkView("https://youtu.be/XACvspcClW0?si=br2DPuj6t4oWyuMW", "Futures in English", Color.BLUE,20);
                addLinkView("https://www.closerenglish.com.co/future-forms/", "Future Forms", Color.BLUE,20);
                addLinkView("https://youtu.be/uBj-efFVkgI?si=FY-EqPutmhCp5kra", "Used to vs Would", Color.BLUE,20);

                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.britishcouncil.org.mx/blog/irregular-verbs","Irregular Verbs","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/Nql1Y_TuIEM?si=zaRapJC9P3Y8DpSQ","Condicionales","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/Sg5lg5ohzMc?si=mMOJDQA-uYIviWHT","Reported Speech","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.perfect-english-grammar.com/gerunds-and-infinitives.html","Gerunds and infinitives","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/Yo3-SS79Atk?si=kj1N6awM2_nZP9Rm","Gerunds and infinitives Part 2","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/FM8xxpFS0wk?si=Gp0RJarzkTyHdPob","Futures in English","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.closerenglish.com.co/future-forms/","Future Forms","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/uBj-efFVkgI?si=FY-EqPutmhCp5kra","Used to vs Would","INGLES"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/FM8xxpFS0wk?si=Gp0RJarzkTyHdPob","Comparative vs superlative\"","INGLES"));
                break;

            case "FILOSOFIA":

                addLinkView("https://view.genial.ly/64dad9c843f8dc001269a405/presentation-mitos-cosmogonicos", "Mitos Cosmogónicos", Color.BLUE,20);
                addLinkView("https://view.genial.ly/5d8026a69b543a0fe680f2d2/presentation-mito", "El mito",Color.BLUE,20 );
                addLinkView("https://view.genial.ly/64ebe295220d2f0018ff5420/presentation-pitagoricos", "Pitagóricos", Color.BLUE,20);
                addLinkView("https://view.genial.ly/5ea9d53c9065570d9a2f0758/presentation-escuela-eleatica", "Escuela Eleática", Color.BLUE,20);
                addLinkView("https://view.genial.ly/64fe5d87a4860700186ef0de/presentation-presentacion-de-heraclito", "Heráclito", Color.BLUE,20);
                addLinkView("https://view.genial.ly/650f641d4b3b670011f8f681/presentation-filosofos-pluralistas", "Filosofós Pluralistas", Color.BLUE,20);
                addLinkView("https://quizlet.com/mx/361282272/los-primeros-filosofos-flash-cards/?i=5di0h7&x=1jqt", "Los primos Filosofós", Color.BLUE,20);
                addLinkView("https://view.genial.ly/651e1d6ac38fb20011a5416e/presentation-los-atomistas", "Los atómistas", Color.BLUE,20);
                addLinkView("https://view.genial.ly/652ada978583d200111bc212/presentation-socrates", "Sócrates", Color.BLUE,20);
                addLinkView("https://view.genial.ly/5ee2b7dc7ea3bb0d62a71e0c/presentation-metafisica-de-platon", "Metafísica de Platón", Color.BLUE,20);
                addLinkView("https://quizlet.com/mx/370997041/introduccion-a-platon-flash-cards/?i=5di0h7&x=1jqt", "Introducción a Platón", Color.BLUE,20);

                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/64dad9c843f8dc001269a405/presentation-mitos-cosmogonicos","Mitos Cosmogónicos","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/5d8026a69b543a0fe680f2d2/presentation-mito","El mito","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/64ebe295220d2f0018ff5420/presentation-pitagoricos","Pitagóricos","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/5ea9d53c9065570d9a2f0758/presentation-escuela-eleatica","Escuela Eleática","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/64fe5d87a4860700186ef0de/presentation-presentacion-de-heraclito","Heráclito","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/650f641d4b3b670011f8f681/presentation-filosofos-pluralistas","Filosofós Pluralistas","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://quizlet.com/mx/361282272/los-primeros-filosofos-flash-cards/?i=5di0h7&x=1jqt","Los primos Filosofós","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/651e1d6ac38fb20011a5416e/presentation-los-atomistas","Los atómistas","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/652ada978583d200111bc212/presentation-socrates","Sócrates","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://view.genial.ly/5ee2b7dc7ea3bb0d62a71e0c/presentation-metafisica-de-platon","Metafísica de Platón","FILOSOFIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://quizlet.com/mx/370997041/introduccion-a-platon-flash-cards/?i=5di0h7&x=1jqt","Introducción a Platón","FILOSOFIA"));

                break;

            case "HISTORIA" :

                addLinkView("https://drive.google.com/file/d/1pl87Iz8ozjjMfU3rbAk2sKOXYKGH5lhd/view?usp=drive_link", "La primera Guerra Mundial y sus consecuencias", Color.BLUE,20);
                addLinkView("https://drive.google.com/file/d/1WJydTkVnbJSjOOPNawsYcNz0sLHHxam9/view?usp=sharing", "La Revolución Rusa y El Fin de la Guerra",Color.BLUE,20 );
                addLinkView("https://youtu.be/Vbu6tH0Hc-o?si=GgLXb7em3LPuv9X0", "La primera Guerra Mundial", Color.BLUE,20);
                addLinkView("https://youtu.be/-mnRwShLmXc?si=d70i2wlWVg0IAVZS", "La Revolución Rusa", Color.BLUE,20);
                addLinkView("https://youtu.be/3LQAnFEADl4?si=TWcm3ACR33S9zfU0", "La Revolución Industrial", Color.BLUE,20);

                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://drive.google.com/file/d/1pl87Iz8ozjjMfU3rbAk2sKOXYKGH5lhd/view?usp=drive_link","La primera Guerra Mundial y sus consecuencias","HISTORIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://drive.google.com/file/d/1WJydTkVnbJSjOOPNawsYcNz0sLHHxam9/view?usp=sharing","La Revolución Rusa y El Fin de la Guerra","HISTORIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/Vbu6tH0Hc-o?si=GgLXb7em3LPuv9X0","La primera Guerra Mundial","HISTORIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/-mnRwShLmXc?si=d70i2wlWVg0IAVZS","La Revolución Rusa","HISTORIA"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://youtu.be/3LQAnFEADl4?si=TWcm3ACR33S9zfU0","La Revolución Industrial","HISTORIA"));

                break;

            case "MATEMATICAS":

                addLinkView("https://www.youtube.com/playlist?list=PLeySRPnY35dHyUzy-YVDD9ZllhtXfcQ4_&si=mD36rtY4espFZ85r", "Logaritmos y ecuaciones", Color.BLUE,20);
                addLinkView("https://www.youtube.com/playlist?list=PLlQ8HaUPX-NJFJf2LTRQ1qtI-2J8ocW8k&si=lCn3-_3kT0l8gqTl", "Curso general de matemáticas financieras",Color.BLUE,20 );
                addLinkView("https://www.youtube.com/playlist?list=PLeySRPnY35dE48tg5rvN5UyO8pxXNv61L", "Interés simple y compuesto", Color.BLUE,20);

                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.youtube.com/playlist?list=PLeySRPnY35dHyUzy-YVDD9ZllhtXfcQ4_&si=mD36rtY4espFZ85r","Logaritmos y ecuaciones","MATEMATICAS"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.youtube.com/playlist?list=PLlQ8HaUPX-NJFJf2LTRQ1qtI-2J8ocW8k&si=lCn3-_3kT0l8gqTl","Curso general de matemáticas financieras","MATEMATICAS"));
                materialDeAyuda.add(new com.example.alumnos.modelos.MaterialDeAyuda("https://www.youtube.com/playlist?list=PLeySRPnY35dE48tg5rvN5UyO8pxXNv61L","Interés simple y compuesto","MATEMATICAS"));

            break;


        }




    }

    private void cargarMaterial(String materia){

        cargarMaterialDefinido(materia);

        db.collection("material")
                //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada
                .whereEqualTo("materia",materia)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        int index = materialDeAyuda.size();
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            materialDeAyuda.add( new com.example.alumnos.modelos.MaterialDeAyuda(document.getId(), document.getData()) );

                            TextView titulo = new TextView(MaterialDeAyuda.this);
                            titulo.setText(materialDeAyuda.get(index).getTema());

                            titulo.setId(index);
                            // Agrega el RadioButton al grupo
                            //materialesScroll.addView(titulo, index);
                            addLinkView(materialDeAyuda.get(index).getLink(),materialDeAyuda.get(index).getTema(),Color.BLUE, 20 );

                            titulo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Acciones a realizar al hacer clic en el TextView
                                    int index = scrollview.indexOfChild(view);


                                }
                            });

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

        materialesScroll.addView(textView);

        // Agregar el TextView al Layout
       // LayoutTopics.addView(textView);
    }


}