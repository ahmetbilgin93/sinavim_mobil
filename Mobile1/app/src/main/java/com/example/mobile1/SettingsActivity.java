package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    EditText sure, puan, zorluk;
    Button setSave;
    SP sp;
    Context context=this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();

        sure.setText(sp.getStringValue(context, "sure"));
        puan.setText(sp.getStringValue(context, "puan"));
        zorluk.setText(sp.getStringValue(context, "zorluk"));

        setSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suregeldi = sure.getText().toString();
                String puangeldi = puan.getText().toString();
                String zorlukgeldi = zorluk.getText().toString();

                sp.saveString(context, "sure", suregeldi);
                sp.saveString(context, "puan", puangeldi);
                sp.saveString(context, "zorluk", zorlukgeldi);
                Toast.makeText(getApplicationContext(), "Ayarlar Kaydedildi!", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(getIntent());
            }
        });


    }

    public void init(){
        sure = (EditText) findViewById(R.id.sure);
        puan = (EditText) findViewById(R.id.puan);
        zorluk = (EditText) findViewById(R.id.zorluk);
        setSave = (Button) findViewById(R.id.setSave);
        sp = new SP();
    }
}