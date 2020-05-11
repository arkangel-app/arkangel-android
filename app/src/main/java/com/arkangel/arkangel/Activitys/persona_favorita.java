package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.arkangel.arkangel.R;
import com.arkangel.arkangel.Clases.User;

import org.json.JSONArray;
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

public class persona_favorita extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private final String[] parentesco = { "Papá", "Mamá", "Tío", "Tía", "Abuelo", "Abuela", "Hermano", "Hermana", "Primo", "Prima"};

    private EditText nombre;
    private EditText correo;
    private String id_user;

    private String parentSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_favorita);

        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, parentesco);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        //final String categoria=getIntent().getExtras().getString("id_user");
        User user=User.getInstance();
     //   Toast.makeText(getApplicationContext(),user.id_user+"",Toast.LENGTH_LONG);
        id_user= User.id_user;
        Log.i("test", User.id_user +"");

        Button crearcuenta = findViewById(R.id.crearcuenta);

        crearcuenta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consumirServicio();

                newIntenten();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
      //  Toast.makeText(getApplicationContext(), "Parentesco: "+parentesco[position] ,Toast.LENGTH_SHORT).show();
        parentSelect="papa";
        if(position==0){
            parentSelect = "papa";
        }else if (position==1){
            parentSelect = "mama";
        }else if (position==2){
            parentSelect = "tio";
        }else if (position==3){
            parentSelect = "tia";
        }else if (position==4){
            parentSelect = "abuelo";
        }else if (position==5){
            parentSelect = "abuela";
        }else if (position==6){
            parentSelect = "hermano";
        }else if (position==7){
            parentSelect = "hermana";
        }else if (position==8){
            parentSelect = "primo";
        }else if (position==9){
            parentSelect = "prima";
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
    private void newIntenten(){
        Intent intent = new Intent(getApplicationContext(), codigo.class);
        startActivity(intent);
    }

    private void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String nombre2 = nombre.getText().toString();
        String correo2 = correo.getText().toString();
        String parentesco= String.valueOf(parentSelect);
        //ServicioPersonaF servicioPersonaF = new ServicioPersonaF(this, "https://arkangelapp.herokuapp.com/users/parent", nombre2, correo2, parentesco,id_user);
        //servicioPersonaF.execute();
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            url= new URL("https://arkangelapp.herokuapp.com/users/parent");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("name",nombre2);
            parametrosPost.put("email",correo2);
            parametrosPost.put("parentezco",parentesco);
            parametrosPost.put("id_kid",id_user);

            OutputStream os = conexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int respuesta= conexion.getResponseCode();
            result= new StringBuilder();
            String json;

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
                Log.i("user",json);
                User user= User.getInstance();
                User.token =json;


                // txtUser.setText(json);


                JSONObject reader = new JSONObject(json);
                JSONArray jsonArray= new JSONArray(json);
                //   String usuario_id= jsonArray.getString(Integer.parseInt("id_usuario"));
                //   Toast.makeText( getApplicationContext(), json+"entranding", Toast.LENGTH_LONG).show();
                //  txtUser.setText(json+"");
                //  User user= User.getInstance();
                //  user.id_user=Integer.getInteger(json);


                // txtUser.setText(jsonArray.optString("id_usuario"));
              /*  for (int i=0;i<jsonArray.length();i++){

                    JSONObject obj;
                    obj = jsonArray.getJSONObject(i);
                  //  String usuario_id= obj.optString("id_usuario");
                   // Toast.makeText( getApplicationContext(), jsonArray+"entranding", Toast.LENGTH_LONG).show();
              //     txtUser.setText(usuario_id+"");
                    Toast.makeText( getApplicationContext(), usuario_id+"entranding", Toast.LENGTH_LONG).show();



                }*/

            }
        }catch (Exception e){

            //  Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }



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

}
