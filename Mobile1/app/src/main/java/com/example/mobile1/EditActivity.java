package com.example.mobile1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText dSoru, dCevap, dY1, dY2, dY3, dY4;
    Button dButton;
    Context context =this;
    SP sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        init();
        dSoru.setText(sp.getStringValue(context, "editsoru"));
        dCevap.setText(sp.getStringValue(context, "editcevap"));
        dY1.setText(sp.getStringValue(context, "edity1"));
        dY2.setText(sp.getStringValue(context, "edity2"));
        dY3.setText(sp.getStringValue(context, "edity3"));
        dY4.setText(sp.getStringValue(context, "edity4"));


        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSoru(dSoru.getText().toString(),dCevap.getText().toString(),  dY1.getText().toString(), dY2.getText().toString(), dY3.getText().toString(), dY4.getText().toString(), sp.getIntValue(context, "editid"));
                Intent intent=new Intent(EditActivity.this, Questions.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "DÜZENLENDİ!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void init(){
        dSoru = (EditText) findViewById(R.id.düzenleSoru);
        dCevap = (EditText) findViewById(R.id.düzenleDogru);
        dY1 = (EditText) findViewById(R.id.düzenleY1);
        dY2 = (EditText) findViewById(R.id.düzenleY2);
        dY3 = (EditText) findViewById(R.id.düzenleY3);
        dY4 = (EditText) findViewById(R.id.düzenleY4);
        dButton = (Button) findViewById(R.id.sorulariDuzenle);
        sp = new SP();
    }

    public void editSoru(String s, String c, String y1, String y2, String y3, String y4, int id){
        ContentValues cv = new ContentValues();
        cv.put("soru",s); //These Fields should be your String values of actual column names
        cv.put("dogru",c);
        cv.put("y1",y1);
        cv.put("y2",y2);
        cv.put("y3",y3);
        cv.put("y4",y4);
        
        SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
        database.update("sorular", cv, "sid = ?", new String[]{String.valueOf(id)});
    }
}