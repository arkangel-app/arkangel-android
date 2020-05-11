package com.arkangel.arkangel.Clases;

import android.os.StrictMode;
import android.util.Log;

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
public class SaveDairyHumor {

    public SaveDairyHumor() {
        User user = User.getInstance();
        Sentimiento sentimiento = Sentimiento.getInstance();
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
    public void save(){
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String kid_id= User.id_user;
        String animo_id= Sentimiento.animo_id;
        String causa_id= Sentimiento.causa_id;
        String mensaje= Sentimiento.mensaje;
        Boolean send_parent= Sentimiento.send_parent;

        try {
            url= new URL("https://arkangelapp.herokuapp.com/emociones/save_dairy_emotion");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("kid_id",kid_id);
            parametrosPost.put("causa_id",causa_id);
            parametrosPost.put("animo_id",animo_id);
            parametrosPost.put("mensaje",mensaje);
            parametrosPost.put("send_parent",send_parent);

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
    public void saveSimple(){
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String kid_id= User.id_user;
        String animo_id= Sentimiento.animo_id;

        try {
            url= new URL("https://arkangelapp.herokuapp.com/emociones/save_dairy_simple_emotion");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("kid_id",kid_id);

            parametrosPost.put("animo_id",animo_id);

            parametrosPost.put("send_parent",true);

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
}
