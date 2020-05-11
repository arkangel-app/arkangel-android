package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arkangel.arkangel.Activitys.Notifications;
import com.arkangel.arkangel.Clases.User;
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

public class tutor extends AppCompatActivity {

    private EditText keyedit;

    private boolean respuestaok=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ImageView next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getKey();

                if(respuestaok){
                    Intent intent = new Intent(getBaseContext(), Notifications.class);
                    startActivity(intent);
                }else{
                   Toast.makeText( getApplicationContext(), "Código incorrecto ", Toast.LENGTH_LONG).show();
                }

            }
        });

        keyedit = findViewById(R.id.codigo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notifications, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.inicio:
                // do your code
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
    private void getKey(){
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String key=keyedit.getText().toString();




        try {
            url= new URL("https://arkangelapp.herokuapp.com/users/test_parent");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("key",key);
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
                Log.i("ResponseJson",json);
                JSONObject reader = new JSONObject(json);
                User user= User.getInstance();
                user.id_user =reader.getInt("id_kid")+"";
                Log.i("ResponseJson", user.id_user);

                respuestaok = true;

                // txtUser.setText(json);



                /*txtDescripcion.setText(reader.optString("descripcion"));
                txtTitulo.setText(reader.optString("title_suger"));
                String nameImage="aburrido.jpg";
                nameImage=reader.optString("image");
                String uri = "@drawable/"+nameImage;
                int id = getResources().getIdentifier(uri, null, getPackageName());
                imgActividad.setImageResource(id);*/

            }
        }catch (Exception e){

            //  Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
