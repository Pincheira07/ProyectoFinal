package com.example.catalyst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

import modelo.EnviarRegistros;

public class QRscanner extends AppCompatActivity implements View.OnClickListener {


    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rscanner);



        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        escanearCodigo();

    }

    private void escanearCodigo() {
        IntentIntegrator intengrator = new IntentIntegrator(this);
        intengrator.setOrientationLocked(false);
        intengrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intengrator.setPrompt("Escaneando codigo");
        intengrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (resultado != null){
            if(resultado.getContents()!=null){
                ejecutarServicio("http://192.168.0.8:8080/catalyst/registro.php",resultado.getContents(), codigo(), fechaHora());

            }else{
                Toast.makeText(this,"Sin resultados", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private String codigo() {
        String codigo = "MP" + "-" + "308" + "-" + Math.random();
        return codigo;
    }

    private void ejecutarServicio(String url,String codigo, String usuario, String hora ){
        EnviarRegistros entrada = new EnviarRegistros(this,url, codigo, usuario, hora);
        entrada.ejecutarServicio();
    }

    public static String fechaHora(){
        String fechaHora = "";
        Calendar today = Calendar.getInstance();
        fechaHora = today.getTime().toString();
        return fechaHora;

    }
}
