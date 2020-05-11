package com.arkangel.arkangel;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.arkangel.arkangel.Activitys.actividad;
import com.arkangel.arkangel.Activitys.sentimientos;

public class tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ImageView ruleta = findViewById(R.id.ruletaTextoImg);
        RotateAnimation rotate = new RotateAnimation(0, 265, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.56f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        ruleta.startAnimation(rotate);

        //ObjectAnimator anim = ObjectAnimator.ofFloat(ruleta,"rotation",270);
        //anim.setDuration(3000);
        //anim.start();


        //ruleta.animate().rotationBy(360f).setDuration(4500).setInterpolator(new LinearInterpolator()).start();



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
                Intent intent = new Intent(getBaseContext(), sentimientos.class);
                startActivity(intent);

            }
        });

    }
}
