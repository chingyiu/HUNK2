package com.hunkim2.testdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sean on 11/4/2016.
 */
public class MyDBHelper extends SQLiteOpenHelper{

    private final static int DB_VERSION = 1;

    private final static String DB_NAME = "User_Route.db";  //<-- db name

    public final static String TABLE_USER_ROUTE = "User_Route"; //<-- table name
    public static final String COLUMN_USER_ROUTE_ID = "_id";
    public static final String COLUMN_USER_ROUTE_START = "start_point";
    public static final String COLUMN_USER_ROUTE_END = "end_point";
    public static final String COLUMN_USER_ROUTE_ARRIVAL = "arrival_time";
    public static final String COLUMN_USER_ROUTE_REPEAT = "repeat";
    public static final String COLUMN_USER_ROUTE_MON = "mon";
    public static final String COLUMN_USER_ROUTE_TUE = "tue";
    public static final String COLUMN_USER_ROUTE_WED = "wed";
    public static final String COLUMN_USER_ROUTE_THU = "thu";
    public static final String COLUMN_USER_ROUTE_FRI = "fri";
    public static final String COLUMN_USER_ROUTE_SAT = "sat";
    public static final String COLUMN_USER_ROUTE_SUN = "sun";


    public final static String TABLE_TRIAL = "Trial"; //<-- table name
    public static final String COLUMN_TRIAL_ID = COLUMN_USER_ROUTE_ID;
    public static final String COLUMN_TRIAL_START = "start_time";
    public static final String COLUMN_TRIAL_END = "end_time";
    public static final String COLUMN_TRIAL_STRINGS = "strings_of_speed";
    public static final String COLUMN_TRIAL_USERROUTE_ID = "user_route_id";

    final String CREATE_TABLE_USER_ROUTE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_ROUTE
            + "( "
            + COLUMN_USER_ROUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ROUTE_START + " TEXT NOT NULL, "
            + COLUMN_USER_ROUTE_END + " TEXT NOT NULL, "
            + COLUMN_USER_ROUTE_ARRIVAL + " INTEGER, "
            + COLUMN_USER_ROUTE_REPEAT + " BOOLEAN NOT NULL, "
            + COLUMN_USER_ROUTE_MON + " BOOLEAN, "
            + COLUMN_USER_ROUTE_TUE + " BOOLEAN, "
            + COLUMN_USER_ROUTE_WED + " BOOLEAN, "
            + COLUMN_USER_ROUTE_THU + " BOOLEAN, "
            + COLUMN_USER_ROUTE_FRI + " BOOLEAN, "
            + COLUMN_USER_ROUTE_SAT + " BOOLEAN, "
            + COLUMN_USER_ROUTE_SUN + " BOOLEAN "
            + ");";

    final String CREATE_TABLE_TRIAL = "CREATE TABLE IF NOT EXISTS " + TABLE_TRIAL
            + "( "
            + COLUMN_TRIAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TRIAL_START + " INTEGER NOT NULL, "
            + COLUMN_TRIAL_END + " INTEGER NOT NULL, "
            + COLUMN_TRIAL_STRINGS + " TEXT NOT NULL, "
            + COLUMN_TRIAL_USERROUTE_ID + " INTEGER NOT NULL "
            + ");";

    public MyDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER_ROUTE);
        db.execSQL(CREATE_TABLE_TRIAL);

        // Testing purpose
        // hard code
        db.execSQL("INSERT INTO " + TABLE_USER_ROUTE + " VALUES ("
                + "1, "
                + "'Hang Hau', "
                + "'HKUST', "
                + "1460434796, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0 "
                + ");"
        );

        db.execSQL("INSERT INTO " + TABLE_TRIAL + " VALUES ("
                + "1, "
                + System.currentTimeMillis()/1000 + ", "
                + (System.currentTimeMillis()/1000 + 1800*1000) + ", " // 30minslater
                + "'WWWWWWWWWSSTTTTTTTTTTTTTTTTTTWWSWW', "
                + "1 "
                + ");"
        );
        db.execSQL("INSERT INTO " + TABLE_TRIAL + " VALUES ("
                + "2, "
                + System.currentTimeMillis()/1000 + ", "
                + (System.currentTimeMillis()/1000 + 1800*1000) + ", " // 30minslater
                + "'WWWWWWWWWSSTTTTTTTTTTwwTTTTTTTTWWSWW', "
                + "1 "
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ROUTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIAL);

        onCreate(db);
    }

}