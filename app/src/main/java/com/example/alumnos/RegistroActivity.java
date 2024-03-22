package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    ImageView atras;
    EditText nombre;
    EditText correoR;
    EditText contraseñaR;
    EditText confirmarcontraseña;
    EditText telefono;
    Button btnRegistro;

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        atras = findViewById(R.id.atras8);
        nombre = findViewById(R.id.nombreMateria);
        correoR = findViewById(R.id.correoR);
        contraseñaR = findViewById(R.id.contraseñaR);
        confirmarcontraseña = findViewById(R.id.confirmarcontraseña);
        telefono = findViewById(R.id.telefono);
        btnRegistro = findViewById(R.id.btnRecuperar);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroActivity.this, InicioActivity.class);
                startActivity(i);
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
        // Obtenemos los datos del formulario
        String name = nombre.getText().toString();
        String email = correoR.getText().toString();
        String password = contraseñaR.getText().toString();
        String confirmpassword = confirmarcontraseña.getText().toString();

        // Validamos que todos los campos estén llenos
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmpassword.isEmpty()) {

            if (isEmailValid(email)) {
                if (password.equals(confirmpassword)) {
                    if (password.length() >= 8) {
                        createUser(name, email, password);
                    }
                    else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener almenos 8 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(RegistroActivity.this, "El correo no es valido", Toast.LENGTH_SHORT).show();
            }
        }
        else{
        Toast.makeText(RegistroActivity.this, "Para continuar inserta todos los campos", Toast.LENGTH_SHORT).show();
    }

}

    private void createUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                  String id = mAuth.getCurrentUser().getUid();
                  Map<String, Object> map = new HashMap<>();
                  map.put("name", name);
                  map.put("email", email);
                  map.put("role", 2);
                    mFirestore.collection("Users").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(RegistroActivity.this, InicioActivity.class);
                                startActivity(i);
                                Toast.makeText(RegistroActivity.this, "El usuario se almacenó correctamente", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegistroActivity.this, "No se pudo registrar el usuario, intenta de nuevo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }

    public boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }
}
