package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkangel.arkangel.Clases.Sentimiento;
import com.arkangel.arkangel.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class actividad extends AppCompatActivity {

    private TextView txtTitulo, txtDescripcion;
    private ImageView imgActividad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
        Sentimiento sentimiento = Sentimiento.getInstance();
        txtTitulo=findViewById(R.id.textTituloActividad);
        txtDescripcion=findViewById(R.id.textDescripcionActividad);
        imgActividad=findViewById(R.id.actividadImg);
        Button hecho = findViewById(R.id.hecho);
        getEmotion();
        hecho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), sentimientos.class);
                startActivity(intent);

            }
        });

    }
    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
    private void getEmotion(){
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String animo_id= Sentimiento.animo_id;
        String causa_id= Sentimiento.causa_id;
        //String causa_id= "2";


        try {
            url= new URL("https://arkangelapp.herokuapp.com/emociones/kid_recomendation");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("causa_id",causa_id);
            parametrosPost.put("animo_id",animo_id);
            OutputStream os = conexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int respuesta= conexion.getResponseCode();
            result= new StringBuilder();
            String json;
            Log.i("Response Sentimiento",respuesta+"antes");
            if(respuesta== HttpURLConnection.HTTP_OK){
                InputStream in= new BufferedInputStream(conexion.getInputStream());
                BufferedReader buf= new BufferedReader(new InputStreamReader(in));

                while (((linea=buf.readLine())!=null)){
                    result.append(linea);
                    // Toast.makeText( getApplicationContext(), result.toString()+"entranding", Toast.LENGTH_LONG).show();

                    //   txtUser.setText(result.toString());
                }
                json=result.toString();
                buf.close();
                Log.i("Response Sentimiento",json);


                // txtUser.setText(json);


                JSONObject reader = new JSONObject(json);
                txtDescripcion.setText(reader.optString("descripcion"));
                txtTitulo.setText(reader.optString("title_suger"));
                String nameImage="aburrido.jpg";
                nameImage=reader.optString("image");
                String uri = "@drawable/"+nameImage;
                int id = getResources().getIdentifier(uri, null, getPackageName());
                  imgActividad.setImageResource(id);

                Log.i("Response Sentimiento",id+"");
                Log.i("Response Sentimiento",nameImage+"");
            }
        }catch (Exception e){

            //  Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
