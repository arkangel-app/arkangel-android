package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private final String[] sexo = { "Masculino", "Femenino"};
    private Button iniciar;
    private EditText nombre;
    private EditText edad;
    private EditText correo;
    private EditText contrasena;
    private char sexoSelect;
    TextView txtUser;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        contrasena = findViewById(R.id.contrasena);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        Button siguiente = findViewById(R.id.siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consumirServicio();
                   newIntenten();
              /*  Intent intent = new Intent(getBaseContext(), persona_favorita.class);
                startActivity(intent);*/
            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), "Sexo: "+sexo[position] ,Toast.LENGTH_SHORT).show();
        if(position==0){
            sexoSelect = 'M';
        }else{
            sexoSelect = 'F';
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
    private void newIntenten(){
        Intent intent = new Intent(getBaseContext(), persona_favorita.class);
       // intent.putExtra("id_user",4);

        startActivity(intent);
    }

    private void consumirServicio(){
        // ahora ejecutaremos el hilo creado
       /* String name= nombre.getText().toString();
        String sexo= String.valueOf(sexoSelect);
        Integer age= Integer.parseInt(edad.getText().toString());
        String email= correo.getText().toString();
        String password= contrasena.getText().toString();
        ServicioRegister servicioRegister= new ServicioRegister(this,"https://arkangelapp.herokuapp.com/users/register",name,age,sexo,email,password);
        servicioRegister.execute();*/
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String name= nombre.getText().toString();
        String sexo= String.valueOf(sexoSelect);
        Integer age= Integer.parseInt(edad.getText().toString());
        String email= correo.getText().toString();
        String password= contrasena.getText().toString();

        try {
            url= new URL("https://arkangelapp.herokuapp.com/users/register");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("name",name);
            parametrosPost.put("age",age);
            parametrosPost.put("sexo",sexo);
            parametrosPost.put("email",email);
            parametrosPost.put("password",password);

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
                User.id_user =json;


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
