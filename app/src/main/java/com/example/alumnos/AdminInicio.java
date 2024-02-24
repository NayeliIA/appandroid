package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminInicio extends AppCompatActivity {
    ImageView atras5;
    Button buttonAdmin;
    Button buttonContenido;
    Button buttonfinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inicio);
        atras5 = findViewById(R.id.atras5);
        atras5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminInicio.this, InicioActivity.class);
                startActivity(i);
            }
        });

        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminInicio.this, AdminUsuarios.class);
                startActivity(i);
            }
        });

        buttonContenido = findViewById(R.id.buttonContenido);
        buttonContenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminInicio.this, AdminPropuestas.class);
                startActivity(i);
            }
        });
        buttonfinish = findViewById(R.id.buttonfinish);
        buttonfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(AdminInicio.this, MainActivity.class);
                finish();
            }
        });

    }
}
