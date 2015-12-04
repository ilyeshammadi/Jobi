package com.example.ilyes.jobi.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.ilyes.jobi.other.Util;

/**
 * Created by ilyes on 28/11/15.
 */
public class UserDataSource {


    SQLiteOpenHelper helper;
    SQLiteDatabase database;
    Context context;

    public UserDataSource(Context context) {
        this.context = context;
        helper = new UserDbOpenHelper(context);

    }


    public void open() {
        Log.v(Util.LOG_TAG, "database open");
        database = helper.getWritableDatabase();
    }

    public void close() {
        Log.v(Util.LOG_TAG, "database close");
        database.close();
    }


    // TODO: 29/11/15 Fix search in one table problem 
    
    public boolean isWorkerExist(String email, String password) {
        email = "'" + email + "'";
        password = "'" + password + "'";
        
        String query = "SELECT * FROM " + WorkerEntry.TABLE +
                " WHERE " + WorkerEntry.COLUMN_EMAIL + "=" + email +
                " AND " + WorkerEntry.COLUMN_PASSWORD + "=" + password;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Worker : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isClientExist(String email, String password) {
        email = "'" + email + "'";
        password = "'" + password + "'";

        String query = "SELECT * FROM " + ClientEntry.TABLE +
                " WHERE " + ClientEntry.COLUMN_EMAIL + "=" + email +
                " AND " + ClientEntry.COLUMN_PASSWORD + "=" + password;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Client : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isUserExist(String email, String password) {
        email = "'" + email + "'";
        password = "'" + password + "'";

        String query = "SELECT * FROM " + ClientEntry.TABLE + " " + WorkerEntry.TABLE +
                " WHERE " + UserEntry.COLUMN_EMAIL + "=" + email +
                " AND " + UserEntry.COLUMN_PASSWORD + "=" + password;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Client : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isWorkerRegistred(String email) {
        email = "'" + email + "'";

        String query = "SELECT * FROM " + WorkerEntry.TABLE +
                " WHERE " + UserEntry.COLUMN_EMAIL + "=" + email;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Worker registred : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }


    public boolean isClientRegistred(String email) {
        email = "'" + email + "'";

        String query = "SELECT * FROM " + ClientEntry.TABLE +
                " WHERE " + UserEntry.COLUMN_EMAIL + "=" + email;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Client registred : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }


    public boolean isUserRegistred(String email) {
        return isClientRegistred(email) || isWorkerRegistred(email);
    }


    public long getWorkerId(String email, String password) {
        email = "'" + email + "'";
        password = "'" + password + "'";

        String query = "SELECT * FROM " + WorkerEntry.TABLE +
                " WHERE " + WorkerEntry.COLUMN_EMAIL + "=" + email +
                " AND " + WorkerEntry.COLUMN_PASSWORD + "=" + password;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Worker id : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return id;
        }
        cursor.close();
        return -1;
    }


    public long getClientId(String email, String password) {
        email = "'" + email + "'";
        password = "'" + password + "'";

        String query = "SELECT * FROM " + ClientEntry.TABLE +
                " WHERE " + ClientEntry.COLUMN_EMAIL + "=" + email +
                " AND " + ClientEntry.COLUMN_PASSWORD + "=" + password;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(UserEntry._ID));
            Toast.makeText(context, "Client id : " + id, Toast.LENGTH_SHORT).show();
            cursor.close();
            return id;
        }
        cursor.close();
        return -1;
    }



}
