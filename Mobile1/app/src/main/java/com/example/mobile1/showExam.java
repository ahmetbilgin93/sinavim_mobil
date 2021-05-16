package com.example.mobile1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class showExam extends AppCompatActivity {

    private static final String FILE_NAME = "exam.txt"  ;

    TextView textView;
    EditText editText;
    Button gonder, getir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam);

        textView = (TextView) findViewById(R.id.showExam);
        editText = (EditText) findViewById(R.id.filetext);
        gonder = (Button) findViewById(R.id.savefile);
        getir = (Button) findViewById(R.id.getfiletext);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(text.getBytes());

                    editText.getText().clear();
                    Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        getir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;


                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String readingtext;
                   // while((readingtext = br.readLine()) != null){
                        //br.readLine();
                      //  sb.append(readingtext).append("\n");
                  //  }
                    Toast.makeText(getApplicationContext(), "Saved to " + br.readLine(),Toast.LENGTH_LONG).show();
                    editText.setText(br.readLine());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });




    }




}