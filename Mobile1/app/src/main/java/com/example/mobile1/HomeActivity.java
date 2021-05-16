package com.example.mobile1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnNew;
    Button btnAll;
    Button btnExam;
    Button btnSettings;
    Button btnExit;
    TextView wlcm;
    SP sp;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = new SP();
        btnNew= findViewById(R.id.button3);
        btnAll = findViewById(R.id.button4);
        btnExam = findViewById(R.id.button5);
        btnSettings = findViewById(R.id.button6);
        btnExit = findViewById(R.id.cik);
        wlcm = (TextView) findViewById(R.id.welcome);
        int uid = 123456789;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            uid = bundle.getInt("uid");
        }


        int finalUid = uid;
        btnNew.setOnClickListener(new View.OnClickListener(){

                                        @Override
                                        public void onClick(View v) {
                                            Intent intent=new Intent(HomeActivity.this, NewQuestionActivity.class);
                                            intent.putExtra("uid", finalUid);
                                            startActivity(intent);
                                        }
                                    }
        );

        btnAll.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent=new Intent(HomeActivity.this, Questions.class);
                                          intent.putExtra("uid", finalUid);
                                          startActivity(intent);
                                      }
                                  }
        );

        btnExam.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent=new Intent(HomeActivity.this, ExamActivity.class);
                                          intent.putExtra("uid", finalUid);
                                          startActivity(intent);
                                      }
                                  }
        );

        btnSettings.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent=new Intent(HomeActivity.this, SettingsActivity.class);
                                          intent.putExtra("uid", finalUid);
                                          startActivity(intent);
                                      }
                                  }
        );

        btnExit.setOnClickListener(new View.OnClickListener(){

                                           @Override
                                           public void onClick(View v) {

                                               sp.clear(context);
                                               Intent intent=new Intent(HomeActivity.this, MainActivity.class);
                                               startActivity(intent);
                                           }
                                       }
        );

        wlcm.setText("Hoş Geldin " + sp.getStringValue(context, "username") + "!");


    }
}