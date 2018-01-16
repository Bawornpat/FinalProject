package com.ssru.afinal.ldlearning;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignupActivity extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    Button btnSignup, btnBacktoLogin;
    EditText txtUser , txtPass , txtPassCheck;
    String user,pass,passCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUser = (EditText) findViewById(R.id.txt_user);
        txtPass = (EditText) findViewById(R.id.txt_pass);
        txtPassCheck = (EditText) findViewById(R.id.txt_pass_check);

        btnSignup = (Button) findViewById(R.id.btn_signupok);
        btnBacktoLogin = (Button) findViewById(R.id.btn_backtologin);




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = txtUser.getText().toString();
                pass = txtPass.getText().toString();

                Toast.makeText(SignupActivity.this, "บันทึกค่าสำเร็จ",Toast.LENGTH_LONG).show();


                DatabaseReference mDataRef = mRootRef.child("DataUser");
                mDataRef.child(user).child("username").setValue(user);
                mDataRef.child(user).child("password").setValue(pass);

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                DatabaseReference mStartRef = mRootRef.child("Practice");
                String key = mStartRef.push().getKey();
                mStartRef.child(user).child("Chapter1").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter2").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter3").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter4").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter5").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter6").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter7").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter8").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Chapter9").child(key).child("percent").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Percent").child("Sum").child(key).child("results").setValue(String.valueOf("0"));
                mStartRef.child(user).child("Date").setValue(strDate);
                finish();
            }

        });

        btnBacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
