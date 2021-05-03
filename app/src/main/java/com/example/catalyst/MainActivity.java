package com.example.catalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button); //inicializar boton
        button.setOnClickListener(new View.OnClickListener() { //Al hacer click inicia la actividad para abrir el escaner
            @Override
            public void onClick(View v) {
                abirEscanerQR();

            }
        });


    }

    public void abirEscanerQR(){
        Intent intent = new Intent(this, QRscanner.class); //Se llama al escaner QR
        startActivity(intent); //Se inicia la actividad
    }


}