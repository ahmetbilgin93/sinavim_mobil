package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewQuestionActivity extends AppCompatActivity {

    private EditText soru, dogru, y1, y2, y3, y4;
    private Button yukle;
    private String question, correct, w1, w2, w3, w4;
    SP sp;
    Context context=this;

    private void init(){
        yukle = findViewById(R.id.soruyukle);
        soru = (EditText) findViewById(R.id.soru);
        dogru = (EditText) findViewById(R.id.dogru);
        y1=(EditText)findViewById(R.id.y1);
        y2=(EditText)findViewById(R.id.y2);
        y3=(EditText)findViewById(R.id.y3);
        y4=(EditText)findViewById(R.id.y4);
    }

    private void addQuestion(String soru, String dogru, String y1, String y2, String y3, String y4, int id){

            try {
                SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
                database.execSQL("CREATE TABLE IF NOT EXISTS sorular (sid INTEGER PRIMARY KEY, soru VARCHAR, dogru VARCHAR, y1 VARCHAR, y2 VARCHAR, y3 VARCHAR, y4 VARCHAR, uid INT)");

                String sqlsorgu = "INSERT INTO sorular (soru, dogru, y1, y2, y3, y4, uid) VALUES(?, ?, ?, ?, ?, ?, ?)";
                SQLiteStatement states = database.compileStatement(sqlsorgu);
                states.bindString(1, soru);
                states.bindString(2, dogru);
                states.bindString(3, y1);
                states.bindString(4, y2);
                states.bindString(5, y3);
                states.bindString(6, y4);
                states.bindLong(7, sp.getIntValue(context, "userid"));
                states.execute();

            }catch(Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        init();
        sp=new SP();

        int uid = 123456789;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            uid = bundle.getInt("uid");
        }


        int finalUid = uid;

        yukle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                question = soru.getText().toString();
                correct = dogru.getText().toString();
                w1 = y1.getText().toString();
                w2 = y2.getText().toString();
                w3 = y3.getText().toString();
                w4 = y4.getText().toString();

                if(!TextUtils.isEmpty(question)){
                    if(!TextUtils.isEmpty(correct)){
                        if(!TextUtils.isEmpty(w1)){
                            if(!TextUtils.isEmpty(w2)){
                                if(!TextUtils.isEmpty(w3)){
                                    if(!TextUtils.isEmpty(w4)){
                                        //Kayıt
                                        addQuestion(question, correct, w1, w2, w3, w4, finalUid);
                                        soru.setText("");
                                        dogru.setText("");
                                        y1.setText("");
                                        y2.setText("");
                                        y3.setText("");
                                        y4.setText("");
                                        Toast.makeText(getApplicationContext(), "Soru Eklendi!", Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(getIntent());
                                    }else Toast.makeText(getApplicationContext(), "Cevap Boş olamaz", Toast.LENGTH_SHORT).show();
                                }else Toast.makeText(getApplicationContext(), "Cevap Boş olamaz", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getApplicationContext(), "Cevap Boş olamaz", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getApplicationContext(), "Cevap Boş olamaz", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getApplicationContext(), "Cevap Boş olamaz", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Soru Boş olamaz", Toast.LENGTH_SHORT).show();
            }
        });
    }
}