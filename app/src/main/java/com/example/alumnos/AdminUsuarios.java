package com.example.alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alumnos.modelos.MaterialDeAyuda;
import com.example.alumnos.modelos.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdminUsuarios extends AppCompatActivity {

    private EditText correoUsuario;

    private Button btnBuscar;

    private Button btnActualizar;

    private TextView nombre;


    FirebaseFirestore db;

    Usuario usuario;

    final String[] opciones = {"", "ESTUDIANTE", "ADMIN"};

    private Spinner roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios);


        correoUsuario = findViewById(R.id.correoUsuario);

        btnBuscar = findViewById(R.id.btnBuscarUsuario);

        btnActualizar = findViewById(R.id.btnActualizar);

        nombre = findViewById(R.id.usuarioNombre);

        roles = findViewById(R.id.rolesSpinner);

        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roles.setAdapter(adapter);






        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String correo = correoUsuario.getText().toString();

                if(correo.trim().equals("")) {

                    return;
                }


                db.collection("Users")
                        //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada
                        .whereEqualTo("email",correo)
                        .get()
                        .addOnCompleteListener(task -> {

                            if (task.isSuccessful()) {


                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    usuario = new Usuario(document.getId(), document.getData());

                                    nombre.setText("Nombre: "+usuario.getName() );

                                    roles.setSelection(usuario.getRole() == 1 ? 2 : 1 );
                                }


                            } else {
                                // Manejar errores
                                //Log.d(TAG, "Error getting documents: ", task.getException());
                                task.getException().printStackTrace();
                            }

                        });






            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String correo = correoUsuario.getText().toString();

                if(Objects.isNull(usuario) || roles.getSelectedItem().toString().equals("ROLE de usuario")) {

                    return;
                }


                DocumentReference userDoc = db.collection("Users").document(usuario.getId());
                        //Con esto se consulta las propuestas con la condicion de que la propiedad materia tenga el nombre de la materia seleccionada

                Map<String, Object> updates = new HashMap<>();

                final String opcionSe = roles.getSelectedItem().toString();
                updates.put("role", opcionSe.equals("ADMIN") ? 1 : 2);

                userDoc.update(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // La actualización fue exitosa

                                usuario = null;
                                nombre.setText("Nombre: ");
                                correoUsuario.setText("");
                                roles.setSelection(0);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Manejar el error en caso de fallo
                            }
                        });;





            }
        });

    }



}