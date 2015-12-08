package com.example.ilyes.jobi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ilyes.jobi.others.Util;

/**
 * Created by ilyes on 28/11/15.
 */
public class UserDbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jobi.db";
    public static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_WORKER =
            "CREATE TABLE " + WorkerEntry.TABLE + " (" +
                    WorkerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    WorkerEntry.COLUMN_NAME + " TEXT ," +
                    WorkerEntry.COLUMN_EMAIL + " TEXT ," +
                    WorkerEntry.COLUMN_PASSWORD + " TEXT ," +
                    WorkerEntry.COLUMN_NUMERO_TEL + " TEXT ," +
                    WorkerEntry.COLUMN_ADDRESS + " TEXT ," +
                    WorkerEntry.COLUMN_EXP_YEAR + " INTEGER NOT NULL ," +
                    WorkerEntry.COLUMN_BIRTH_DATE + " TEXT ," +
                    WorkerEntry.COLUMN_FUNCTION + " TEXT" +
                    ");";

    private static final String CREATE_TABLE_CLIENT =
            "CREATE TABLE " + ClientEntry.TABLE + " (" +
                    ClientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ClientEntry.COLUMN_NAME + " TEXT ," +
                    ClientEntry.COLUMN_EMAIL + " TEXT ," +
                    ClientEntry.COLUMN_PASSWORD + " TEXT ," +
                    ClientEntry.COLUMN_NUMERO_TEL + " TEXT ," +
                    ClientEntry.COLUMN_ADDRESS + " TEXT" +
                    ");";


    private static final String CREATE_TABLE_POST =
            "CREATE TABLE " + PostEntry.TABLE + " (" +
                    PostEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PostEntry.COLUMN_TITLE + " TEXT ," +
                    PostEntry.COLUMN_TEXT + " TEXT ," +
                    PostEntry.COLUMN_USER_OWNER_ID + " INTEGER NOT NULL, " +
                    " FOREIGN KEY (" + PostEntry.COLUMN_USER_OWNER_ID + ") REFERENCES " +
                    ClientEntry.TABLE + " (" + ClientEntry._ID + ")" +
                    "); ";


    public UserDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WORKER);
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_POST);

        Log.v(Util.LOG_TAG, "create database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkerEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ClientEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PostEntry.TABLE);
        onCreate(db);
    }
}
