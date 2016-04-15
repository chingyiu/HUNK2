package com.hunkim2.timeestimation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewRoute extends AppCompatActivity {

    EditText et_name, et_from, et_to;

    Button btn_date;
    int set_year, set_month, set_day;
    static final int DATEDIALOG_ID = 0;

    Button btn_time;
    int set_hour, set_minute;
    static final int TIMEDIALOG_ID = 1;

    Button btn_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        // Initialize database Objective to do the insertion
        final MyDBHelper db = new MyDBHelper(this);

        et_name = (EditText) findViewById(R.id.editText_EditPathName);
        et_from = (EditText) findViewById(R.id.editText_EditPathFrom);
        et_to   = (EditText) findViewById(R.id.editText_EditPathTo);

        final Calendar cal = Calendar.getInstance();
        set_year = cal.get(Calendar.YEAR);
        set_month = cal.get(Calendar.MONTH);
        set_day = cal.get(Calendar.DAY_OF_MONTH);

        showDatePickerDialog();
        showTimePickerDialog();

        btn_create = (Button) findViewById(R.id.button_create);

        // When the "Create" button is clicked,
        // invoke the Insertion to DB.
        //      After that, back to the MainActivity
        //      and update the list of route.
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // execute query
                long result = db.insertRoute(et_name.getText().toString(),
                        et_from.getText().toString(),
                        et_to.getText().toString(),
                        set_year, set_month, set_day,
                        set_hour, set_minute,
                        30); // TODO: "30 minutes" should be requested from GG API

                // close the db after finish
                db.close();

                // close this Activity
                NewRoute.this.finish();
                // Go back to Main
                Intent intent_request = new Intent(NewRoute.this, MainActivity.class);
                NewRoute.this.startActivity(intent_request);
            }
        });
    }

    public void showDatePickerDialog() {
        btn_date = (Button)findViewById(R.id.button_EditPathArrivalDate);
        btn_date.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DATEDIALOG_ID);
                    }
                }
        );
    }

    public void showTimePickerDialog() {
        btn_time = (Button)findViewById(R.id.button_EditPathArrivalTime);
        btn_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        showDialog(TIMEDIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == TIMEDIALOG_ID)
            return new TimePickerDialog(NewRoute.this, myTimePickerListener, set_hour, set_minute, false);
        else if (id == DATEDIALOG_ID)
            return new DatePickerDialog(NewRoute.this, myDatePickerListener, set_year, set_month, set_day);

        return null;
    }

    protected DatePickerDialog.OnDateSetListener myDatePickerListener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            set_year = year;
            set_month = monthOfYear + 1; // java save 0-11, not 1-12
            set_day = dayOfMonth;

            btn_date.setText(set_year +"/"+set_month+"/"+set_day);
        }
    };

    protected TimePickerDialog.OnTimeSetListener myTimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    set_hour = hourOfDay;
                    set_minute = minute;

                    // Optimize the Display text in eg. "01:01" format
                    if(set_minute / 10 == 0) {
                        if(set_hour / 10 == 0)
                            btn_time.setText("0" + set_hour + " : 0" + set_minute);
                        else
                            btn_time.setText(set_hour + " : 0" + set_minute);
                    }else{
                        if(set_hour / 10 == 0)
                            btn_time.setText("0" + set_hour + " : " + set_minute);
                        else
                            btn_time.setText(set_hour + " : " + set_minute);
                    }
                }
            };
}
