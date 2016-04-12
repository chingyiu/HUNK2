package com.hunkim2.testdb;

        import android.app.Activity;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v4.app.NavUtils;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.TextView;


public class ShowMovie extends Activity {

    // SQLite helper: responsible for create, update and delete database
    private MySQLiteHelper sqlHelper;

    // database object that executer query and update to database / table
    private SQLiteDatabase mydb;

    // The name of table's column that we wish to retrieve.
    // To save memory consumption, we will only retrieve
    // those fields that we need in the current activity/view
    private String[] allColumns;

    // cursor that points to the movies records that retrieved from movie database table
    private Cursor myCursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);



        //Getting the bundle information.
        //From which, we will know which movie to display details information
        Bundle bundle = this.getIntent().getExtras();
        long rowId = bundle.getLong("rowId");

        // We specify only those table fields/columns that we need in this activity / view.
        allColumns = new String[] {
                MySQLiteHelper.COLUMN_ID,
                MySQLiteHelper.COLUMN_TITLE,
                MySQLiteHelper.COLUMN_DIRECTOR,
                MySQLiteHelper.COLUMN_GROSS,
                MySQLiteHelper.COLUMN_CAST,
                MySQLiteHelper.COLUMN_STORY
        };


        //Pass "this" as the context
        sqlHelper = new MySQLiteHelper(this);

        // Get a SQLiteDatabase object so that we can run database query
        mydb = sqlHelper.getReadableDatabase();

        // Calling query method to retrieve records (table rows) cursor.
        myCursor = mydb.query(MySQLiteHelper.TABLE_NAME,
                allColumns, "_ID="+String.valueOf(rowId), null, null, null, null);

        // Get a reference to the UI component of activity_show_movie.xml
        TextView movieTitle = (TextView) findViewById(R.id.showMovieTitle);

        myCursor.moveToFirst();
        if (!myCursor.isAfterLast()) {
            //Set the activity screen title
            setTitle(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLE)));

            movieTitle.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLE)));
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

