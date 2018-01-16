package com.ssru.afinal.ldlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ssru.afinal.ldlearning.adapter.AlphabetAdapter;
import com.ssru.afinal.ldlearning.model.Alphabet;

import java.util.ArrayList;

public class ListCharActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Alphabet> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_char);

        arrayList = new ArrayList<>();



        arrayList.add(new Alphabet(R.drawable.cha1,R.raw.s1,"ก-ไก่"));
        arrayList.add(new Alphabet(R.drawable.cha2,R.raw.s2, "ข-ไข่"));
        arrayList.add(new Alphabet(R.drawable.cha3,R.raw.s3, "ฃ-ขวด"));
        arrayList.add(new Alphabet(R.drawable.cha4,R.raw.s4, "ค-ควาย"));
        arrayList.add(new Alphabet(R.drawable.cha5,R.raw.s5, "ฅ-คน"));
        arrayList.add(new Alphabet(R.drawable.cha6,R.raw.s6, "ฆ-ระฆัง"));
        arrayList.add(new Alphabet(R.drawable.cha7,R.raw.s7, "งอ-งู"));
        arrayList.add(new Alphabet(R.drawable.cha8,R.raw.s8, "จ-จาน"));
        arrayList.add(new Alphabet(R.drawable.cha9,R.raw.s9, "ฉ-ฉิ่ง"));
        arrayList.add(new Alphabet(R.drawable.cha10,R.raw.s10, "ช-ช้าง"));
        arrayList.add(new Alphabet(R.drawable.cha11,R.raw.s11, "ซ-โซ่"));
        arrayList.add(new Alphabet(R.drawable.cha12,R.raw.s12, "ฌ-กะเฌอ"));
        arrayList.add(new Alphabet(R.drawable.cha13,R.raw.s13, "ญ-หญิง"));
        arrayList.add(new Alphabet(R.drawable.cha14,R.raw.s14, "ฎ-ชฎา"));
        arrayList.add(new Alphabet(R.drawable.cha15,R.raw.s15, "ฏ-ปฏัก"));
        arrayList.add(new Alphabet(R.drawable.cha16,R.raw.s16, "ฐ-ฐาน"));
        arrayList.add(new Alphabet(R.drawable.cha17,R.raw.s17, "ฑ-มณโฑ"));
        arrayList.add(new Alphabet(R.drawable.cha18,R.raw.s18, "ฒ-ผู้เฒ่า"));
        arrayList.add(new Alphabet(R.drawable.cha19,R.raw.s19, "ณ-เณร"));
        arrayList.add(new Alphabet(R.drawable.cha20,R.raw.s20, "ด-เด็ก"));
        arrayList.add(new Alphabet(R.drawable.cha21,R.raw.s21, "ต-เต่า"));
        arrayList.add(new Alphabet(R.drawable.cha22,R.raw.s22, "ถ-ถุง"));
        arrayList.add(new Alphabet(R.drawable.cha23,R.raw.s23, "ท-ทหาร"));
        arrayList.add(new Alphabet(R.drawable.cha24,R.raw.s24, "ธ-ธง"));
        arrayList.add(new Alphabet(R.drawable.cha25,R.raw.s25, "น-หนู"));
        arrayList.add(new Alphabet(R.drawable.cha26,R.raw.s26, "บ-ใบไม้"));
        arrayList.add(new Alphabet(R.drawable.cha27,R.raw.s27, "ป-ปลา"));
        arrayList.add(new Alphabet(R.drawable.cha28,R.raw.s28, "ผ-ผึ้ง"));
        arrayList.add(new Alphabet(R.drawable.cha29,R.raw.s29, "ฝ-ฝา"));
        arrayList.add(new Alphabet(R.drawable.cha30,R.raw.s30, "พ-พาน"));
        arrayList.add(new Alphabet(R.drawable.cha31,R.raw.s31, "ฟัน-ฟัน"));
        arrayList.add(new Alphabet(R.drawable.cha32,R.raw.s32, "ภ-สำเภา"));
        arrayList.add(new Alphabet(R.drawable.cha33,R.raw.s33, "ม-ม้า"));
        arrayList.add(new Alphabet(R.drawable.cha34,R.raw.s34, "ย-ยักษ์"));
        arrayList.add(new Alphabet(R.drawable.cha35,R.raw.s35, "ร-เรือ"));
        arrayList.add(new Alphabet(R.drawable.cha36,R.raw.s36, "ล-ลิง"));
        arrayList.add(new Alphabet(R.drawable.cha37,R.raw.s37, "ว-แหวน"));
        arrayList.add(new Alphabet(R.drawable.cha38,R.raw.s38, "ศ-ศาลา"));
        arrayList.add(new Alphabet(R.drawable.cha39,R.raw.s39, "ษ-ฤๅษี"));
        arrayList.add(new Alphabet(R.drawable.cha40,R.raw.s40, "ส-เสือ"));
        arrayList.add(new Alphabet(R.drawable.cha41,R.raw.s41, "ห-หีบ"));
        arrayList.add(new Alphabet(R.drawable.cha42,R.raw.s42, "ฬ-จุฬา"));
        arrayList.add(new Alphabet(R.drawable.cha43,R.raw.s43, "อ-อ่าง"));
        arrayList.add(new Alphabet(R.drawable.cha44,R.raw.s44, "ฮ-นกฮูก"));


        recyclerView = findViewById(R.id.Re_Char);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        AlphabetAdapter alphabetAdapter = new AlphabetAdapter(this, arrayList);
        recyclerView.setAdapter(alphabetAdapter);
    }
}
