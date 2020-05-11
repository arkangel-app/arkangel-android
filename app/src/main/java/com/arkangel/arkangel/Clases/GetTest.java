package com.arkangel.arkangel.Clases;

import android.os.StrictMode;
import android.util.Log;

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

public class GetTest {
    public GetTest(){

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
    public void saveTest(){
        URL url = null;
        String linea = "";
        //final int respuesta=0;
        StringBuilder result = null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Question obQuestion=null;
        try {
            url = new URL("https://arkangelapp.herokuapp.com/emociones/test_result");

            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost = new JSONObject();
            User user=User.getInstance();
            String id= user.id_user;
            TestQuestion tq=TestQuestion.getInstance();

            parametrosPost.put("kid_id", id);
            parametrosPost.put("r1", tq.r1);
            parametrosPost.put("r2", tq.r2);
            parametrosPost.put("r3", tq.r3);
            parametrosPost.put("r4", tq.r4);
            parametrosPost.put("r5", tq.r5);
            parametrosPost.put("r6", tq.r6);
            parametrosPost.put("r7", tq.r7);
            parametrosPost.put("r8", tq.r8);
            parametrosPost.put("r9", tq.r9);
            parametrosPost.put("r10", tq.r10);
            parametrosPost.put("r11", tq.r11);
            parametrosPost.put("r12", tq.r12);
            parametrosPost.put("r13", tq.r13);
            parametrosPost.put("r14", tq.r14);
            parametrosPost.put("r15", tq.r15);
            parametrosPost.put("r16", tq.r16);
            parametrosPost.put("r17", tq.r17);






            OutputStream os = conexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int respuesta = conexion.getResponseCode();
            result = new StringBuilder();
            String json;
            Log.i("Response Sentimiento", respuesta + "antes");
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));

                while (((linea = buf.readLine()) != null)) {
                    result.append(linea);
                    // Toast.makeText( getApplicationContext(), result.toString()+"entranding", Toast.LENGTH_LONG).show();

                    //   txtUser.setText(result.toString());
                }
                json = result.toString();
                buf.close();
                Log.i("Response Sentimiento", json);


                // txtUser.setText(json);


                JSONObject reader = new JSONObject(json);
                String resultado = reader.optString("result");
                tq.resultado=resultado;

                Log.i("resultado_test", tq.resultado);
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
        } catch (Exception e) {

            //  Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public Question getQuestion(int id) {
        URL url = null;
        String linea = "";
        //final int respuesta=0;
        StringBuilder result = null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Question obQuestion=null;
        try {
            url = new URL("https://arkangelapp.herokuapp.com/emociones/get_question_by_id");

            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost = new JSONObject();
            parametrosPost.put("id_question", id);



            OutputStream os = conexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int respuesta = conexion.getResponseCode();
            result = new StringBuilder();
            String json;
            Log.i("Response Sentimiento", respuesta + "antes");
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));

                while (((linea = buf.readLine()) != null)) {
                    result.append(linea);
                    // Toast.makeText( getApplicationContext(), result.toString()+"entranding", Toast.LENGTH_LONG).show();

                    //   txtUser.setText(result.toString());
                }
                json = result.toString();
                buf.close();
                Log.i("Response Sentimiento", json);


                // txtUser.setText(json);


                JSONObject reader = new JSONObject(json);
                String idQ = reader.optString("id_question");
                String question= reader.optString("question");
                int real_test_post= reader.optInt("real_test_pos");
                String personality_profile= reader.optString("personality_profile");
                String value = reader.getString("value");
                String status= reader.getString("status");
                 obQuestion = new Question(idQ,question,real_test_post,personality_profile,status,value);
                Log.i("question",obQuestion.toString());
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
        } catch (Exception e) {

            //  Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return obQuestion;

    }
}
