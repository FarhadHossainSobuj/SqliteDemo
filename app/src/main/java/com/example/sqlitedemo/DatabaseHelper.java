package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "phonebook.db";
    private static final int VERSION = 2;

    private static final String TABLE_NAME = "contact";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + NUMBER + " TEXT, " + EMAIL + " TEXT)");
        Log.d("DatabaseCreate", "Done");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
        Log.d("DatabaseCreate", "onUpgrade Done");
    }
    public boolean insert( String name, String number, String email){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("number", number);
        values.put("email", email);

        long ok = db.insert(TABLE_NAME, null, values);
        if(ok == -1){
            return false;
        } else {
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);

    }
    public Cursor getSingleData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME + " where id=" + id, null);

    }

    public int updateData(String id, String name, String number, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("number", number);
        values.put("email", email);
        int ok = db.update(TABLE_NAME, values, "id="+id, null);
        //boolean ok = false;
        return ok;

    }
}
