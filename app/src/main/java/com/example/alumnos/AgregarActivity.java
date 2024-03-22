package com.example.alumnos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class AgregarActivity extends AppCompatActivity {

    private EditText editTextTema;
    private EditText editTextLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        editTextTema = findViewById(R.id.nombreMateria);
        editTextLink = findViewById(R.id.editTextLink);

        final int REQUEST_CODE_AGREGAR = 100;

        Button buttonSubir = findViewById(R.id.button7);

        buttonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tema = editTextTema.getText().toString();
                String link = editTextLink.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("tema", tema);
                intent.putExtra("link", link);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
