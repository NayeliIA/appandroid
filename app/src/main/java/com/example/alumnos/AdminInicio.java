package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//com.example.alumnos
public class AdminInicio extends AppCompatActivity {

    Button buttonAdmin;
    Button buttonContenido;
    Button buttonfinish;
    Button buttonsubir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inicio);


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

        buttonsubir = findViewById(R.id.buttonsubir);
        buttonsubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminInicio.this, AdminCrearPropuesta.class);
                startActivity(i);
            }
        });
        buttonfinish = findViewById(R.id.buttonfinish);
        buttonfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminInicio.this, MainActivity.class);

                startActivity(i);
                finish();
            }
        });

    }
}
