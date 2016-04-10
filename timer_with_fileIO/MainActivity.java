package com.hunkim2.testtimer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer = new Timer(true);
    MyTimerTask sumX;

    String file = "strings_of_speed";
    String data = "Strings of speed: ";

    TextView tv;
    EditText filename,content;
    Button button;

    private Context context;
    File fileObj = new File(context.getFilesDir(), file);

    int x = 0;
    private TextView text_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumX = new MyTimerTask();

        timer.schedule(sumX, 5000, 5000);
        text_x = (TextView)findViewById(R.id.TextView01);

        tv=(TextView)findViewById(R.id.textView2);

        try {
            FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);

            fOut.write(data.getBytes());
            fOut.close();

            Toast.makeText(getBaseContext(),"file created",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    x += 1;
                    text_x.setText("x = " + x);

                    try{
                        FileInputStream fin = openFileInput(file);
                        int c;
                        String temp="";

                        while( (c = fin.read()) != -1){
                            temp = temp + Character.toString((char)c);
                        }
                        tv.setText(temp);
                        Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();


                        FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);
                        String tmp="";
                        tmp = temp.concat(Integer.toString(x));

                        fOut.write(tmp.getBytes());
                        fOut.close();

                        Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }



                }
            });
        }
    }


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
}
