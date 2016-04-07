package com.hunkim2.framework;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView text_x;
    private TextView text_y;
    private TextView text_z;
    private TextView text_cur_a;
    private TextView text_max_a;

    private float gx=0, gy=0, gz=0;
    private int indexOfQueue = 0;
    private float gravityQueue[] = new float[100];

    private SensorManager aSensorManager;
    private Sensor aSensor;
    private float gravity[] = new float[3];
    private double cur_accel;
    private double max_accel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        // create a reference
        Firebase myFirebaseRef = new Firebase("https://torrid-inferno-4164.firebaseio.com/");

        /*
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = (String) dataSnapshot.getValue();

            }
        });
        */

        text_cur_a = (TextView)findViewById(R.id.TextView_cur_a);
        text_max_a = (TextView)findViewById(R.id.TextView_max_a);

        text_x = (TextView)findViewById(R.id.TextView01);
        text_y = (TextView)findViewById(R.id.TextView02);
        text_z = (TextView)findViewById(R.id.TextView03);

        aSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        aSensorManager.registerListener( this, aSensor, aSensorManager.SENSOR_DELAY_NORMAL);

        /*
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.route_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.route_array1, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        */
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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub

        /*gravity[0] = event.values[0];
        gravity[1] = event.values[1];
        gravity[2] = event.values[2];
        */
        gravity[0] = (float)Math.round(event.values[0]*100)/100;
        gravity[1] = (float)Math.round(event.values[1]*100)/100;
        gravity[2] = (float)Math.round(event.values[2]*100)/100;

        text_x.setText("X = "+gravity[0]);
        text_y.setText("Y = "+gravity[1]);
        text_z.setText("Z = "+gravity[2]);

        cur_accel = Math.pow(gravity[0], 2)
                + Math.pow(gravity[1], 2)
                + Math.pow(gravity[2], 2);

        cur_accel = Math.sqrt(cur_accel);

        if (cur_accel > max_accel)
            max_accel = cur_accel;

        text_cur_a.setText("Current acceleration = "+cur_accel);
        text_max_a.setText("Maximum acceleration = "+max_accel);
    }

    /*
    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        // 取消註冊SensorEventListener
        aSensorManager.unregisterListener(this);
        Toast.makeText(this, "Unregister accelerometerListener", Toast.LENGTH_LONG).show();
        super.onPause();
    }*/
}
