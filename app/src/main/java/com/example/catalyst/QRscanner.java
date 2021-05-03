package com.example.catalyst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.catalyst.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



import java.util.Calendar;
import java.util.Date;

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
        intengrator.setCaptureActivity(CaptureAct.class);
        intengrator.setOrientationLocked(false);
        intengrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intengrator.setPrompt("Escaneando codigo");
        intengrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (resultado != null){
            mensajeEjecucion(resultado);

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void mensajeEjecucion(IntentResult resultado){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Código escaneado: ");
        builder.setMessage(resultado.getContents() +", "+fechaHora(resultado));
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public static String fechaHora(IntentResult resultado){
        String fechaHora = "";
        if (resultado !=null){
            Calendar today = Calendar.getInstance();
            fechaHora = today.getTime().toString();
        }
        return fechaHora;

    }





}