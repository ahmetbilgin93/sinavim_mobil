package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ExamActivity extends AppCompatActivity {

    EditText sure, puan, zorluk, sayi;
    Button setSave2;
    SP sp;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        init();

        sure.setText(sp.getStringValue(context, "sure"));
        puan.setText(sp.getStringValue(context, "puan"));
        zorluk.setText(sp.getStringValue(context, "zorluk"));


        setSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suregeldi = sure.getText().toString();
                String puangeldi = puan.getText().toString();
                String zorlukgeldi = zorluk.getText().toString();
                String sayigeldi = sayi.getText().toString();

                sp.saveString(context, "sure", suregeldi);
                sp.saveString(context, "puan", puangeldi);
                sp.saveString(context, "zorluk", zorlukgeldi);
                sp.saveString(context, "sayi", sayigeldi);

                Intent intent=new Intent(ExamActivity.this, NewExamActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        sure = (EditText) findViewById(R.id.sure2);
        puan = (EditText) findViewById(R.id.puan2);
        zorluk = (EditText) findViewById(R.id.zorluk2);
        setSave2 = (Button) findViewById(R.id.setSave2);
        sayi = (EditText) findViewById(R.id.sayi);
        sp = new SP();
    }
}