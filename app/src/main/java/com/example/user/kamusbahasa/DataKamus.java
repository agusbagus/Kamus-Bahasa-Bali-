package com.example.user.kamusbahasa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataKamus extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbkamus";
    public static final String INDONESIA = "indonesia";
    public static final String BALI = "bali";
    public static final String KETERANGAN = "keterangan";


    public DataKamus(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public void createTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS kamus");
        db.execSQL("CREATE TABLE if not exists kamus(id INTEGER PRIMARY KEY AUTOINCREMENT, indonesia TEXT, bali TEXT, keterangan TEXT);");
    }


    public void generateData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(INDONESIA, "terimakasih");
        cv.put(BALI, "matursukseme");
        cv.put(KETERANGAN, "Matursukseme adalah ucapan terimakasih ");
        db.insert("kamus", BALI, cv);

        cv.put(INDONESIA, "jalan");
        cv.put(BALI, "Margi");
        cv.put(KETERANGAN, "margi arti jalan ini bahasa bali alus");
        db.insert("kamus", BALI, cv);

        cv.put(INDONESIA, "duduk");
        cv.put(BALI, "melinggih");
        cv.put(KETERANGAN, "melinggih artinya adalah duduk dalam bahasa alus bali");
        db.insert("kamus", BALI, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
}