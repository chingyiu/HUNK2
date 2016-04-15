package com.hunkim2.timeestimation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by Sean on 13/4/2016.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private final static int DB_VERSION = 1;

    private final static String DB_NAME = "User_Route.db";  //<-- db name

    public final static String TABLE_USER_ROUTE = "User_Route"; //<-- table name
    //  "User_Route" table structure:
    //  # attributes: 18
    public static final String COLUMN_USER_ROUTE_ID = "_id";
    public static final String COLUMN_USER_ROUTE_NAME = "route_name";   // new
    public static final String COLUMN_USER_ROUTE_START = "start_point";
    public static final String COLUMN_USER_ROUTE_END = "end_point";
    // public static final String COLUMN_USER_ROUTE_ARRIVAL = "arrival_time";   // divided into below
    public static final String COLUMN_USER_ROUTE_YEAR = "arrival_year";     // new
    public static final String COLUMN_USER_ROUTE_MONTH = "arrival_month";   // new
    public static final String COLUMN_USER_ROUTE_DAY = "arrival_day";       // new
    public static final String COLUMN_USER_ROUTE_HOUR = "arrival_hour";     // new
    public static final String COLUMN_USER_ROUTE_MINUTE = "arrival_minute"; // new

    public static final String COLUMN_USER_ROUTE_REPEAT = "repeat";
    public static final String COLUMN_USER_ROUTE_MON = "mon";
    public static final String COLUMN_USER_ROUTE_TUE = "tue";
    public static final String COLUMN_USER_ROUTE_WED = "wed";
    public static final String COLUMN_USER_ROUTE_THU = "thu";
    public static final String COLUMN_USER_ROUTE_FRI = "fri";
    public static final String COLUMN_USER_ROUTE_SAT = "sat";
    public static final String COLUMN_USER_ROUTE_SUN = "sun";
    public static final String COLUMN_USER_ROUTE_ESTIMATION = "estimated_time";


    public final static String TABLE_TRIAL = "Trial"; //<-- table name
    //  "User_Route" table structure:
    //  # attributes: 5
    public static final String COLUMN_TRIAL_ID = "_id";
    public static final String COLUMN_TRIAL_START = "start_time";
    public static final String COLUMN_TRIAL_END = "end_time";
    public static final String COLUMN_TRIAL_STRINGS = "strings_of_speed";
    public static final String COLUMN_TRIAL_USER_ROUTE_ID = "user_route_id";

    final String CREATE_TABLE_USER_ROUTE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_ROUTE
            + "( "
            + COLUMN_USER_ROUTE_ID + " integer primary key autoincrement, "
            + COLUMN_USER_ROUTE_NAME + " text, "
            + COLUMN_USER_ROUTE_START + " text not null, "
            + COLUMN_USER_ROUTE_END + " text not null, "
            + COLUMN_USER_ROUTE_YEAR + " integer, "
            + COLUMN_USER_ROUTE_MONTH + " integer, "
            + COLUMN_USER_ROUTE_DAY + " integer, "
            + COLUMN_USER_ROUTE_HOUR + " integer, "
            + COLUMN_USER_ROUTE_MINUTE + " integer, "
            + COLUMN_USER_ROUTE_REPEAT + " integer default 0, "
            + COLUMN_USER_ROUTE_MON + " integer default 0, "
            + COLUMN_USER_ROUTE_TUE + " integer default 0, "
            + COLUMN_USER_ROUTE_WED + " integer default 0, "
            + COLUMN_USER_ROUTE_THU + " integer default 0, "
            + COLUMN_USER_ROUTE_FRI + " integer default 0, "
            + COLUMN_USER_ROUTE_SAT + " integer default 0, "
            + COLUMN_USER_ROUTE_SUN + " integer default 0, "
            + COLUMN_USER_ROUTE_ESTIMATION + " integer  default 0"
            + ");";

    final String CREATE_TABLE_TRIAL = "CREATE TABLE IF NOT EXISTS " + TABLE_TRIAL
            + "( "
            + COLUMN_TRIAL_ID + " integer primary key autoincrement, "
            + COLUMN_TRIAL_START + " integer , "
            + COLUMN_TRIAL_END + " integer, "
            + COLUMN_TRIAL_STRINGS + " text not null, "
            + COLUMN_TRIAL_USER_ROUTE_ID + " integer not null "
            + ");";

    public MyDBHelper(Context context) {
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
                + "1, "             // ID
                + "'Route 1', "         // route_name
                + "'Hang Hau', "    // Start
                + "'HKUST', "       // end
                + "2016, "              // year
                + "04, "                // month
                + "13, "                // day
                + "15, "                // hour
                + "59, "                // minute
                + "0, "             // repeat
                + "0, "             // mon
                + "0, "             // tue
                + "0, "             // wed
                + "0, "             // thu
                + "0, "             // fri
                + "0, "             // sat
                + "0, "             // sun
                + "18 "             // est. time (min at the moment)
                + ");"
        );

        db.execSQL("INSERT INTO " + TABLE_USER_ROUTE + " VALUES ("
                + "2, "
                + "'Route 2', "         // route_name
                + "'Po Lam', "
                + "'HKUST', "
                + "2016, "              // year
                + "04, "                // month
                + "13, "                // day
                + "15, "                // hour
                + "59, "                // minute
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "0, "
                + "20 "             // est. time (min at the moment)
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
                + (System.currentTimeMillis()/1000 + 1800*1000) + ", " // 30 minslater
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

    public void close(){
        super.close();
    }

    public long insertRoute(String RName, String RStart, String REnd,
                            int RYear, int RMonth, int RDay,
                            int RHour, int RMinute,
                            int REstimation)
    {
        try{
            ContentValues cv = new ContentValues();
            cv.put("route_name", RName);
            cv.put("start_point", RStart);
            cv.put("end_point", REnd);
            cv.put("arrival_year", RYear);
            cv.put("arrival_month", RMonth);
            cv.put("arrival_day", RDay);
            cv.put("arrival_hour", RHour);
            cv.put("arrival_minute", RMinute);
            cv.put("estimated_time", REstimation);

            return getWritableDatabase().insert(TABLE_USER_ROUTE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
