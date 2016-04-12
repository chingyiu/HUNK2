package com.hunkim2.testdatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity{

    private MyDBHelper myDBHelper;
    private SQLiteDatabase myDB;
    private String[] allColumns;
    private Cursor myCursor;

    private SimpleCursorAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[] {
                MyDBHelper.COLUMN_USER_ROUTE_ID,
                MyDBHelper.COLUMN_USER_ROUTE_START,
                MyDBHelper.COLUMN_USER_ROUTE_END,
                MyDBHelper.COLUMN_USER_ROUTE_ARRIVAL,
                MyDBHelper.COLUMN_USER_ROUTE_REPEAT
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
                MyDBHelper.COLUMN_USER_ROUTE_ARRIVAL,
                MyDBHelper.COLUMN_USER_ROUTE_REPEAT
        };

        // Specify the view id in the row.xml to load the data.
        // Each table column specified the above fromColumns will corresponds to
        // a view component below
        int[] toViews = {
                R.id.routeStart,
                R.id.routeEnd,
                R.id.routeArrival,
                R.id.routeRepeat
        };

        // Creating SimpleCursorAdapter that
        dbAdapter = new SimpleCursorAdapter(this,
                R.layout.row,
                myCursor,
                fromColumns,
                toViews,
                0);

        // Connect the dbAdapter to the ListView
        setListAdapter(dbAdapter);
    }

    public void onListItemClick(ListView parent, View v,
                                int position,	long id) {

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ShowRoute.class);

        Bundle bundle = new Bundle();
        bundle.putLong("rowId", id);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
