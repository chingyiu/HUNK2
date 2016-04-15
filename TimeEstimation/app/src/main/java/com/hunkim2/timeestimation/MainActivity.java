package com.hunkim2.timeestimation;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

// Database management Reference:
// http://jim690701.blogspot.hk/2012/06/android-sqlite.html
// https://www.youtube.com/watch?v=d_PjDM4nFxI

public class MainActivity extends ListActivity {

    private MyDBHelper myDBHelper;
    private SQLiteDatabase myDB;
    private String[] allColumns;
    private Cursor myCursor;

    private SimpleCursorAdapter dbAdapter;

    Date d;
    SimpleDateFormat sdf;

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[]{
                MyDBHelper.COLUMN_USER_ROUTE_ID,
                MyDBHelper.COLUMN_USER_ROUTE_START,
                MyDBHelper.COLUMN_USER_ROUTE_END,
                MyDBHelper.COLUMN_USER_ROUTE_MONTH,
                MyDBHelper.COLUMN_USER_ROUTE_DAY,
                MyDBHelper.COLUMN_USER_ROUTE_HOUR,
                MyDBHelper.COLUMN_USER_ROUTE_MINUTE,
                MyDBHelper.COLUMN_USER_ROUTE_ESTIMATION
        };
        //Pass "this" as the context
        myDBHelper = new MyDBHelper(this);
        // Get a SQLiteDatabase object so that we can run database query
        myDB = myDBHelper.getReadableDatabase();
        // Calling query method to retrieve records (table rows) cursor.
        myCursor = myDB.query(myDBHelper.TABLE_USER_ROUTE,
                allColumns, null, null, null, null, null);

        // Specify the columns/fields to retrieve from database table
        String[] fromColumns = {
                MyDBHelper.COLUMN_USER_ROUTE_START,
                MyDBHelper.COLUMN_USER_ROUTE_END,
                MyDBHelper.COLUMN_USER_ROUTE_MONTH,
                MyDBHelper.COLUMN_USER_ROUTE_DAY,
                MyDBHelper.COLUMN_USER_ROUTE_HOUR,
                MyDBHelper.COLUMN_USER_ROUTE_MINUTE,
                MyDBHelper.COLUMN_USER_ROUTE_ESTIMATION
        };

        // Specify the view id in the row.xml to load the data.
        // Each table column specified the above fromColumns will corresponds to
        // a view component below
        int[] toViews = {
                R.id.routeStart,
                R.id.routeEnd,
                R.id.routeArrival_month,
                R.id.routeArrival_day,
                R.id.routeArrival_hour,
                R.id.routeArrival_minute,
                R.id.routeEst
        };

        // Creating SimpleCursorAdapter that
        dbAdapter = new SimpleCursorAdapter(this,
                R.layout.main_row,
                myCursor,
                fromColumns,
                toViews,
                0);

        // Connect the dbAdapter to the ListView
        setListAdapter(dbAdapter);

        //
        // Show the current time
        d = new Date();
        sdf = new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        tv = (TextView) findViewById(R.id.tv_curTime);
        tv.setText(currentDateTimeString);


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    // Implemented function for the Button (in activity_main.xml)
    // same function NAME should be used with the "onclick" of the Button

    // ref: http://developer.android.com/training/basics/firstapp/building-ui.html
    public void toCreateRoute(View view) {
        Intent intent = new Intent(this, NewRoute.class);

        startActivity(intent);
    }
}