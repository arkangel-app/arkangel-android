package com.arkangel.arkangel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.arkangel.arkangel.Activitys.options_user;
import com.arkangel.arkangel.Clases.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Created by Eddy on 14/01/2018.
 */

public class ServicioTask extends AsyncTask<Void, Void, String> {
    //variables del hilo
    private final Context httpContext;//contexto
    private ProgressDialog progressDialog;//dialogo cargando
    private String linkrequestAPI="";//link  para consumir el servicio rest
    private String email="";
    private String password="";
    //constructor del hilo (Asynctask)
    public ServicioTask(Context ctx, String linkAPI, String email, String  password ){
        this.httpContext=ctx;
        this.linkrequestAPI=linkAPI;
        this.email=email;
        this.password=password;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(httpContext, "Procesando Solicitud", "por favor, espere");
    }

    @Override
    protected String doInBackground(Void... params) {
        String result= null;

        String wsURL = linkrequestAPI;
        URL url = null;
        try {
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //crear el objeto json para enviar por POST
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("email",email);
            parametrosPost.put("password",password);

            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTENER EL RESULTADO DEL REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();
            String json;
            int responseCode=urlConnection.getResponseCode();// conexion OK?
            if(responseCode== HttpURLConnection.HTTP_OK){
                BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder sb= new StringBuilder("");
                String linea="";
                while ((linea=in.readLine())!= null){
                    sb.append(linea);
                    break;

                }
                in.close();
                result= sb.toString();
                json=result;
                JSONObject reader = new JSONObject(json);


               // JSONArray jsonArray=new JSONArray(json);
                Log.i("idUserDartos","si lo nuestro");
                Log.i("idUserDartos",json+"");
                reader=reader.getJSONObject("user");
                Log.i("idUserDartos",reader+"");
                //   String usuario_id= jsonArray.getString(Integer.parseInt("id_usuario"));
                //Toast.makeText( httpContext, json+"entranding", Toast.LENGTH_LONG).show();
                //  txtUser.setText(json+"");
                //  User user= User.getInstance();
                //  user.id_user=Integer.getInteger(json);
                String usuario_id= reader.optString("id_usuario");
                Log.i("UsuarioId",usuario_id+"");
                User user=User.getInstance();
                User.id_user =usuario_id;
                // txtUser.setText(jsonArray.optString("id_usuario"));


                Intent intent = new Intent(httpContext.getApplicationContext(), options_user.class);
                 httpContext.startActivity(intent);
            }
            else{
                result= new String("ERROR: "+ responseCode);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        //Toast.makeText(httpContext,resultadoapi,Toast.LENGTH_LONG).show();//mostrara una notificacion con el resultado del request
    }

    //FUNCIONES----------------------------------------------------------------------
    //Transformar JSON Obejct a String *******************************************
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


