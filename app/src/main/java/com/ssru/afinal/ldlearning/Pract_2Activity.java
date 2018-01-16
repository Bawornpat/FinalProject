package com.ssru.afinal.ldlearning;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Pract_2Activity extends AppCompatActivity {

    /** Firebase **/
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();


    /** SharedPreferences **/
    protected static final int RESULT_SPEECH = 1;
    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";
    final String KEY_REMEMBER = "RememberUsername";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    /**Orther**/
    CardView cs1, cs2, cs3, cs4;
    Button btnSend;
    int countBtn, percent ;
    int checkBtn1=0, checkBtn2=0, checkBtn3=0, checkBtn4=0;
    double score1=0, score2=0, score3=0, score4=0, sum ,per ;
    String user,Chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pract_2);

        cs1 = findViewById(R.id.Speech2_1);
        cs2 = findViewById(R.id.Speech2_2);
        cs3 = findViewById(R.id.Speech2_3);
        cs4 = findViewById(R.id.Speech2_4);

        btnSend = findViewById(R.id.btn_send2);

        /** SharedPreferences **/
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        user = sp.getString(KEY_USERNAME, "");


        /** set **/
        cs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countBtn = 1;

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

                try {
                    startActivityForResult(intent, RESULT_SPEECH);

                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        cs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countBtn =2;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

                try {
                    startActivityForResult(intent, RESULT_SPEECH);

                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countBtn =3;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

                try {
                    startActivityForResult(intent, RESULT_SPEECH);

                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countBtn =4;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

                try {
                    startActivityForResult(intent, RESULT_SPEECH);

                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Pract_2Activity.this);
                builder.setMessage("ยืนยันการส่งคำตอบ?");
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                "ส่งคำตอบเรียบร้อย", Toast.LENGTH_SHORT).show();

                        Chapter = "Chapter2";
                        sum = score1+score2+score3+score4;
                        per = (sum/4)*100;
                        percent = (int)per;

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss");
                        Date date = new Date();
                        String strDate = dateFormat.format(date).toString();

                        DatabaseReference mDataRef = mRootRef.child("Practice");
                        String key = mDataRef.push().getKey();
                        mDataRef.child(user).child(Chapter).child(key).child("cha1").setValue(String.valueOf(score1));
                        mDataRef.child(user).child(Chapter).child(key).child("cha2").setValue(String.valueOf(score2));
                        mDataRef.child(user).child(Chapter).child(key).child("cha3").setValue(String.valueOf(score3));
                        mDataRef.child(user).child(Chapter).child(key).child("cha4").setValue(String.valueOf(score4));
                        mDataRef.child(user).child(Chapter).child(key).child("sum").setValue(String.valueOf(sum));
                        mDataRef.child(user).child(Chapter).child(key).child("percent").setValue(String.valueOf(percent));
                        mDataRef.child(user).child("Percent").child("Sum").child(key).child("results").setValue(String.valueOf(percent));
                        mDataRef.child(user).child("Date").setValue(strDate);


                        finish();

                    }
                });
                builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }

    /** Speech to Text && set to Database **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = String.valueOf(arrayList);

                    /** 1 **/
                    if (countBtn == 1 && checkBtn1==0 ){
                        if (text.contains("ขอไข่")){
                            checkBtn1=1;
                            score1=1;
                            Toast.makeText(Pract_2Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();


                        }else if (text.contains("ขอ")||text.contains("ไข่")||text.contains("ขาไก่")){
                            checkBtn1=1;
                            score1=0.5;
                            Toast.makeText(Pract_2Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();




                        }else {
                            checkBtn1=1;
                            score1=0;
                            Toast.makeText(Pract_2Activity.this,"ผิด",Toast.LENGTH_SHORT).show();

                        }

                        /** 2 **/
                    }else if (countBtn == 2 && checkBtn2==0 ){
                        if (text.contains("ขอขวด")||text.contains("คอขวด")){
                            checkBtn2=1;
                            score2=1;
                            Toast.makeText(Pract_2Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();



                        }else if (text.contains("ขอ")||text.contains("ขวด")){
                            checkBtn2=1;
                            score2=0.5;
                            Toast.makeText(Pract_2Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();



                        }else{
                            checkBtn2=1;
                            score2=0;
                            Toast.makeText(Pract_2Activity.this,"ผิด",Toast.LENGTH_SHORT).show();




                        }

                        /** 3 **/
                    }else if (countBtn == 3 && checkBtn3==0 ){
                        if (text.contains("ชอช้าง")){

                            checkBtn3=1;
                            score3=1;
                            Toast.makeText(Pract_2Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();

                        }else if (text.contains("ชอ")||text.contains("ช้าง")){

                            checkBtn3=1;
                            score3=0.5;
                            Toast.makeText(Pract_2Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();

                        }else {
                            checkBtn3=1;
                            score2=0;
                            Toast.makeText(Pract_2Activity.this,"ผิด",Toast.LENGTH_SHORT).show();
                        }

                        /** 4 **/
                    }else if (countBtn == 4 && checkBtn4==0 ){
                        if (text.contains("ซอโซ่")){

                            checkBtn4=1;
                            score4=1;
                            Toast.makeText(Pract_2Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();

                        }else if (text.contains("ซอ")||text.contains("โซ่")){

                            checkBtn4=1;
                            score4=0.5;
                            Toast.makeText(Pract_2Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();
                        } else {

                            checkBtn4=1;
                            score4=0;
                            Toast.makeText(Pract_2Activity.this,"ผิด",Toast.LENGTH_SHORT).show();

                        }

                    }
                }
                break;
            }

        }
    }
}
