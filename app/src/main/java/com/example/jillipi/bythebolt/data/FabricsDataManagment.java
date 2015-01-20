package com.example.jillipi.bythebolt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jpyle on 1/13/2015.
 * This class helps manage the database
 * queires for inserting, deleting, updating, and getting the list of fabrics
 */
public class FabricsDataManagment {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    //List of all the colums in the table that we are going to query
    private String[] allColums = {DbContract.DbEntry._ID,
                                  DbContract.DbEntry.COLUMN_NAME_TITLE,
                                  DbContract.DbEntry.COLUMN_NAME_TYPE,
                                  DbContract.DbEntry.COLUMN_NAME_YARDS,
                                  DbContract.DbEntry.COLUMN_NAME_DATE,
                                  DbContract.DbEntry.COLUMN_NAME_PIC,
                                  DbContract.DbEntry.COLUMN_NAME_DETAILS_PIC};

    //Constructor
    public FabricsDataManagment(Context context) {
        dbHelper = new DbHelper(context);
    }

    //Opens connection to the database
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    //close db connection
    public void close() {
        dbHelper.close();
    }

    //Get a list of all the fabrics that are in the database no sort, no limit
    public List<Fabric> getAllFabrics() {
        List<Fabric> fabricsList = new ArrayList<Fabric>();

        Cursor cursor = database.query(DbContract.DbEntry.TABLE_NAME,allColums,null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Fabric fabric = cursorToFabric(cursor);
            fabricsList.add(fabric);
            cursor.moveToNext();
        }

        cursor.close();
        return fabricsList;
    }

    //Insert a fabric into the database
    public long insertFabric(Fabric fabric) {

        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.COLUMN_NAME_TITLE, fabric.getName());
        values.put(DbContract.DbEntry.COLUMN_NAME_TYPE, fabric.getType());
        values.put(DbContract.DbEntry.COLUMN_NAME_DATE, fabric.getDate());
        values.put(DbContract.DbEntry.COLUMN_NAME_YARDS, fabric.getYardage());

        long insertId = database.insert(DbContract.DbEntry.TABLE_NAME, null, values);

        return insertId;
    }

    //Delete a fabric from the database using the fabric object to get the ID of the fabric
    public void deleteFabric(Fabric fabric) {
        long id = fabric.getId();
        database.delete(DbContract.DbEntry.TABLE_NAME, DbContract.DbEntry._ID + "=" + id,null);
    }

    //Returns a fabric object from the DB using a ID
    public Fabric getFabricById(long id){
        Fabric returnedFabric = null;
        String where = "_id=?";
        String[] whereArgs = {Objects.toString(id)};
        Cursor cursor = database.query(DbContract.DbEntry.TABLE_NAME,allColums,where,whereArgs,null,null,null);
        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            returnedFabric = cursorToFabric(cursor);
        }

        return returnedFabric;

    }

    //Uses the ID of the fabric object to update a row in the database with the new values in the fabric object
    public void updateFabric(Fabric fabric) {
        String where = "_id=?";
        String[] whereArgs = {Objects.toString(fabric.getId())};
        //add to the ConentValues object with the fabric objects instance variables
        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.COLUMN_NAME_TITLE, fabric.getName());
        values.put(DbContract.DbEntry.COLUMN_NAME_TYPE, fabric.getType());
        values.put(DbContract.DbEntry.COLUMN_NAME_DATE, fabric.getDate());
        values.put(DbContract.DbEntry.COLUMN_NAME_YARDS, fabric.getYardage());

        int rowsAffected = database.update(DbContract.DbEntry.TABLE_NAME,values,where,whereArgs);

    }

    public void deleteData(){
        database.execSQL("DELETE FROM " + DbContract.DbEntry.TABLE_NAME);
    }

    //Takes a cursor and turns it's current location into a fabric object
    private Fabric cursorToFabric(Cursor cursor){
        Fabric fabric = new Fabric();
        fabric.setId(cursor.getLong(0));
        fabric.setName(cursor.getString(1));
        fabric.setType(cursor.getString(2));
        fabric.setDate(cursor.getString(3));
        fabric.setYardage(cursor.getString(4));

        return fabric;

    }



}
