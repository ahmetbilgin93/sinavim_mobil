package com.example.mobile1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(Context context) {
        super(context, "sinavim", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS kullanicilar (id INTEGER PRIMARY KEY, isim VARCHAR, mail VARCHAR, pass VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS sorular (sid INTEGER PRIMARY KEY, soru VARCHAR, dogru VARCHAR, y1 VARCHAR, y2 VARCHAR, y3 VARCHAR, y4 VARCHAR, uid INT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

    public int deleteSoru(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("sorular", "sid=?", new String []{String.valueOf(id)});


    }
}
