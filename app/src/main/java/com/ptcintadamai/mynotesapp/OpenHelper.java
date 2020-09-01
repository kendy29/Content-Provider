package com.ptcintadamai.mynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String NAME_DB="test";
    public static final String TABLE_NAME="note";
    String id="id";
    String title="title";
    String description="description";
    String date="date";
    String SQL_CREATE_NOTE=String.format("CREATE TABLE %s" +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL)",
            TABLE_NAME,id,title,description,date);

    public OpenHelper(@Nullable Context context) {
        super(context, NAME_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public long insert(String _title,String _description,String _date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(title,_title);
        cv.put(description,_description);
        cv.put(date,_date);
        return db.insert(TABLE_NAME,null,cv);
    }
    public Cursor getData(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(sql,null);
    }
    public long update(String _id,String _title,String _description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(title,_title);
        cv.put(description,_description);
        return db.update(TABLE_NAME,cv,id+" = ?",new String[]{_id});
    }
    public long delete(String _id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, id + " = ?", new String[]{_id});
    }
}
