package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.arkangel.arkangel.Activitys.actividad;
import com.arkangel.arkangel.Clases.SaveDairyHumor;
import com.arkangel.arkangel.Clases.Sentimiento;
import com.arkangel.arkangel.R;

public class nervioso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nervioso);

        Sentimiento sentimiento = Sentimiento.getInstance();
        ImageView blink = findViewById(R.id.tap);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(blink, "scaleX", 0.7f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(blink, "scaleY", 0.7f);
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();
        AnimatorSet moveUp = new AnimatorSet();

        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDownX.setRepeatCount(Animation.INFINITE);
        scaleDownY.setRepeatCount(Animation.INFINITE);

        scaleDown.start();
        moveUp.start();

        blink.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Sentimiento.causa_id ="";
                    Sentimiento.mensaje ="";
                    Sentimiento.send_parent = true;
                    SaveDairyHumor obj= new SaveDairyHumor();
                    obj.saveSimple();
                Intent intent = new Intent(getBaseContext(), actividad.class);
                startActivity(intent);

            }
        });


    }
}
