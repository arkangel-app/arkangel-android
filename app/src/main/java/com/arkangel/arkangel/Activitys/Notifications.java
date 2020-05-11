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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.arkangel.arkangel.Adapters.CustomListAdapter;
import com.arkangel.arkangel.Clases.ListItem;
import com.arkangel.arkangel.Clases.User;
import com.arkangel.arkangel.R;

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
import java.util.ArrayList;
import java.util.Iterator;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        //noinspection unchecked
        lv.setAdapter(new CustomListAdapter(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext(), "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();

                ImageView next = findViewById(R.id.next);

                        Intent intent = new Intent(getBaseContext(), notificationSelected.class);
                        intent.putExtra("id_notification",user.getId());
                        startActivity(intent);
            }
        });

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

    private ArrayList getListData() {
        ArrayList<ListItem> results = new ArrayList<>();
    /*    ListItem user1 = new ListItem();
        user1.setName("Daniel");
        user1.setDesignation("01/01/2020");
        user1.setLocation("necesita un abrazo");
        results.add(user1);

        ListItem user2 = new ListItem();
        user2.setName("Rohini Alavala");
        user2.setDesignation("Agricultural Officer");
        user2.setLocation("Guntur");
        results.add(user2);

        ListItem user3 = new ListItem();
        user3.setName("Trishika Dasari");
        user3.setDesignation("Charted Accountant");
        user3.setLocation("Guntur");
        results.add(user3);*/
        URL url= null;
        String linea="";
        //final int respuesta=0;
        StringBuilder result=null;
        HttpURLConnection conexion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        User user=User.getInstance();
        String id= User.id_user;

        Log.i("ResponseJson",id);


        try {
            url= new URL("https://arkangelapp.herokuapp.com/emociones/list_notifications_by_kid");

            conexion= (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("id_kid",id);
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
                JSONArray jsonArray= new JSONArray(json);
                  for (int i=0;i<jsonArray.length();i++){

                    JSONObject obj;
                     obj = jsonArray.getJSONObject(i);
                     ListItem user2 = new ListItem();
                     user2.setId(obj.optString("id_parent_notification"));
                     user2.setName(obj.optString("kid"));
                     user2.setDesignation(obj.optString("created"));
                     user2.setLocation(obj.optString("title"));
                     results.add(user2);

                }





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
        return results;

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
