package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;

import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arkangel.arkangel.R;
import com.arkangel.arkangel.ServicioTask;

public class login extends AppCompatActivity  {

    private EditText correo;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button iniciar = findViewById(R.id.iniciar);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);

        iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consumirServicio();
            }
        });

    }

    private void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String email= correo.getText().toString();
        String password= contrasena.getText().toString();
        ServicioTask servicioTask= new ServicioTask(this,"https://arkangelapp.herokuapp.com/users/login",email,password);
        servicioTask.execute();
    }

}



