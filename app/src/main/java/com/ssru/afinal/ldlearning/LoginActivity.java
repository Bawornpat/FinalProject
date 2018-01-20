package com.ssru.afinal.ldlearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("DataUser");

    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";
    final String KEY_REMEMBER = "RememberUsername";
    final String CHECK_LOGIN = "userLogin";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private Button btnLogin, btnSingup;
    private EditText etxtUser, etxtPass;
    private CheckBox cbRemember;
    boolean userCheck, passCheck;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSingup = (Button) findViewById(R.id.btn_singup);
        etxtUser = (EditText) findViewById(R.id.etxtUsername);
        etxtPass = (EditText) findViewById(R.id.etxtPassword);
        cbRemember = (CheckBox)findViewById(R.id.cbRemember);


        // SharedPreferences
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();


        etxtUser.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) {
                editor = sp.edit();
                editor.putString(KEY_USERNAME, s.toString());
                editor.commit();
            }
        });

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(KEY_REMEMBER,isChecked);
                editor.commit();
            }
        });

        boolean isRemember = sp.getBoolean(KEY_REMEMBER, false);
        cbRemember.setChecked(isRemember);

        if(isRemember) {
            String username = sp.getString(KEY_USERNAME, "");
            etxtUser.setText(username);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CheckUser((Map<String, Object>) dataSnapshot.getValue());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(r);

            }
        });
    }



    /************************************************************************************************************************/

    private void CheckUser(Map<String, Object> value) {
        final ArrayList<String> username = new ArrayList<>();
        final ArrayList<String> password = new ArrayList<>();
        user = etxtUser.getText().toString();
        pass = etxtPass.getText().toString();


        for (Map.Entry<String, Object> entry : value.entrySet()) {

            Map Username = (Map) entry.getValue();
            Map Password = (Map) entry.getValue();

            username.add((String) Username.get("username"));
            password.add((String) Password.get("password"));
            }

            /*----------------------------------------------------------------------------------------------------------*/

            /** Check  **/
            int i;
            for (i = 0 ; i < username.size(); i++) {

                if (username.get(i).equals(user) == true) {
                    userCheck = true;
                    if (password.get(i).equals(pass)==true){
                        passCheck = true;
                    }else{
                        passCheck = false;
                    }
                }
                if (i == username.size()-1){
                    if (userCheck != true){
                        userCheck = false;
                    }
                }
            }

            if (userCheck == true) {
                if (passCheck == true){
                    Toast.makeText(LoginActivity.this, "พบ" + user + "ในระบบ || รหัสผ่านถูกต้อง " , Toast.LENGTH_SHORT).show();

                    editor.putBoolean(CHECK_LOGIN,true);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else if(passCheck == false){
                    Toast.makeText(LoginActivity.this, "พบ" + user + "ในระบบ || รหัสผ่านไม่ถูกต้อง " , Toast.LENGTH_SHORT).show();
                }



            }else if (userCheck == false){
                Toast.makeText(LoginActivity.this, "ไม่พบ" + user + "ในระบบ", Toast.LENGTH_SHORT).show();
            }



            }

        }


