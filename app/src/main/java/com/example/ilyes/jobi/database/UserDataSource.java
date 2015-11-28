package com.example.ilyes.jobi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ilyes.jobi.other.Util;

/**
 * Created by ilyes on 28/11/15.
 */
abstract public class UserDataSource {


    SQLiteOpenHelper helper;
    SQLiteDatabase database;

    public UserDataSource(Context context) {
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


}
