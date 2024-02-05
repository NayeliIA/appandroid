package com.example.alumnos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioActivity extends AppCompatActivity {

    ImageView atras5;
   TextView registrateAqui;
   TextView recuperarcontrasena;
    EditText correo;
    EditText contraseña;
    Button btnEntrar;
   FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        setContentView(R.layout.activity_inicio);

        atras5 = findViewById(R.id.atras5);

        atras5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent i = new Intent(InicioActivity.this, MainActivity.class);
                    startActivity(i);




            }
        });
        recuperarcontrasena = findViewById(R.id.recuperarcontrasena);
        registrateAqui = findViewById(R.id.registrateAqui);
        correo = findViewById(R.id.correo);

        contraseña = findViewById(R.id.contraseña);
        btnEntrar = findViewById(R.id.btnEntrar);
        mAuth = FirebaseAuth.getInstance();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        recuperarcontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioActivity.this, RecuperarActivity.class);
                startActivity(i);
            }
        });
        registrateAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });


    }

    private void login() {
            String correoL = correo.getText().toString();
            String contraseñaL = contraseña.getText().toString();

            mAuth.signInWithEmailAndPassword(correoL, contraseñaL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        if(correo.getText().toString().startsWith("luishiram")){
                            Intent i = new Intent(InicioActivity.this, AdminPropuestas.class);
                            startActivity(i);

                        }else{
                            Intent i = new Intent(InicioActivity.this, PaginaunoActivity.class);
                            startActivity(i);

                        }


                    } else {
                        Toast.makeText(InicioActivity.this, "Correo/contraseña incorrectos", Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }
}