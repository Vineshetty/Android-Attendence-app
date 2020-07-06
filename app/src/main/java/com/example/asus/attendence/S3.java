package com.example.asus.attendence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class S3 extends SQLiteOpenHelper {
    public static String DB_NAME = "studentDB";
    public S3(Context context) {

        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student1 ( name,usn primary key, branch,semsec )");
        db.execSQL("create table CSE6a(name,usn primary key,CN,ST,CD,BI)");
        db.execSQL("create table CSE6d(name,usn,primary key,CN,ST,cd,BI)");
        db.execSQL("create table CSE6b(name,usn primary key,CN,ST,CD,BI)");
        db.execSQL("create table CSE6c(name,usn,primary key,CN,ST,cd,BI)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student1");
        db.execSQL("drop table if exists cse6a");
        db.execSQL("drop table if exists cse6d");
        db.execSQL("drop table if exists cse6b");
        db.execSQL("drop table if exists cse6c");
        onCreate(db);
    }
}
