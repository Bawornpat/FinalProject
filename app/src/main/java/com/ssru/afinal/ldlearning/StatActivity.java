package com.ssru.afinal.ldlearning;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import me.itangqi.waveloadingview.WaveLoadingView;

public class StatActivity extends AppCompatActivity {




    /** SharedPreferences **/
    protected static final int RESULT_SPEECH = 1;
    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    /** orther **/
    WaveLoadingView waveSum, w1, w2, w3, w4, w5, w6, w7, w8, w9;

    CardView Cstat1, Cstat2, Cstat3, Cstat4, Cstat5, Cstat6, Cstat7, Cstat8, Cstat9;
    Dialog d1, d2, d3, d4, d5, d6, d7, d8, d9;

    String user,getPer="Sum";
    TextView Date , Time ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);



        Cstat1 = findViewById(R.id.Cstat1);
        Cstat2 = findViewById(R.id.Cstat2);
        Cstat3 = findViewById(R.id.Cstat3);
        Cstat4 = findViewById(R.id.Cstat4);
        Cstat5 = findViewById(R.id.Cstat5);
        Cstat6 = findViewById(R.id.Cstat6);
        Cstat7 = findViewById(R.id.Cstat7);
        Cstat8 = findViewById(R.id.Cstat8);
        Cstat9 = findViewById(R.id.Cstat9);

        d1 = new Dialog(this);
        d2 = new Dialog(this);
        d3 = new Dialog(this);
        d4 = new Dialog(this);
        d5 = new Dialog(this);
        d6 = new Dialog(this);
        d7 = new Dialog(this);
        d8 = new Dialog(this);
        d9 = new Dialog(this);


        waveSum = findViewById(R.id.wavePercent);

        Date = findViewById(R.id.date);
        Time = findViewById(R.id.time);


        /** SharedPreferences **/
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        user = sp.getString(KEY_USERNAME, "");


        /** Firebase **/
        final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Practice").child(user);


        /** Timr **/
        mRootRef.child("Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String[] dateTime = value.split(",");
                Date.setText(dateTime[0]);
                Time.setText(dateTime[1]);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mRootRef.child("Percent").child("Sum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> sumPer = new ArrayList<>();


                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                    sumPer.add((String) map.get("results"));

                }



                int x = 0;
                for(int i =0;i<sumPer.size();i++){
                    x += Integer.parseInt(sumPer.get(i));
                }

                double sum1 = ((double)x);
                double sum2 = 0.0;
                if (sumPer.size() == 1){
                  sum2 = (sumPer.size())*100.0;
                }else if (sumPer.size() > 1){
                  sum2 = (sumPer.size()-1)*100.0;
                }

                int ans = (int) ((sum1/sum2)*100.0);

                String results = String.valueOf(ans);

                waveSum.setProgressValue(ans);

                if (ans <50) {
                    waveSum.setBottomTitle(String.valueOf(results+"%"));
                    waveSum.setCenterTitle(String.format(""));
                    waveSum.setTopTitle(String.format(""));
                }else if (ans <80){
                    waveSum.setBottomTitle("");
                    waveSum.setCenterTitle(String.valueOf(results+"%"));
                    waveSum.setTopTitle("");
                }else{
                    waveSum.setBottomTitle("");
                    waveSum.setCenterTitle("");
                    waveSum.setTopTitle(String.valueOf(results+"%"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        Cstat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                d1.setContentView(R.layout.pract1_pop);
                w1 = d1.findViewById(R.id.wave1);

                mRootRef.child("Chapter1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per1 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per1.add((String) map.get("percent"));


                            double x = 0.0;
                            for(int i =0;i<Per1.size();i++){
                                int y = Integer.parseInt(Per1.get(i));
                                x += y;
                            }

                            double sum1 = (x);
                            double sum2 = 0.0;
                            if (Per1.size() == 1){
                                sum2 = (Per1.size())*100.0;
                            }else if (Per1.size() > 1){
                                sum2 = (Per1.size()-1)*100.0;
                            }

                            int ans = (int) ((sum1/sum2)*100.0);
                            String results = String.valueOf(ans);

                            w1.setProgressValue(ans);

                            if (ans <50) {
                                w1.setBottomTitle(String.valueOf(results+"%"));
                                w1.setCenterTitle(String.format(""));
                                w1.setTopTitle(String.format(""));
                            }else if (ans<80){
                                w1.setBottomTitle("");
                                w1.setCenterTitle(String.valueOf(results+"%"));
                                w1.setTopTitle("");
                            }else {
                                w1.setBottomTitle("");
                                w1.setCenterTitle("");
                                w1.setTopTitle(String.valueOf(results + "%"));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                d1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d1.show();

            }
        });


        Cstat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                d2.setContentView(R.layout.pract2_pop);
                w2 = d2.findViewById(R.id.wave2);

              mRootRef.child("Chapter2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per2 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per2.add((String) map.get("percent"));


                            int x = 0;
                            for (int i = 0; i < Per2.size(); i++) {
                                int y = Integer.parseInt(Per2.get(i));
                                x += y;
                            }

                            double sum1 = ((double)x);
                            double sum2 = 0.0;
                            if (Per2.size() == 1){
                                sum2 = (Per2.size())*100.0;
                            }else if (Per2.size() > 1){
                                sum2 = (Per2.size()-1)*100.0;
                            }

                            int ans = (int) ((sum1/sum2)*100.0);
                            String results = String.valueOf(ans);

                            w2.setProgressValue(ans);

                            if (ans < 50) {
                                w2.setBottomTitle(String.valueOf(results + "%"));
                                w2.setCenterTitle(String.format(""));
                                w2.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w2.setBottomTitle("");
                                w2.setCenterTitle(String.valueOf(results + "%"));
                                w2.setTopTitle("");
                            } else {
                                w2.setBottomTitle("");
                                w2.setCenterTitle("");
                                w2.setTopTitle(String.valueOf(results + "%"));
                            }


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d2.show();


            }
        });


        Cstat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d3.setContentView(R.layout.pract3_pop);
                w3 = d3.findViewById(R.id.wave3);


                mRootRef.child("Chapter3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per3 = new ArrayList<>();


                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per3.add((String) map.get("percent"));


                            int x = 0;
                            for (int i = 0; i < Per3.size(); i++) {
                                int y = Integer.parseInt(Per3.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per3.size() == 1) {
                                sum2 = (Per3.size()) * 100.0;
                            } else if (Per3.size() > 1) {
                                sum2 = (Per3.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);


                            w3.setProgressValue(ans);

                            if (ans < 50) {
                                w3.setBottomTitle(String.valueOf(results + "%"));
                                w3.setCenterTitle(String.format(""));
                                w3.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w3.setBottomTitle("");
                                w3.setCenterTitle(String.valueOf(results + "%"));
                                w3.setTopTitle("");
                            } else {
                                w3.setBottomTitle("");
                                w3.setCenterTitle("");
                                w3.setTopTitle(String.valueOf(results + "%"));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d3.show();
            }
        });

        Cstat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d4.setContentView(R.layout.pract4_pop);
                w4 = d4.findViewById(R.id.wave4);

                mRootRef.child("Chapter4").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per4 = new ArrayList<>();


                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per4.add((String) map.get("percent"));



                            int x = 0;
                            for (int i = 0; i < Per4.size(); i++) {
                                int y = Integer.parseInt(Per4.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per4.size() == 1) {
                                sum2 = (Per4.size()) * 100.0;
                            } else if (Per4.size() > 1) {
                                sum2 = (Per4.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                            w4.setProgressValue(ans);

                            if (ans < 50) {
                                w4.setBottomTitle(String.valueOf(results + "%"));
                                w4.setCenterTitle(String.format(""));
                                w4.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w4.setBottomTitle("");
                                w4.setCenterTitle(String.valueOf(results + "%"));
                                w4.setTopTitle("");
                            } else {
                                w4.setBottomTitle("");
                                w4.setCenterTitle("");
                                w4.setTopTitle(String.valueOf(results + "%"));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d4.show();
            }
        });

        Cstat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d5.setContentView(R.layout.pract5_pop);
                w5 = d5.findViewById(R.id.wave5);


                mRootRef.child("Chapter5").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per5 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per5.add((String) map.get("percent"));


                            int x = 0;
                            for (int i = 0; i < Per5.size(); i++) {
                                int y = Integer.parseInt(Per5.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per5.size() == 1) {
                                sum2 = (Per5.size()) * 100.0;
                            } else if (Per5.size() > 1) {
                                sum2 = (Per5.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                            w5.setProgressValue(ans);

                            if (ans < 50) {
                                w5.setBottomTitle(String.valueOf(results + "%"));
                                w5.setCenterTitle(String.format(""));
                                w5.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w5.setBottomTitle("");
                                w5.setCenterTitle(String.valueOf(results + "%"));
                                w5.setTopTitle("");
                            } else {
                                w5.setBottomTitle("");
                                w5.setCenterTitle("");
                                w5.setTopTitle(String.valueOf(results + "%"));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d5.show();
            }
        });

        Cstat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d6.setContentView(R.layout.pract6_pop);
                w6 = d6.findViewById(R.id.wave6);


                mRootRef.child("Chapter6").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per6 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per6.add((String) map.get("percent"));


                            int x = 0;
                            for (int i = 0; i < Per6.size(); i++) {
                                int y = Integer.parseInt(Per6.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per6.size() == 1) {
                                sum2 = (Per6.size()) * 100.0;
                            } else if (Per6.size() > 1) {
                                sum2 = (Per6.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                            w6.setProgressValue(ans);

                            if (ans < 50) {
                                w6.setBottomTitle(String.valueOf(results + "%"));
                                w6.setCenterTitle(String.format(""));
                                w6.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w6.setBottomTitle("");
                                w6.setCenterTitle(String.valueOf(results + "%"));
                                w6.setTopTitle("");
                            } else {
                                w6.setBottomTitle("");
                                w6.setCenterTitle("");
                                w6.setTopTitle(String.valueOf(results + "%"));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d6.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d6.show();
            }
        });

        Cstat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d7.setContentView(R.layout.pract7_pop);
                w7 = d7.findViewById(R.id.wave7);

                mRootRef.child("Chapter7").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per7 = new ArrayList<>();
                        ArrayList<String> LastScore7 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per7.add((String) map.get("percent"));


                            int x = 0;
                            for (int i = 0; i < Per7.size(); i++) {
                                int y = Integer.parseInt(Per7.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per7.size() == 1) {
                                sum2 = (Per7.size()) * 100.0;
                            } else if (Per7.size() > 1) {
                                sum2 = (Per7.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                        w7.setProgressValue(ans);

                        if (ans < 50) {
                            w7.setBottomTitle(String.valueOf(results + "%"));
                            w7.setCenterTitle(String.format(""));
                            w7.setTopTitle(String.format(""));
                        } else if (ans < 80) {
                            w7.setBottomTitle("");
                            w7.setCenterTitle(String.valueOf(results + "%"));
                            w7.setTopTitle("");
                        } else {
                            w7.setBottomTitle("");
                            w7.setCenterTitle("");
                            w7.setTopTitle(String.valueOf(results + "%"));
                        }
                    }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d7.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d7.show();
            }
        });

        Cstat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d8.setContentView(R.layout.pract8_pop);
                w8 = d8.findViewById(R.id.wave8);

                mRootRef.child("Chapter8").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per8 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per8.add((String) map.get("percent"));
                            int x = 0;
                            for (int i = 0; i < Per8.size(); i++) {
                                int y = Integer.parseInt(Per8.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per8.size() == 1) {
                                sum2 = (Per8.size()) * 100.0;
                            } else if (Per8.size() > 1) {
                                sum2 = (Per8.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                            w8.setProgressValue(ans);

                            if (ans < 50) {
                                w8.setBottomTitle(String.valueOf(results + "%"));
                                w8.setCenterTitle(String.format(""));
                                w8.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w8.setBottomTitle("");
                                w8.setCenterTitle(String.valueOf(results + "%"));
                                w8.setTopTitle("");
                            } else {
                                w8.setBottomTitle("");
                                w8.setCenterTitle("");
                                w8.setTopTitle(String.valueOf(results + "%"));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                d8.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d8.show();
            }
        });

        Cstat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d9.setContentView(R.layout.pract9_pop);
                w9 = d9.findViewById(R.id.wave9);

                mRootRef.child("Chapter9").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Per9 = new ArrayList<>();

                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                            Per9.add((String) map.get("percent"));
                            int x = 0;
                            for (int i = 0; i < Per9.size(); i++) {
                                int y = Integer.parseInt(Per9.get(i));
                                x += y;
                            }

                            double sum1 = ((double) x);
                            double sum2 = 0.0;
                            if (Per9.size() == 1) {
                                sum2 = (Per9.size()) * 100.0;
                            } else if (Per9.size() > 1) {
                                sum2 = (Per9.size() - 1) * 100.0;
                            }

                            int ans = (int) ((sum1 / sum2) * 100.0);
                            String results = String.valueOf(ans);

                            w9.setProgressValue(ans);

                            if (ans < 50) {
                                w9.setBottomTitle(String.valueOf(results + "%"));
                                w9.setCenterTitle(String.format(""));
                                w9.setTopTitle(String.format(""));
                            } else if (ans < 80) {
                                w9.setBottomTitle("");
                                w9.setCenterTitle(String.valueOf(results + "%"));
                                w9.setTopTitle("");
                            } else {
                                w9.setBottomTitle("");
                                w9.setCenterTitle("");
                                w9.setTopTitle(String.valueOf(results + "%"));
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


                d9.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d9.show();
            }
        });




    }



}
