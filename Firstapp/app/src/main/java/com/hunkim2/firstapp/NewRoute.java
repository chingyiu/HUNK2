package com.hunkim2.firstapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.sql.Time;
import java.util.Calendar;

public class NewRoute extends AppCompatActivity   {

    EditText et_time;
    static final int DIALOG_ID = 0;
    int set_hour;
    int set_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        showTimePickerDialog();
    }

    public void showTimePickerDialog() {
        et_time = (EditText)findViewById(R.id.editText_EditPathArrivalTime);
        et_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new TimePickerDialog(NewRoute.this, myTimePickerListener, set_hour, set_minute, false);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener myTimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    set_hour = hourOfDay;
                    set_minute = minute;
                    Toast.makeText(NewRoute.this, set_hour+ " : " +set_minute, Toast.LENGTH_LONG).show();
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_route, menu);
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

    // todo 1:
    public void createRoute(View view) {

        // Database insertion function
        // implement in here
    }

}
