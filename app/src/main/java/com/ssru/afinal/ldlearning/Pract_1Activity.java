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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Pract_1Activity extends AppCompatActivity {

    /** Firebase **/
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();


    /** SharedPreferences **/
    protected static final int RESULT_SPEECH = 1;
    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    /**Orther**/
    CardView cs1, cs2, cs3, cs4, cs5;
    Button btnSend;
    int countBtn, percent ;
    int checkBtn1=0, checkBtn2=0, checkBtn3=0, checkBtn4=0, checkBtn5=0;
    double score1=0, score2=0, score3=0, score4=0, score5=0, sum=0 ,per=0 ;
    String user,Chapter;


    public Pract_1Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pract_1);

        cs1 = findViewById(R.id.Speech1_1);
        cs2 = findViewById(R.id.Speech1_2);
        cs3 = findViewById(R.id.Speech1_3);
        cs4 = findViewById(R.id.Speech1_4);
        cs5 = findViewById(R.id.Speech1_5);
        btnSend = findViewById(R.id.btn_send1);

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

        cs5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countBtn =5;
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
                        new AlertDialog.Builder(Pract_1Activity.this);
                builder.setMessage("ยืนยันการส่งคำตอบ?");
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Chapter = "Chapter1";
                        sum = score1+score2+score3+score4+score5;
                        per = (sum/5)*100;
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
                        mDataRef.child(user).child(Chapter).child(key).child("cha4").setValue(String.valueOf(score4));
                        mDataRef.child(user).child(Chapter).child(key).child("cha5").setValue(String.valueOf(score5));
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
                        if (text.contains("กอไก่")){
                            checkBtn1=1;
                            score1=1;
                            Toast.makeText(Pract_1Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();


                        }else if (text.contains("กอ")||text.contains("ไก่")||text.contains("กไก่")||text.contains("กกไก่") ){
                            checkBtn1=1;
                            score1=0.5;
                            Toast.makeText(Pract_1Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();




                        }else {
                            checkBtn1=1;
                            score1=0;
                            Toast.makeText(Pract_1Activity.this,"ผิด",Toast.LENGTH_SHORT).show();

                        }

                        /** 2 **/
                    }else if (countBtn == 2 && checkBtn2==0 ){
                        if (text.contains("พอสำเภา")){
                            checkBtn2=1;
                            score2=1;
                            Toast.makeText(Pract_1Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();



                        }else if (text.contains("พอ")||text.contains("สำเภา")||text.contains("พ่อสำเภา")){
                            checkBtn2=1;
                            score2=0.5;
                            Toast.makeText(Pract_1Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();



                        }else{
                            checkBtn2=1;
                            score2=0;
                            Toast.makeText(Pract_1Activity.this,"ผิด",Toast.LENGTH_SHORT).show();




                        }

                        /** 3 **/
                    }else if (countBtn == 3 && checkBtn3==0 ){
                        if (text.contains("ถอถุง")){

                            checkBtn3=1;
                            score3=1;
                            Toast.makeText(Pract_1Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();

                        }else if (text.contains("ถอ")||text.contains("ถุง")||text.contains("ขอถุง")){

                            checkBtn3=1;
                            score3=0.5;
                            Toast.makeText(Pract_1Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();

                        }else {
                            checkBtn3=1;
                            score2=0;
                            Toast.makeText(Pract_1Activity.this,"ผิด",Toast.LENGTH_SHORT).show();
                        }

                        /** 4 **/
                    }else if (countBtn == 4 && checkBtn4==0 ){
                        if (text.contains("ดอชะดา")||text.contains("ดอชฏา")||text.contains("ดอ-ชฏา")||text.contains("ดอชดา")){

                            checkBtn4=1;
                            score4=1;
                            Toast.makeText(Pract_1Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();

                        }else if (text.contains("ดอ")||text.contains("ชะดา")||text.contains("ชฎา")||text.contains("ชดา")){

                            checkBtn4=1;
                            score4=0.5;
                            Toast.makeText(Pract_1Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();
                        } else {

                        checkBtn4=1;
                        score4=0;
                        Toast.makeText(Pract_1Activity.this,"ผิด",Toast.LENGTH_SHORT).show();

                        }
                        /** 5 **/
                    }else if (countBtn == 5 && checkBtn5==0 ){
                        if (text.contains("ตอปะตัก") ){

                            checkBtn5=1;
                            score5=1;
                            Toast.makeText(Pract_1Activity.this,"ถูกต้อง",Toast.LENGTH_SHORT).show();


                        }else if (text.contains("ตอ")||text.contains("ปะตัก")||text.contains("ปฏัก")){

                            checkBtn5=1;
                            score5=0.5;
                            Toast.makeText(Pract_1Activity.this,"ใกล้เคียง",Toast.LENGTH_SHORT).show();

                        }else {

                            checkBtn5=1;
                            score5=0;
                            Toast.makeText(Pract_1Activity.this,"ผิด",Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                break;
            }

        }
    }
}
