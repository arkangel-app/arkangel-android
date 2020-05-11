package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkangel.arkangel.R;
import com.arkangel.arkangel.Clases.User;

public class codigo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        ImageView next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        TextView compartir = findViewById(R.id.compartir);
         final TextView txtCodigo= findViewById(R.id.codigo);
         User user=User.getInstance();
         Log.i("token", User.token +"");
         txtCodigo.setText(User.token);

        compartir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@hotmail.com");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Arkangel - Persona favorita");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hola!, te he agregado como mi persona favorita en Arkangel app. \n\n Tu codigo es: " +txtCodigo.getText()+ "\n\nDescarga la aplicación en:\n\nhttps://play.google.com/store/apps/details?id=com.arkangel.arkangel");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });



    }
}
