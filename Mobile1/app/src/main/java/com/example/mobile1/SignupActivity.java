package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^.{6,}");

    private TextView tv1;
    private Button btnOk;
    private EditText signName, signMail, signPass1, signPass2;
    private String name, mail, pass1, pass2;
    SP sp;
    Context context=this;

    private void init(){
        tv1 = (TextView) findViewById(R.id.textView);
        btnOk = findViewById(R.id.button);
        signMail = (EditText) findViewById(R.id.signMail);
        signName = (EditText) findViewById(R.id.signName);
        signPass1=(EditText)findViewById(R.id.signPass1);
        signPass2=(EditText)findViewById(R.id.signPass2);
        sp = new SP();
    }

    private int adduser(String isim, String posta, String sifre){
        int cntrl=checkUser(posta);

        if(cntrl==-1){
            Toast.makeText(getApplicationContext(), "Hata db", Toast.LENGTH_SHORT).show();
            return -1;
        }else if(cntrl==0){
            return 0;
        }else{


        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);

            String sqlsorgu = "INSERT INTO kullanicilar (isim, mail, pass) VALUES(?, ?, ?)";
            SQLiteStatement states = database.compileStatement(sqlsorgu);
            states.bindString(1, isim);
            states.bindString(2, posta);
            states.bindString(3, sifre);
            states.execute();




            return cntrl;

        }catch(Exception e) {
            e.printStackTrace();
            return -1;
        }}
    }

    private int checkUser(String posta){
        int activeid=0;
        try {
            String dene = posta;
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanicilar (id INTEGER PRIMARY KEY, isim VARCHAR, mail VARCHAR, pass VARCHAR)");
                Cursor cursor = database.rawQuery("SELECT * FROM kullanicilar ", null);
            while (cursor.moveToNext()) {

                if(cursor.getString(2).equals(posta)){
                    return 0;
                }
                else {
                    activeid=cursor.getInt(0);
                }


            }
            return activeid;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        tv1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });



        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mail = signMail.getText().toString();
                name = signName.getText().toString();
                pass1 = signPass1.getText().toString();
                pass2 = signPass2.getText().toString();

                if(!TextUtils.isEmpty(name)){
                    if(!TextUtils.isEmpty(mail)) {
                        if (Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                            if (TextUtils.equals(pass1, pass2)) {
                                if(!TextUtils.isEmpty(pass1)){
                                    if(PASSWORD_PATTERN.matcher(pass1).matches()){
                                        //Kayıt

                                        int uyeMi = adduser(name, mail, pass1);
                                        if (uyeMi == 0){
                                            Toast.makeText(getApplicationContext(), "Girmeye çalıştığınız Mail Sistemed Kayıtlı", Toast.LENGTH_SHORT).show();
                                        }else{
                                            uyeMi=uyeMi+1;
                                            sp.saveInt(context, "userid", uyeMi);
                                            sp.saveString(context, "username", name);
                                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                            intent.putExtra("uid", uyeMi);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                                        }
                                        
                                        

                                    }else{
                                        Toast.makeText(getApplicationContext(), "Şifre En Az 6 Karakter Olmalı!", Toast.LENGTH_SHORT).show();
                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(), "Şifre Boş Olamaz!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Şifreler Aynı Değil!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Mail Formatı Uygunsuz!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Mail Boş Olamaz!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "İsim Boş Olamaz!", Toast.LENGTH_SHORT).show();
                }


            }

                                 }
        );
    }
}