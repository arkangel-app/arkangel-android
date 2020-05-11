package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.arkangel.arkangel.Clases.Sentimiento;
import com.arkangel.arkangel.R;

public class feliz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feliz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Sentimiento sentimiento = Sentimiento.getInstance();
        Button feliz1 = findViewById(R.id.feliz1);
        Button feliz2 = findViewById(R.id.feliz2);
        Button feliz3 = findViewById(R.id.feliz3);
        Button feliz4 = findViewById(R.id.feliz4);

        feliz1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sentimiento.causa_id ="5";
                Intent intent = new Intent(getBaseContext(), felizformulario.class);
                startActivity(intent);
            }
        });

        feliz2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sentimiento.causa_id ="6";
                Intent intent = new Intent(getBaseContext(), felizformulario.class);
                startActivity(intent);
            }
        });

        feliz3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sentimiento.causa_id ="7";
                Intent intent = new Intent(getBaseContext(), felizformulario.class);
                startActivity(intent);
            }
        });

        feliz4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sentimiento.causa_id ="8";
                Intent intent = new Intent(getBaseContext(), felizformulario.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.principal:
                // do your code
                Intent intent = new Intent(getBaseContext(), options_user.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                // do your code
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(getApplicationContext(),sentimientos.class);
        startActivity(intent);
        return true;
    }
}
