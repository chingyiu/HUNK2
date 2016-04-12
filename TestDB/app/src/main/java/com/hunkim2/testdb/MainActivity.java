package com.hunkim2.testdb;


        import android.app.ListActivity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.View;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.Toast;

public class MainActivity extends ListActivity{


    // SQLite helper: responsible for create, update and delete database
    private MySQLiteHelper sqlHelper;

    // database object that executes query and update to database / table
    private SQLiteDatabase mydb;


    // The name of table's column that we wish to retrieve.
    // To save memory consumption, we will only retrieve
    // those fields that we need in the current activity/view
    private String[] allColumns;

    // cursor that points to the movies records that retrieved from movie database table
    private Cursor myCursor;

    // CursorAdapter that works as the middle-man
    // between cursor (data-source) and list views
    private SimpleCursorAdapter dbAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        load activity_main.xml
        the xml file loaded here are expected to have a ListView Object,
        */
        setContentView(R.layout.activity_main);



        // Declaring an ArrayAdapter that will be serving as data source for our list view
        // In the last week's lab, we declared ArrayAdapter as data-source.
        // They are similar in parameters that are required.
        // Only cursor support multiple data fields while ArrayAdapter is one-dimensional (only one field).
        /*
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
				this,
				R.layout.row,
				R.id.listItemText,
				brands);
         */



        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[] {
                MySQLiteHelper.COLUMN_ID,
                MySQLiteHelper.COLUMN_TITLE,
                MySQLiteHelper.COLUMN_DIRECTOR,
                MySQLiteHelper.COLUMN_GROSS
        };


        //Pass "this" as the context
        sqlHelper = new MySQLiteHelper(this);

        // Get a SQLiteDatabase object so that we can run database query
        mydb = sqlHelper.getReadableDatabase();

        // Calling query method to retrieve records (table rows) cursor.
        myCursor = mydb.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);


        // Specify the columns/fields to retrieve from database table
        String[] fromColumns = {
                MySQLiteHelper.COLUMN_TITLE,
                MySQLiteHelper.COLUMN_DIRECTOR,
                MySQLiteHelper.COLUMN_GROSS
        };

        // Specify the view id in the row.xml to load the data.
        // Each table column specified the above fromColumns will corresponds to
        // a view component below
        int[] toViews = {
                R.id.movieTitle,
                R.id.movieDirector,
                R.id.movieGross
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
        intent.setClass(MainActivity.this, ShowMovie.class);

        Bundle bundle = new Bundle();
        bundle.putLong("rowId", id);
        intent.putExtras(bundle);

        startActivity(intent);
    }



}