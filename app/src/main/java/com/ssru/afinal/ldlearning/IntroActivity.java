package com.ssru.afinal.ldlearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    private ImageView iv1;

    final String PREF_NAME = "LoginPreferences";
    final String CHECK_LOGIN = "userLogin";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv1 = (ImageView) findViewById(R.id.iv1);

        // SharedPreferences
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        boolean check = sp.getBoolean(CHECK_LOGIN, false);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv1.startAnimation(myanim);

        if(check==true){
            final Intent i = new Intent(this,MainActivity.class);
            Thread timer = new Thread(){
                public void run () {
                    try{
                        sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }else if(check==false){
            final Intent i = new Intent(this,LoginActivity.class);
            Thread timer = new Thread(){
                public void run () {
                    try{
                        sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }


    }
}
