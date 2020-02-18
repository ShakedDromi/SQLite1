package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sqlite.GradesC.GRADE;
import static com.example.sqlite.GradesC.QUARTER;
import static com.example.sqlite.STUDENTS.ADDRESS;
import static com.example.sqlite.STUDENTS.DAD_NAME;
import static com.example.sqlite.STUDENTS.DAD_NUM;
import static com.example.sqlite.STUDENTS.HOME_P;
import static com.example.sqlite.STUDENTS.KEY_ID;
import static com.example.sqlite.STUDENTS.MOM_NAME;
import static com.example.sqlite.STUDENTS.NAME;
import static com.example.sqlite.STUDENTS.PHONE;
import static com.example.sqlite.STUDENTS.TABLE_USERS;
import static com.example.sqlite.Users.MOM_NUM;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GRADES ="Grades";
    String strCreate = "CREATE TABLE " + GradesC.TABLE_GRADES, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_USERS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+ADDRESS+" TEXT,";
        strCreate+=" "+PHONE+" INTEGER";
        strCreate+=" "+HOME_P+" INTEGER";
        strCreate+=" "+MOM_NAME+" TEXT,";
        strCreate+=" "+MOM_NUM+" INTEGER";
        strCreate+=" "+DAD_NAME+" TEXT,";
        strCreate+=" "+DAD_NUM+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE " + TABLE_GRADES;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+QUARTER+" INTEGER";
        strCreate+=" "+GRADE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        strDelete = "DROP TABLE IF EXISTS "+ TABLE_USERS;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS "+ TABLE_GRADES;
        db.execSQL(strDelete);

        onCreate(db);

    }
}