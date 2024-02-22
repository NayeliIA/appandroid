package com.example.alumnos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class InicioActivity extends AppCompatActivity {

    ImageView atras5;
    TextView registrateAqui;
    TextView recuperarcontrasena;
    EditText correo;
    EditText contraseña;
    Button btnEntrar;
    FirebaseAuth mAuth;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        setContentView(R.layout.activity_inicio);

        atras5 = findViewById(R.id.atras5);

        db = FirebaseFirestore.getInstance();

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



// Verificar si hay un usuario actualmente autenticado
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        if (currentUser != null) {
//
//            String uid = currentUser.getUid();
//            String email = currentUser.getEmail();
//

//
//        }


    }

    private void login() {


        String correoL = correo.getText().toString();
        String contraseñaL = contraseña.getText().toString();

        mAuth.signInWithEmailAndPassword(correoL, contraseñaL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {



                    db.collection("Users").document( task.getResult().getUser().getUid() ).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        // El documento existe, puedes acceder a sus datos
                                        Map<String, Object> datos = documentSnapshot.getData();
                                        String rolUsuario = datos.get("role").toString();

                                        if(rolUsuario.equals("1")){

                                            Intent i = new Intent(InicioActivity.this, AdminInicio.class);
                                            startActivity(i);

                                        }else{

                                            Intent i = new Intent(InicioActivity.this, PaginaunoActivity.class);
                                            startActivity(i);

                                        }



                                    } else {
                                        // El documento no existe

                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Manejar el fallo en la operación

                                }
                            });



                } else {
                    Toast.makeText(InicioActivity.this, "Correo/contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


