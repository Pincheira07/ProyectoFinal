package com.example.catalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button); //inicializar boton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRscanner();

            }
        });


    }
    public void onClickMap(View v) {
        Intent intent = new Intent(this, Map.class); //llamada al mapa
        startActivity(intent);
    }
    public void openQRscanner(){
        Intent intent = new Intent(this, QRscanner.class); //Se llama a la actividad secundaria
        startActivity(intent); //Se inicia la actividad
    }


}