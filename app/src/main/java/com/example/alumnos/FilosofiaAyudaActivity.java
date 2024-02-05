package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;

public class FilosofiaAyudaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_AGREGAR = 100;
    ImageView atras4;
    Button button;
    LinearLayout LayoutTopics;
    private SharedPreferences sharedPreferences;
    private Set<String> linkSet;
    private final static String GOOGLE1 = "https://view.genial.ly/64dad9c843f8dc001269a405/presentation-mitos-cosmogonicos";
    private final static String GOOGLE2 = "https://view.genial.ly/5d8026a69b543a0fe680f2d2/presentation-mito";
    private final static String GOOGLE3 = "https://view.genial.ly/64ebe295220d2f0018ff5420/presentation-pitagoricos";
    private final static String GOOGLE4 = "https://view.genial.ly/5ea9d53c9065570d9a2f0758/presentation-escuela-eleatica";
    private final static String GOOGLE5 = "https://view.genial.ly/64fe5d87a4860700186ef0de/presentation-presentacion-de-heraclito";
    private final static String GOOGLE6 = "https://view.genial.ly/650f641d4b3b670011f8f681/presentation-filosofos-pluralistas";
    private final static String GOOGLE7 = "https://quizlet.com/mx/361282272/los-primeros-filosofos-flash-cards/?i=5di0h7&x=1jqt";
    private final static String GOOGLE8 = "https://view.genial.ly/651e1d6ac38fb20011a5416e/presentation-los-atomistas";
    private final static String GOOGLE9 = "https://view.genial.ly/652ada978583d200111bc212/presentation-socrates";
    private final static String GOOGLE10 = "https://view.genial.ly/5ee2b7dc7ea3bb0d62a71e0c/presentation-metafisica-de-platon";
    private final static String GOOGLE11 = "https://quizlet.com/mx/370997041/introduccion-a-platon-flash-cards/?i=5di0h7&x=1jqt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filosofia_ayuda);

        atras4 = findViewById(R.id.atras4);
        button = findViewById(R.id.button);
        LayoutTopics = findViewById(R.id.LayoutTopics);

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FilosofiaAyudaActivity.this, Materias.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FilosofiaAyudaActivity.this, AgregarActivity.class);
                startActivityForResult(i, REQUEST_CODE_AGREGAR);
            }
        });


        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        linkSet = sharedPreferences.getStringSet("links_filosofia", new HashSet<>());

        // Mostrar los links almacenados
        for (String link : linkSet) {
            String tema = sharedPreferences.getString("filosofia_"+link, "");
            addLinkView(link, tema, Color.BLUE, 20);
        }

        // Mostrar los enlaces de YouTube almacenados
        addLinkView(GOOGLE1, "Mitos Cosmogónicos", Color.BLUE, 20);
        addLinkView(GOOGLE2, "El mito", Color.BLUE, 20);
        addLinkView(GOOGLE3, "Pitagóricos", Color.BLUE, 20);
        addLinkView(GOOGLE4, "Escuela Eleática", Color.BLUE, 20);
        addLinkView(GOOGLE5, "Heráclito", Color.BLUE, 20);
        addLinkView(GOOGLE6, "Filosofós Pluralistas", Color.BLUE, 20);
        addLinkView(GOOGLE7, "Los primos Filosofós", Color.BLUE, 20);
        addLinkView(GOOGLE8, "Los atómistas", Color.BLUE, 20);
        addLinkView(GOOGLE9, "Sócrates", Color.BLUE, 20);
        addLinkView(GOOGLE10, "Metafísica de Platón", Color.BLUE, 20);
        addLinkView(GOOGLE11, "Introducción a Platón", Color.BLUE, 20);
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
            editor.putStringSet("links_filosofia", linkSet);
            editor.putString("filosofia_"+link, tema);
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

    private boolean isYouTubeLink(String link) {
        return link.contains("youtube.com") || link.contains("youtu.be");
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
