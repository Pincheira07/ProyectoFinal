package com.example.catalyst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class QRscanner extends AppCompatActivity implements View.OnClickListener {

    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rscanner);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
    }

    private void ejecutarServicio(String URL, String codigo, String hora, String usuario){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> Toast.makeText(getApplicationContext(), "Información recibida", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT)){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo );
                parametros.put("hora", hora);
                parametros.put("usuario", usuario);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
            if(resultado.getContents()!=null){
                ejecutarServicio("http://192.168.0.2:8080/catalyst/insertar_datos.php",resultado.getContents(), fechaHora(resultado),"Th{phz'Wpujolpyh");
            }else{
                Toast.makeText(this,"Sin resultados", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String mensajeEjecucion(IntentResult resultado){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Código escaneado: ");
        String mensaje = resultado.getContents();
        AlertDialog dialog = builder.create();
        dialog.show();

        return mensaje;

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