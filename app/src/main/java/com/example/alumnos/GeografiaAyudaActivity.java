package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import java.util.HashSet;
import java.util.Set;
import android.widget.LinearLayout.LayoutParams;
import android.content.SharedPreferences;

public class GeografiaAyudaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_AGREGAR = 100;
    ImageView atras4;
    Button button;
    LinearLayout LayoutTopics;
    private SharedPreferences sharedPreferences;
    private Set<String> linkSet;
    private final static String GOOGLE1 = "https://docs.google.com/presentation/d/1Z9WWWZam_wZDKclYJh8tUm9Mta4Rh6HteU7lCalww3M/edit?usp=sharing";
    private final static String GOOGLE2= "https://drive.google.com/file/d/1RFwEEhBPmKSVqlmGypcn8EWu7OPbJTMO/view?usp=drive_link";
    private final static String YOUTUBE1 = "https://youtu.be/mvsOl8s2aJM?si=R3cbPmZ6oc6ZZJ6s";
    private final static String YOUTUBE2 = "https://youtu.be/c1H8ulqOO3A?si=X104OJ7krKXZCR0_";
    private final static String YOUTUBE3 = "https://youtu.be/b2QCAxkBlX4?si=GngXSmjzP_yVDZU4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geografia_ayuda);

        atras4 = findViewById(R.id.atras4);
        button = findViewById(R.id.button);
        LayoutTopics = findViewById(R.id.LayoutTopics);

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GeografiaAyudaActivity.this, Materias.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GeografiaAyudaActivity.this, AgregarActivity.class);
                startActivityForResult(i, REQUEST_CODE_AGREGAR);
            }
        });

        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        linkSet = sharedPreferences.getStringSet("links_geografia", new HashSet<>());

        // Mostrar los links almacenados
        for (String link : linkSet) {
            String tema = sharedPreferences.getString("geografia_"+link, "");
            addLinkView(link, tema, Color.BLUE,20);
        }

        // Mostrar los enlaces de YouTube almacenados
        addLinkView(GOOGLE1, "Temario General", Color.BLUE,20);
        addLinkView(GOOGLE2, "Resumen del Temario",Color.BLUE,20 );
        addLinkView(YOUTUBE1, "Tipos de erosión", Color.BLUE,20);
        addLinkView(YOUTUBE2, "Fenómeno niño y niña", Color.BLUE,20);
        addLinkView(YOUTUBE3, "Equinoccios y Solsticios", Color.BLUE,20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK) {
            // Obtén el tema y el enlace del formulario
            String tema = data.getStringExtra("tema");
            String link = data.getStringExtra("link");
            linkSet.add(link);

            // Guardar el conjunto en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("links_geografia", linkSet);
            editor.putString("geografia_"+link, tema);
            editor.apply();
            addLinkView(link, tema,Color.BLUE,20);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            String link = (String) v.getTag();
            if (link != null && !link.isEmpty()) {
                openLink(link);
            }
        }
    }

    private void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);

    }



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
        LayoutTopics.addView(textView);
    }
}