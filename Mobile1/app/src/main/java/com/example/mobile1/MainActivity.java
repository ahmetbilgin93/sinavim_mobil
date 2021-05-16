package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnSignup;
    SP sp;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = new SP();
        if(sp.getIntValue(context, "userid")==-1){



        btnLogin = findViewById(R.id.button7);
        btnSignup = findViewById(R.id.button8);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanicilar (id INTEGER PRIMARY KEY, isim VARCHAR, mail VARCHAR, pass VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS sorular (sid INTEGER PRIMARY KEY, soru VARCHAR, dogru VARCHAR, y1 VARCHAR, y2 VARCHAR, y3 VARCHAR, y4 VARCHAR, uid INT)");

        }catch(Exception e) {
            e.printStackTrace();
        }

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        );

        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        }

        );
        }else{
            Intent intent=new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }



}