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
public class InglesAyudaActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_AGREGAR = 100;
    ImageView atras4;
    Button button;
    LinearLayout LayoutTopics;
    private SharedPreferences sharedPreferences;
    private Set<String> linkSet;
    private final static String GOOGLE1 = "https://www.britishcouncil.org.mx/blog/irregular-verbs";
    private final static String YOUTUBE1= "https://youtu.be/Nql1Y_TuIEM?si=zaRapJC9P3Y8DpSQ";
    private final static String YOUTUBE2 = "https://youtu.be/Sg5lg5ohzMc?si=mMOJDQA-uYIviWHT";
    private final static String GOOGLE4 = "https://www.perfect-english-grammar.com/gerunds-and-infinitives.html";
    private final static String YOUTUBE3 = "https://youtu.be/Yo3-SS79Atk?si=kj1N6awM2_nZP9Rm";
    private final static String YOUTUBE4 = "https://youtu.be/FM8xxpFS0wk?si=Gp0RJarzkTyHdPob";
    private final static String YOUTUBE5 = "https://youtu.be/XACvspcClW0?si=br2DPuj6t4oWyuMW";
    private final static String GOOGLE8 = "https://www.closerenglish.com.co/future-forms/";
    private final static String YOUTUBE6 = "https://youtu.be/uBj-efFVkgI?si=FY-EqPutmhCp5kra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematicas_ayuda);

        atras4 = findViewById(R.id.atras4);
        button = findViewById(R.id.button);
        LayoutTopics = findViewById(R.id.LayoutTopics);

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InglesAyudaActivity.this, Materias.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InglesAyudaActivity.this, AgregarActivity.class);
                startActivityForResult(i, REQUEST_CODE_AGREGAR);
            }
        });

        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        linkSet = sharedPreferences.getStringSet("links", new HashSet<>());

        // Mostrar los links almacenados
        for (String link : linkSet) {
            String tema = sharedPreferences.getString(link, "");
            addLinkView(link, tema, Color.BLUE,20);
        }

        // Mostrar los enlaces de YouTube almacenados
        addLinkView(GOOGLE1, "Irregular Verbs", Color.BLUE,20);
        addLinkView(YOUTUBE1, "Condicionales",Color.BLUE,20 );
        addLinkView(YOUTUBE2, "Reported Speech", Color.BLUE,20);
        addLinkView(GOOGLE4, "Gerunds and infinitives", Color.BLUE,20);
        addLinkView(YOUTUBE3, "Gerunds and infinitives Part 2", Color.BLUE,20);
        addLinkView(YOUTUBE4, "Comparative vs superlative", Color.BLUE,20);
        addLinkView(YOUTUBE5, "Futures in English", Color.BLUE,20);
        addLinkView(GOOGLE8, "Future Forms", Color.BLUE,20);
        addLinkView(YOUTUBE6, "Used to vs Would", Color.BLUE,20);
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
            editor.putStringSet("links", linkSet);
            editor.putString(link, tema);
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