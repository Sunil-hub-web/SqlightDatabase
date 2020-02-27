package com.example.sqlightdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DataBaseName = "Student.db";
    public static final String TableName = "eduction";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "MOBILE";
    public static final String COL5 = "PASSWORD";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableName + " " + "(ID INTEGER PRIMARY KEY,"
                + "NAME TEXT,EMAIL TEXT,MOBILE TEXT,PASSWORD TEXT)");

        /*db.execSQL("create table " + TableName +" " +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"NAME TEXT,EMAIL TEXT ,MOBILE TEXT,PASSWORD TEXT)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }

    public boolean insertData(String name, String email, String mobile, String password) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, mobile);
        contentValues.put(COL5, password);

        Long res = sqLiteDatabase.insert(TableName, null, contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAlldata() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TableName, null);
        return res;
    }

    public boolean updateData(String id, String name, String email, String mobile, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, mobile);
        contentValues.put(COL5, password);

        sqLiteDatabase.update(TableName, contentValues, "ID= ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TableName, "ID =?", new String[]{id});
    }

}

