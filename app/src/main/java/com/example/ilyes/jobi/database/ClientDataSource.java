package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ilyes.jobi.model.Client;
import com.example.ilyes.jobi.other.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyes on 28/11/15.
 */
public class ClientDataSource extends UserDataSource{

    private String[] allColumns = {
            ClientEntry._ID,
            ClientEntry.COLUMN_NAME,
            ClientEntry.COLUMN_EMAIL,
            ClientEntry.COLUMN_PASSWORD,
            ClientEntry.COLUMN_NUMERO_TEL,
            ClientEntry.COLUMN_ADDRESS
    };

    public ClientDataSource(Context context) {
        super(context);
    }


    public void insert(Client client) {

        ContentValues values = new ContentValues();

        values.put(ClientEntry.COLUMN_NAME, client.getName());
        values.put(ClientEntry.COLUMN_EMAIL, client.getEmail());
        values.put(ClientEntry.COLUMN_PASSWORD, client.getPassword());
        values.put(ClientEntry.COLUMN_NUMERO_TEL, client.getNumeroTel());
        values.put(ClientEntry.COLUMN_ADDRESS, client.getAddress().getAddressAsString());
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
            client.setName(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_NAME)));
            client.setEmail(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_EMAIL)));
            client.setPassword(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_PASSWORD)));
            client.setNumeroTel(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_NUMERO_TEL)));
            client.setAddressFromString(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_ADDRESS)));
        }

        return client;
    }


    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();



        Cursor cursor = database.query(ClientEntry.TABLE, allColumns, null, null, null, null, null);


        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Client client = new Client();

                client.setId(cursor.getLong(cursor.getColumnIndex(ClientEntry._ID)));
                client.setName(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_NAME)));
                client.setEmail(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_EMAIL)));
                client.setPassword(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_PASSWORD)));
                client.setNumeroTel(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_NUMERO_TEL)));
                client.setAddressFromString(cursor.getString(cursor.getColumnIndex(ClientEntry.COLUMN_ADDRESS)));
                clients.add(client);
            }
        }

        return clients;
    }

}
