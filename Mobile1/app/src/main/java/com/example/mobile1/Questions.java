package com.example.mobile1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {

    RecyclerView recyclerView;
    SP sp;
    soruAdapter adapter;
    ArrayList<sorular> soruListesi = new ArrayList<>();
    Context context=this;
    int kulid;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        sp=new SP();

        recyclerView = findViewById(R.id.recview);

        //  LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //   layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS sorular (sid INTEGER PRIMARY KEY, soru VARCHAR, dogru VARCHAR, y1 VARCHAR, y2 VARCHAR, y3 VARCHAR, y4 VARCHAR, uid INT)");
            Cursor cursor = database.rawQuery("SELECT * FROM sorular", null);

            while (cursor.moveToNext()) {
                //if(cursor.getInt(7)==finalUid){
                String s = cursor.getString(1);
                String d = cursor.getString(2);
                int i = cursor.getInt(0);
                String yy1 = cursor.getString(3);
                String yy2 = cursor.getString(4);
                String yy3 = cursor.getString(5);
                String yy4 = cursor.getString(6);
                //Toast.makeText(getApplicationContext(), "asgsa"+cursor.getInt(7)+" = " +sp.getIntValue(context, "userid"), Toast.LENGTH_SHORT).show();
               if(cursor.getInt(7)==sp.getIntValue(context, "userid"))
                soruListesi.add(new sorular(s, d,  yy1, yy2, yy3, yy4, i));
                // }
            }
           /* Toast.makeText(getApplicationContext(), "saga  "+ soruListesi.get(1).getSoru(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "saga  "+ soruListesi.get(0).getSoru(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "saga  "+ soruListesi.get(2).getSoru(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "saga  "+ soruListesi.get(2).getCevap(), Toast.LENGTH_SHORT).show();*/


            adapter = new soruAdapter(soruListesi, this);
            // soruAdapter(soruListesi, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //adapter.setOnItemClickListener(new soruAdapter.onItemClickListener() {
        adapter.setOnItemClickListener(new soruAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(sorular soru) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Questions.this);

                        builder.setTitle("Soru Silinecek!");
                        builder.setMessage("Emin misin?");
                        builder.setCancelable(true);
                        builder.setNegativeButton("Hayır", null);
                        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteS(soru.getSoruid());
                                Intent intent = getIntent();
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        builder.show();
                    }

            @Override
            public void onEditClick(sorular soru) {

                sp = new SP();
                sp.saveString(context, "editsoru", soru.getSoru());
                sp.saveString(context, "editcevap", soru.getCevap());
                sp.saveString(context, "edity1", soru.getY1());
                sp.saveString(context, "edity2", soru.getY2());
                sp.saveString(context, "edity3", soru.getY3());
                sp.saveString(context, "edity4", soru.getY4());
                sp.saveInt(context, "editid", soru.getSoruid());
                Intent intent=new Intent(Questions.this, EditActivity.class);
                startActivity(intent);
            }
        });

    }
    public int deleteS(int id){
        SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
        return database.delete("sorular", "sid=?", new String []{String.valueOf(id)});


    }
    }








/*
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanicilar (id INTEGER PRIMARY KEY, isim VARCHAR, mail VARCHAR, pass VARCHAR)");
            Cursor cursor = database.rawQuery("SELECT * FROM kullanicilar", null);
            if (cursor.getCount()==0){
                Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
            }
            else{
               /* while (cursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(), "İsim:"+cursor.getString(0), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Soyisim:"+cursor.getString(1), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Mail:"+cursor.getString(2), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Şifre:"+cursor.getString(3), Toast.LENGTH_SHORT).show();

                }*/

            /*    if (cursor.moveToNext()){
                    String adi= cursor.getString(2);
                    tv1.setText(adi);
                }

           }*/

      //  }*/catch(Exception e) {
      //      e.printStackTrace();
   //   }






