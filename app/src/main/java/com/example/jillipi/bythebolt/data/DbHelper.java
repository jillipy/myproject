package com.example.jillipi.bythebolt.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by jpyle on 1/13/2015.
 * class that handles the creation,
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "entries.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + DbContract.DbEntry.TABLE_NAME + " (" +
                    DbContract.DbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbContract.DbEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DbContract.DbEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    DbContract.DbEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    DbContract.DbEntry.COLUMN_NAME_YARDS + TEXT_TYPE + COMMA_SEP +
                    DbContract.DbEntry.COLUMN_NAME_PIC + TEXT_TYPE + COMMA_SEP +
                    DbContract.DbEntry.COLUMN_NAME_DETAILS_PIC + TEXT_TYPE +
                    ")";

    private static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.DbEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL(DELETE_ENTRIES);
    }

}
