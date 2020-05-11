package com.arkangel.arkangel.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arkangel.arkangel.Clases.Sentimiento;
import com.arkangel.arkangel.R;

public class sentimientos extends AppCompatActivity implements View.OnTouchListener {
    private ImageView ruleta;
    private double mCurrAngle = 0;

    private static final float HALF_SECTOR = 360f / 6f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentimientos);
        Sentimiento sentimiento = Sentimiento.getInstance();
        ruleta=(ImageView)findViewById(R.id.ruletaTextoImg);
        ruleta.setOnTouchListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
        Intent intent = new Intent(getApplicationContext(),options_user.class);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = ruleta.getWidth() / 2;
        final float yc = ruleta.getHeight() / 2;

        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                ruleta.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                TextView txtSentimiento = (TextView) findViewById(R.id.txtSentimiento);
                double mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle);
                //System.out.println(mCurrAngle);
                if(mCurrAngle <=43 && mCurrAngle>=-14){
                    Sentimiento.animo_id ="1";
                    txtSentimiento.setText("Triste");
                }else if(mCurrAngle <=109 && mCurrAngle>=44){
                    Sentimiento.animo_id ="2";
                    txtSentimiento.setText("Feliz");
                }else if(mCurrAngle <=173 && mCurrAngle>=110){
                    Sentimiento.animo_id ="3";
                    txtSentimiento.setText("Enojado");
                }else if(mCurrAngle <=-126 && mCurrAngle>=-179){
                    Sentimiento.animo_id ="4";
                    txtSentimiento.setText("Aburrido");
                }else if(mCurrAngle <=-70 && mCurrAngle>=-124){

                    txtSentimiento.setText("Emergencia");
                }else if(mCurrAngle <=-16 && mCurrAngle>=-69){
                    Sentimiento.animo_id ="5";
                    txtSentimiento.setText("Nervioso");
                }
                break;
            }
            case MotionEvent.ACTION_UP : {
                //mPrevAngle = mCurrAngle = 0;
                if(mCurrAngle <=43 && mCurrAngle>=-14){
                    Intent intent = new Intent(getBaseContext(), causas.class);
                    startActivity(intent);
                }else if(mCurrAngle <=109 && mCurrAngle>=44){
                    Intent intent = new Intent(getBaseContext(), feliz.class);
                    startActivity(intent);
                }else if(mCurrAngle <=173 && mCurrAngle>=110){
                    Intent intent = new Intent(getBaseContext(), enojado.class);
                    startActivity(intent);
                }else if(mCurrAngle <=-126 && mCurrAngle>=-179){
                    Intent intent = new Intent(getBaseContext(), AburridoActivity.class);
                    startActivity(intent);
                }else if(mCurrAngle <=-70 && mCurrAngle>=-124){
                    Intent intent = new Intent(getBaseContext(), emergencia.class);
                    startActivity(intent);
                }else if(mCurrAngle <=-16 && mCurrAngle>=-69){
                    Intent intent = new Intent(getBaseContext(), nervioso.class);
                    startActivity(intent);
                }
                break;
            }
        }
        return true;
    }

    private void animate(double fromDegrees, double toDegrees) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration((long) 0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        ruleta.startAnimation(rotate);
        //System.out.println(mCurrAngle);
    }
}
