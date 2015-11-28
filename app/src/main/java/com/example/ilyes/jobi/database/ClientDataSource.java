package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.ilyes.jobi.model.Client;
import com.example.ilyes.jobi.other.Util;

/**
 * Created by ilyes on 28/11/15.
 */
public class ClientDataSource extends UserDataSource{

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

}
