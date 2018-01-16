package com.ssru.afinal.ldlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class PracticeActivity extends AppCompatActivity {

    CardView c1, c2, c3, c4, c5, c6, c7, c8, c9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        c1 =  findViewById(R.id.card1);
        c2 =  findViewById(R.id.card2);
        c3 =  findViewById(R.id.card3);
        c4 =  findViewById(R.id.card4);
        c5 =  findViewById(R.id.card5);
        c6 =  findViewById(R.id.card6);
        c7 =  findViewById(R.id.card7);
        c8 =  findViewById(R.id.card8);
        c9 =  findViewById(R.id.card9);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(PracticeActivity.this,Pract_1Activity.class);
                startActivity(a);

            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(PracticeActivity.this,Pract_2Activity.class);
                startActivity(b);

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(PracticeActivity.this,Pract_3Activity.class);
                startActivity(c);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(PracticeActivity.this,Pract_4Activity.class);
                startActivity(d);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(PracticeActivity.this,Pract_5Activity.class);
                startActivity(e);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(PracticeActivity.this, Pract_6Activity.class);
                  startActivity(f);
              }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(PracticeActivity.this, Pract_7Activity.class);
                startActivity(g);
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(PracticeActivity.this, Pract_8Activity.class);
                startActivity(h);
            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PracticeActivity.this, Pract_9Activity.class);
                startActivity(i);
            }
        });


    }

}