package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.ilyes.jobi.model.Worker;
import com.example.ilyes.jobi.other.Util;

/**
 * Created by ilyes on 28/11/15.
 */
public class WorkerDataSource extends UserDataSource {

    public WorkerDataSource(Context context) {
        super(context);
    }

    public void insert(Worker worker) {

        ContentValues values = new ContentValues();

        values.put(WorkerEntry.COLUMN_NAME, worker.getName());
        values.put(WorkerEntry.COLUMN_EMAIL, worker.getEmail());
        values.put(WorkerEntry.COLUMN_PASSWORD, worker.getPassword());
        values.put(WorkerEntry.COLUMN_NUMERO_TEL, worker.getNumeroTel());
        values.put(WorkerEntry.COLUMN_ADDRESS, worker.getAddress().getAddressAsString());
        values.put(WorkerEntry.COLUMN_EXP_YEAR, worker.getExpYears());
        values.put(WorkerEntry.COLUMN_BIRTH_DATE, worker.getBirthDateAsString());
        values.put(WorkerEntry.COLUMN_FUNCTION, worker.getFunction());
        long id = database.insert(WorkerEntry.TABLE, null, values);
        worker.setId(id);

        Log.v(Util.LOG_TAG, "row id : " + id);
    }


}
