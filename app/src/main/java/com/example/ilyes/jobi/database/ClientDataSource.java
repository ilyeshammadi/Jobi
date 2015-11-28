package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ilyes.jobi.model.Client;
import com.example.ilyes.jobi.other.Util;

/**
 * Created by ilyes on 28/11/15.
 */
public class ClientDataSource extends UserDataSource{

    private String[] allColumns = {
            WorkerEntry.COLUMN_NAME,
            WorkerEntry.COLUMN_EMAIL,
            WorkerEntry.COLUMN_PASSWORD,
            WorkerEntry.COLUMN_NUMERO_TEL,
            WorkerEntry.COLUMN_ADDRESS
    };

    public ClientDataSource(Context context) {
        super(context);
    }


    public void insert(Client client) {

        ContentValues values = new ContentValues();

        values.put(WorkerEntry.COLUMN_NAME, client.getName());
        values.put(WorkerEntry.COLUMN_EMAIL, client.getEmail());
        values.put(WorkerEntry.COLUMN_PASSWORD, client.getPassword());
        values.put(WorkerEntry.COLUMN_NUMERO_TEL, client.getNumeroTel());
        values.put(WorkerEntry.COLUMN_ADDRESS, client.getAddress().getAddressAsString());
        long id = database.insert(ClientEntry.TABLE, null, values);
        client.setId(id);

        Log.v(Util.LOG_TAG, "row id : " + id);
    }


    public Client get(long id) {
        Client client = new Client();

        Cursor cursor = database.query(
                ClientEntry.TABLE,
                allColumns,
                ClientEntry._ID + " = " + id,
                null,
                null,
                null,
                null
        );


        if (cursor != null) {
            cursor.moveToFirst();

            client.setId(id);
            client.setName(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NAME)));
            client.setEmail(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_EMAIL)));
            client.setPassword(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_PASSWORD)));
            client.setNumeroTel(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_NUMERO_TEL)));
            client.setAddressFromString(cursor.getString(cursor.getColumnIndex(WorkerEntry.COLUMN_ADDRESS)));
        }

        return client;
    }

}
