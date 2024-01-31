package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class GuerramundialActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView atras4;
    Button btn1,btn2,btn3,btn4,btn5,btn6;
    private final static String DRIVE_URL = "https://drive.google.com/file/d/1pl87Iz8ozjjMfU3rbAk2sKOXYKGH5lhd/view?usp=drive_link";
    private final static String DRIVE1_URL = "https://drive.google.com/file/d/1WJydTkVnbJSjOOPNawsYcNz0sLHHxam9/view?usp=sharing";
    private final static String YOUTUBE_URL = "https://youtu.be/Vbu6tH0Hc-o?si=GgLXb7em3LPuv9X0";
    private final static String YOUTUBE1_URL = "https://youtu.be/-mnRwShLmXc?si=2wlWVg0IAVZS";
    private final static String YOUTUBE2_URL = "https://youtu.be/3LQAnFEADl4?si=TWcm3ACR33SzfU0";
    private final static String YOUTUBE3_URL = "https://www.youtube.com/watch?v=_6G_F0ok6NU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guerramundial);

        atras4 = findViewById(R.id.atras4);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        // Configurar el enlace dinámicamente

        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuerramundialActivity.this, Materias.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (v.getId() == R.id.button1) {
            intent.setData(Uri.parse(DRIVE_URL));
        } else if (v.getId() == R.id.button2) {
            intent.setData(Uri.parse(DRIVE1_URL));
        } else if (v.getId() == R.id.button3) {
            intent.setData(Uri.parse(YOUTUBE_URL));
        } else if (v.getId() == R.id.button4) {
            intent.setData(Uri.parse(YOUTUBE1_URL));
        } else if (v.getId() == R.id.button5) {
            intent.setData(Uri.parse(YOUTUBE2_URL));
        } else if (v.getId() == R.id.button6) {
            intent.setData(Uri.parse(YOUTUBE3_URL));
        }

        startActivity(intent);
    }
}
