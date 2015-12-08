package com.example.ilyes.jobi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ilyes.jobi.models.Post;
import com.example.ilyes.jobi.others.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyes on 08/12/15.
 */
public class PostDataSource {

    SQLiteOpenHelper helper;
    SQLiteDatabase database;
    Context context;


    String[] allColumns = {
            PostEntry._ID,
            PostEntry.COLUMN_TITLE,
            PostEntry.COLUMN_TEXT,
            PostEntry.COLUMN_USER_OWNER_ID
    };


    public PostDataSource(Context context) {
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


    private ContentValues getContentValues(Post post) {
        ContentValues values = new ContentValues();

        values.put(PostEntry.COLUMN_TITLE, post.getTitle());
        values.put(PostEntry.COLUMN_TEXT, post.getText());
        values.put(PostEntry.COLUMN_USER_OWNER_ID, post.getUserOwnerId());

        return values;
    }

    public void create(Post post) {
        ContentValues values = getContentValues(post);
        long id = database.insert(PostEntry.TABLE, null, values);
        post.setId(id);

        Log.v(Util.LOG_TAG, "post row id : " + id);
    }

    public Post read(long id) {
        Post post = new Post();

        Cursor cursor = database.query(
                PostEntry.TABLE,
                allColumns,
                PostEntry._ID + " = " + id,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();

            post.setId(id);
            post.setTitle(cursor.getString(cursor.getColumnIndex(PostEntry.COLUMN_TITLE)));
            post.setText(cursor.getString(cursor.getColumnIndex(PostEntry.COLUMN_TEXT)));
            post.setUserOwnerId(cursor.getLong(cursor.getColumnIndex(PostEntry.COLUMN_USER_OWNER_ID)));

        }

        assert cursor != null;
        cursor.close();

        return post;

    }


    public List<Post> readAll(long client_id) {

        List<Post> posts = new ArrayList<>();


        Cursor cursor = database.query(
                PostEntry.TABLE,
                allColumns,
                PostEntry.COLUMN_USER_OWNER_ID + " = " + client_id,
                null,
                null,
                null,
                null
        );


        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Post post = new Post();

                post.setId(cursor.getLong(cursor.getColumnIndex(PostEntry._ID)));
                post.setTitle(cursor.getString(cursor.getColumnIndex(PostEntry.COLUMN_TITLE)));
                post.setText(cursor.getString(cursor.getColumnIndex(PostEntry.COLUMN_TEXT)));
                post.setUserOwnerId(cursor.getLong(cursor.getColumnIndex(PostEntry.COLUMN_USER_OWNER_ID)));

                posts.add(post);
            }
        }

        cursor.close();
        return posts;
    }

    public void update(Post post) {
        ContentValues values = getContentValues(post);
        int operationResult = database.update(PostEntry.TABLE, values, PostEntry._ID + "=" + post.getId(), null);
        Log.v(Util.LOG_TAG, "update post with :  " + operationResult);
    }

    public void delete(long id) {
        int result = database.delete(PostEntry.TABLE, PostEntry._ID + " = " + id, null);
        Log.v(Util.LOG_TAG, "delete post with : " + result);
    }

}
