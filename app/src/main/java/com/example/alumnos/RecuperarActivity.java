package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecuperarActivity extends AppCompatActivity {

    ImageView atras;

    EditText correoR;
    EditText contraseñaR;
    EditText confirmarcontraseña;

    Button btnRegistro;

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        atras = findViewById(R.id.atras8);

        correoR = findViewById(R.id.correoR);
        contraseñaR = findViewById(R.id.contraseñaR);
        confirmarcontraseña = findViewById(R.id.confirmarcontraseña);

        btnRegistro = findViewById(R.id.btnRecuperar);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String email = correoR.getText().toString();

        if (!email.isEmpty()) {
            if (isEmailValid(email)) {
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RecuperarActivity.this, "Se ha enviado un enlace para restablecer la contraseña a su correo electrónico", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RecuperarActivity.this, "No se pudo enviar el enlace para restablecer la contraseña. Verifique el correo electrónico.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(RecuperarActivity.this, "El correo no es válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RecuperarActivity.this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }
}