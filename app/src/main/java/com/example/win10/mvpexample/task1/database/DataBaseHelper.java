package com.example.win10.mvpexample.task1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.win10.mvpexample.task1.content.Cities;

import java.util.List;


/**
 * @author Artur Vasilov
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myDataBase";

    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyLog", "Таблица создана");
        db.execSQL(Requests.CREATION_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Requests.DROP_REQUEST);
        onCreate(db);
    }


    public interface Columns {
        String PAGE = "page";
        String CITY_NAME = "city_name";
    }

    public interface Requests {

        String TABLE_NAME = "MyTable";

        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.PAGE + " INTEGER, " +
                Columns.CITY_NAME + " TEXT" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
