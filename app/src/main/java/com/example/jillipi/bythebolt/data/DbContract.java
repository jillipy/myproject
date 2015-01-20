package com.example.jillipi.bythebolt.data;

import android.provider.BaseColumns;

/**
 * Created by jpyle on 1/13/2015.
 */
public final class DbContract {

    public DbContract() {}

    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "fabrics";
        //what the fabric is called
        public static final String COLUMN_NAME_TITLE = "title";
        //number of yards
        public static final String COLUMN_NAME_TYPE = "type";

        public static final String COLUMN_NAME_YARDS = "yards";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_PIC = "primary_pic";
        public static final String COLUMN_NAME_DETAILS_PIC = "details_pic";



    }


}
