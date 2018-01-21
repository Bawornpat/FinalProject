package com.ssru.afinal.ldlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ConsonantActivity extends AppCompatActivity {

    CardView con1 ,con2 , con3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consonant);

        con1 = findViewById(R.id.Con_one);
        con2 = findViewById(R.id.Con_two);
        con3 = findViewById(R.id.Con_three);

        con1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ConsonantActivity.this,ListCharActivity.class);
                startActivity(a);
            }
        });

        con2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ConsonantActivity.this,PracticeActivity.class);
                startActivity(b);
            }
        });

        con3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(ConsonantActivity.this,PracticeActivity.class);
                startActivity(c);
            }
        });

    }
}
