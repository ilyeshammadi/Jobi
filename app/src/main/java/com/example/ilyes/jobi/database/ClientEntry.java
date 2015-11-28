package com.example.ilyes.jobi.database;

import android.provider.BaseColumns;

/**
 * Created by ilyes on 28/11/15.
 */
public class ClientEntry implements BaseColumns{

    public static final String TABLE = "clients";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NUMERO_TEL = "numero_telephone";
    public static final String COLUMN_ADDRESS = "address";


}
