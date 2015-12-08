package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ilyes.jobi.models.Worker;
import com.example.ilyes.jobi.others.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyes on 28/11/15.
 */
public class WorkerDataSource extends UserDataSource {

    String[] allColumns = {
            WorkerEntry._ID,
            WorkerEntry.COLUMN_NAME,
            WorkerEntry.COLUMN_EMAIL,
            WorkerEntry.COLUMN_PASSWORD,
            WorkerEntry.COLUMN_NUMERO_TEL,
            WorkerEntry.COLUMN_ADDRESS,
            WorkerEntry.COLUMN_EXP_YEAR,
            WorkerEntry.COLUMN_BIRTH_DATE,
            WorkerEntry.COLUMN_FUNCTION
    };


    public WorkerDataSource(Context context) {
        super(context);
    }


    public void create(Worker worker) {

            ContentValues values = getContentValues(worker);

            long id = database.insert(WorkerEntry.TABLE, null, values);
            worker.setId(id);

            Log.v(Util.LOG_TAG, "row id : " + id);
    }

    @NonNull
    private ContentValues getContentValues(Worker worker) {
        ContentValues values = new ContentValues();

        values.put(WorkerEntry.COLUMN_NAME, worker.getName());
        values.put(WorkerEntry.COLUMN_EMAIL, worker.getEmail());
        values.put(WorkerEntry.COLUMN_PASSWORD, worker.getPassword());
        values.put(WorkerEntry.COLUMN_NUMERO_TEL, worker.getNumeroTel());
        values.put(WorkerEntry.COLUMN_ADDRESS, worker.getAddress().getAddressAsString());
        values.put(WorkerEntry.COLUMN_EXP_YEAR, worker.getExpYears());
        values.put(WorkerEntry.COLUMN_BIRTH_DATE, worker.getBirthDateAsString());
        values.put(WorkerEntry.COLUMN_FUNCTION, worker.getFunction());
        return values;
    }

    public Worker read(long id) {
        Worker worker = new Worker();

        Cursor cursor = database.query(
                WorkerEntry.TABLE,
                allColumns,
                WorkerEntry._ID + " = " + id,
                null,
                null,
                null,
                null
        );


        if (cursor != null) {
            cursor.moveToFirst();

            worker.setId(id);
            worker.setName(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NAME)));
            worker.setEmail(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_EMAIL)));
            worker.setPassword(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_PASSWORD)));
            worker.setNumeroTel(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NUMERO_TEL)));
            worker.setAddressFromString(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_ADDRESS)));
            worker.setExpYears(cursor.getInt(cursor.getColumnIndex(WorkerEntry.COLUMN_EXP_YEAR)));
            worker.setDateTimeFromString(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_BIRTH_DATE)));
            worker.setFunction(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_FUNCTION)));

        }

        assert cursor != null;
        cursor.close();

        return worker;
    }

    public List<Worker> readAll() {
        List<Worker> workers = new ArrayList<>();



        Cursor cursor = database.query(WorkerEntry.TABLE, allColumns, null, null, null, null, null);


        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Worker worker = new Worker();

                worker.setId(cursor.getLong(cursor.getColumnIndex(WorkerEntry._ID)));
                worker.setName(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NAME)));
                worker.setEmail(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_EMAIL)));
                worker.setPassword(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_PASSWORD)));
                worker.setNumeroTel(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NUMERO_TEL)));
                worker.setAddressFromString(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_ADDRESS)));
                worker.setExpYears(cursor.getInt(cursor.getColumnIndex(WorkerEntry.COLUMN_EXP_YEAR)));
                worker.setDateTimeFromString(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_BIRTH_DATE)));
                worker.setFunction(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_FUNCTION)));


                workers.add(worker);
            }
        }
        cursor.close();
        return workers;
    }

    public void update(Worker worker) {

        ContentValues values = getContentValues(worker);

        int result = database.update(WorkerEntry.TABLE, values, WorkerEntry._ID + " = " + worker.getId(), null);

        Log.v(Util.LOG_TAG, "update reuslt : " + result);
    }

    public void delete(long id) {
        int result = database.delete(WorkerEntry.TABLE, WorkerEntry._ID + " = " + id, null);
        Log.v(Util.LOG_TAG, "delete with " + result);
    }



}
