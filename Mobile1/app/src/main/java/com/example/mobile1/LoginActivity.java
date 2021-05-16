package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {



    private TextView tv2;
    private Button btnOk;
    private EditText loginMail, loginPass;
    private String mail, pass;
    private String name;
    private int hata=0;
    SP sp;
    Context context=this;

    private void init(){
        loginMail=(EditText)findViewById(R.id.loginMail);
        loginPass=(EditText)findViewById(R.id.loginPass);
        tv2 = (TextView) findViewById(R.id.textView11);
        btnOk = findViewById(R.id.button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        sp = new SP();
        tv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });



        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mail = loginMail.getText().toString();
                pass = loginPass.getText().toString();
                int cntrl;

               /* if(!TextUtils.isEmpty(mail)){
                    if(!TextUtils.isEmpty(pass)){
                        //Giriş
                        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Şifre Boş Olamaz!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Mail Boş Olamaz!", Toast.LENGTH_SHORT).show();
                }*/

                cntrl=checkUser(mail, pass);
                if(cntrl==-1){
                    Toast.makeText(getApplicationContext(), "Veritabanı Hatası.", Toast.LENGTH_SHORT).show();
                }else if(cntrl==-2){
                    Toast.makeText(getApplicationContext(), "Şifre Hatalı! Hatalı Deneme Sayısı: " +hata, Toast.LENGTH_SHORT).show();
                }else if(cntrl==-3){
                    Toast.makeText(getApplicationContext(), "3.Kez Hatalı Giriş! Üyelik Ekranına Yönlendirildiniz. " +hata, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
                }else if(cntrl==-4){
                    Toast.makeText(getApplicationContext(), "Girdiğiniz Mail Sistemde Kayıtlı Değil!", Toast.LENGTH_SHORT).show();
                }else{
                    sp.saveInt(context, "userid", cntrl);
                    sp.saveString(context, "username", name);
                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }


            }
        }
        );
    }

    private int checkUser(String posta, String pass){

        try {
            String dene = posta;
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanicilar (id INTEGER PRIMARY KEY, isim VARCHAR, mail VARCHAR, pass VARCHAR)");
            Cursor cursor = database.rawQuery("SELECT * FROM kullanicilar ", null);
            while (cursor.moveToNext()) {

                if(cursor.getString(2).equals(posta)){
                    if(cursor.getString(3).equals(pass)){
                        Toast.makeText(getApplicationContext(), "Başarıyla Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                        hata=0;
                        name=cursor.getString(1);
                        return cursor.getInt(0);
                    }else if(hata<2){

                        hata++;
                        return -2;
                    }else{

                        hata=0;
                        return -3;
                    }
                }
            }
            hata++;

            return -4;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

}