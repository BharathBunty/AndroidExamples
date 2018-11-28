package com.example.bkanjula.cameraapp.LoginApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class DataBaseHelper {

    DbHelper dbHelper;
    SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void openDB() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB() {
        db.close();
    }

    public long insertData(EmployeeDetails employeeDetails) throws SQLiteException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.EMP_NAME, employeeDetails.getName());
        contentValues.put(DbHelper.EMP_ID, employeeDetails.getId());
        contentValues.put(DbHelper.EMP_EMAIL, employeeDetails.getEmail());
        contentValues.put(DbHelper.EMP_IMAGE, employeeDetails.getImage());
        long row_id = db.insert(DbHelper.TABLE_NAME, null, contentValues);
        return row_id;
    }


    public ArrayList<EmployeeDetails> getData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "select * from " + DbHelper.TABLE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList<EmployeeDetails> storedetails = new ArrayList<>();
        // looping through all rows and adding to listset
        if (c.moveToFirst()) {
            do {
                EmployeeDetails t = new EmployeeDetails();
                t.setName(c.getString((c.getColumnIndex(DbHelper.EMP_NAME))));
                t.setEmail(c.getString((c.getColumnIndex(DbHelper.EMP_EMAIL))));
                t.setId(c.getString((c.getColumnIndex(DbHelper.EMP_ID))));
                t.setImage(c.getString((c.getColumnIndex(DbHelper.EMP_IMAGE))));
                t.setKeyRowid(c.getString(c.getColumnIndex(DbHelper.KEY_ROWID)));

                storedetails.add(t);
                // adding to tags list
            } while (c.moveToNext());
        }


        return storedetails;
    }

    public int updateData(Integer id, EmployeeDetails employeeDetails) throws SQLiteException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_ROWID,employeeDetails.getKeyRowid());
        contentValues.put(DbHelper.EMP_NAME, employeeDetails.getName());
        contentValues.put(DbHelper.EMP_EMAIL, employeeDetails.getEmail());
        contentValues.put(DbHelper.EMP_ID, employeeDetails.getId());
        contentValues.put(DbHelper.EMP_IMAGE, employeeDetails.getImage());
//        db.update(DbHelper.TABLE_NAME , contentValues, DbHelper.KEY_ROWID + "=" + id , null);
        return db.update(DbHelper.TABLE_NAME,contentValues, DbHelper.KEY_ROWID  + " = ?",
                new String[] { String.valueOf(employeeDetails.getKeyRowid() )});
//        db.update(DbHelper.TABLE_NAME, contentValues,
//                DbHelper.KEY_ROWID + "=?", new String[]{String.valueOf(id)});


//        String selectQuery = "SELECT  * FROM " + DbHelper.TABLE_NAME + " WHERE " + DbHelper.KEY_ROWID + " = " + id;
//
//        /*Log.e(LOG, selectQuery);*/
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        EmployeeDetails t = new EmployeeDetails();
//        // looping through all rows and adding to listset
//        if (c.moveToFirst()) {
//            do {
//
//                t.setName(c.getString((c.getColumnIndex(DbHelper.EMP_NAME))));
//                t.setEmail(c.getString((c.getColumnIndex(DbHelper.EMP_EMAIL))));
//                t.setId(c.getString((c.getColumnIndex(DbHelper.EMP_ID))));
//                t.setImage(c.getString((c.getColumnIndex(DbHelper.EMP_IMAGE))));
//
//
//
//                // adding to tags list
//            } while (c.moveToNext());
//        }
//        return t;

    }

    public void deleteEntry(EmployeeDetails id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(DbHelper.TABLE_NAME,DbHelper.KEY_ROWID  + " = ?",
                new String[] { String.valueOf(id.getKeyRowid() )});
        db.close();

    }
}
