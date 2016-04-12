package com.hunkim2.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "boxoffice";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_CAST = "moviecast";
    public static final String COLUMN_STORY = "moviestory";
    public static final String COLUMN_GROSS = "gross";

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_DIRECTOR + " text not null, "
            + COLUMN_CAST + " text not null, "
            + COLUMN_STORY + " text not null, "
            + COLUMN_GROSS + " text not null "
            + ");";



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(MySQLiteHelper.class.getName(),
                "CREATE TABLE SQL: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
                + "1, "
                + "'Taken 2', "
                + "'Olivier Megaton', "
                + "'Liam Neeson, Famke Janssen and Maggie Grace', "
                + "'In Istanbul, retired CIA operative Bryan Mills and his wife are taken hostage by the father of a kidnapper Mills killed while rescuing his daughter.', "
                + "'$50M'"
                + ");"
        );

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("

                + "2, "
                + "'Looper', "
                + "'Rian Johnson', "
                + "'Joseph Gordon-Levitt, Bruce Willis and Emily Blunt', "
                + "'In 2072, when the mob wants to get rid of someone, the target is sent 30 years into the past, where a hired gun awaits. Someone like Joe, who one day learns the mob wants to close the loop by transporting back Joes future self. In 2072, when the mob wants to get rid of someone, the target is sent 30 years into the past, where a hired gun awaits. Someone like Joe, who one day learns the mob wants to close the loop by transporting back Joes future self. In 2072, when the mob wants to get rid of someone, the target is sent 30 years into the past, where a hired gun awaits. Someone like Joe, who one day learns the mob wants to close the loop by transporting back Joes future self. In 2072, when the mob wants to get rid of someone, the target is sent 30 years into the past, where a hired gun awaits. Someone like Joe, who one day learns the mob wants to close the loop by transporting back Joes future self.', "
                + "'$40.3M'"
                + ");"
        );


        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
                + "3, "
                + "'Frankenweenie', "
                + "'Tim Burton', "
                + "'Winona Ryder, Catherine OHara and Martin Short', "
                + "'Young Victor conducts a science experiment to bring his beloved dog Sparky back to life, only to face unintended, sometimes monstrous, consequences.', "
                + "'$11.5M'"
                + ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
