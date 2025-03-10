package com.example.notesmy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteHelper extends SQLiteOpenHelper {

    private static final String DATABASE="DATABASE";
    private static final int VERSION=2;


    public NoteHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table my_table(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT)");






    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {



        db.execSQL("drop table if exists my_table");




    }

    public void insertData(String title,String description){


        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("description",description);
        database.insert("my_table",null,values);








    }


    public Cursor showData(){

        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();

        Cursor cursor= sqLiteDatabase.rawQuery("select * from my_table",null);

        return cursor;



    }








}
