package modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EnviarIngresos{
    private Context contexto;
    private String url;
    private String codigo;
    private String usuario;
    private String hora;


    public EnviarIngresos(Context contexto, String url, String codigo, String usuario, String hora) {
        this.contexto = contexto;
        this.url = url;
        this.codigo = codigo;
        this.usuario = usuario;
        this.hora = hora;

    }

    public void ejecutarServicio(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> Toast.makeText(contexto, "OPERACION EXITOSA", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(contexto, error.toString(), Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo);
                parametros.put("usuario", usuario);
                parametros.put("hora", hora);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(stringRequest);
    }


}
