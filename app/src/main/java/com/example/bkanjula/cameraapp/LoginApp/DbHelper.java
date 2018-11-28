package com.example.bkanjula.cameraapp.LoginApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "EmployeeDetails";
    public static final int DB_VERSION = 1;

    public static final String KEY_ROWID = "id";
    public static final String TABLE_NAME = "EmployeeTable";
    public static final String EMP_ID = "emp_id";
    public static final String EMP_NAME = "emp_name";
    public static final String EMP_EMAIL = "emp_email";
    public static final String EMP_IMAGE = "emp_image" ;

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ROWID + " integer primary key autoincrement,"
            + EMP_ID + " TEXT,"
            + EMP_NAME + " TEXT,"
            + EMP_EMAIL + " TEXT,"
            + EMP_IMAGE + " TEXT"+")";


    public DbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
