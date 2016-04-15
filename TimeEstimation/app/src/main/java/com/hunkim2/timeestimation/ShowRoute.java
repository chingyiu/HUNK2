package com.hunkim2.timeestimation;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Sean on 12/4/2016.
 */
public class ShowRoute extends ListActivity {

    private MyDBHelper myDBHelper;
    private SQLiteDatabase mydb;
    private String[] allColumns;
    private Cursor myCursor;

    private SimpleCursorAdapter dbAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);

        //Getting the bundle information.
        //From which, we will know which movie to display details information
        Bundle bundle = this.getIntent().getExtras();
        long rowId = bundle.getLong("rowId");

        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[] {
                MyDBHelper.COLUMN_USER_ROUTE_ID,
                MyDBHelper.COLUMN_USER_ROUTE_START,
                MyDBHelper.COLUMN_USER_ROUTE_END
        };

        //Pass "this" as the context
        myDBHelper = new MyDBHelper(this);

        // Get a SQLiteDatabase object so that we can run database query
        mydb = myDBHelper.getReadableDatabase();

        // Calling query method to retrieve records (table rows) cursor.
        myCursor = mydb.query(MyDBHelper.TABLE_USER_ROUTE,
                allColumns, MyDBHelper.COLUMN_USER_ROUTE_ID+"="+String.valueOf(rowId), null, null, null, null);

        // Get a reference to the UI component of activity_show_movie.xml
        TextView routeStart = (TextView) findViewById(R.id.showRouteStart);
        TextView routeEnd = (TextView) findViewById(R.id.showRouteEnd);


        myCursor.moveToFirst();
        if (!myCursor.isAfterLast()) {
            //Set the activity screen title
            setTitle(myCursor.getString(myCursor.getColumnIndex(MyDBHelper.COLUMN_USER_ROUTE_START)));
            routeStart.setText(myCursor.getString(myCursor.getColumnIndex(MyDBHelper.COLUMN_USER_ROUTE_START)));

            setTitle(myCursor.getString(myCursor.getColumnIndex(MyDBHelper.COLUMN_USER_ROUTE_END)));
            routeEnd.setText(myCursor.getString(myCursor.getColumnIndex(MyDBHelper.COLUMN_USER_ROUTE_END)));
        }


        // free memory
        allColumns = null;

        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[] {
                MyDBHelper.COLUMN_TRIAL_ID,
                MyDBHelper.COLUMN_TRIAL_START,
                MyDBHelper.COLUMN_TRIAL_END,
                MyDBHelper.COLUMN_TRIAL_STRINGS
        };

        // Calling query method to retrieve records (table rows) cursor.
        myCursor = mydb.query(MyDBHelper.TABLE_TRIAL,
                allColumns, MyDBHelper.COLUMN_TRIAL_USER_ROUTE_ID+"=" +rowId, null, null, null, null);

        // Specify the columns/fields to retrieve from database table
        String[] fromColumns = {
                MyDBHelper.COLUMN_TRIAL_ID,
                MyDBHelper.COLUMN_TRIAL_START,
                MyDBHelper.COLUMN_TRIAL_END,
                MyDBHelper.COLUMN_TRIAL_STRINGS
        };

        // Specify the view id in the row.xml to load the data.
        // Each table column specified the above fromColumns will corresponds to
        // a view component below
        int[] toViews = {
                R.id.showRouteID,
                R.id.showRouteStartTime,
                R.id.showRouteEndTime,
                R.id.showRouteStrings
        };

        // Creating SimpleCursorAdapter that
        dbAdapter = new SimpleCursorAdapter(this,
                R.layout.show_route_trial,
                myCursor,
                fromColumns,
                toViews,
                0);

        // Connect the dbAdapter to the ListView
        setListAdapter(dbAdapter);

    }
}
