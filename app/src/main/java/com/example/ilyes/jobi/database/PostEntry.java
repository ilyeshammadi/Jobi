package com.example.ilyes.jobi.database;

import android.provider.BaseColumns;

/**
 * Created by ilyes on 08/12/15.
 */
public class PostEntry implements BaseColumns {

    public static final String TABLE = "posts";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_USER_OWNER_ID = "user_owner_id";
}
