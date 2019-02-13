package com.unsignedco.unsignia.colormotionvector;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.DecimalFormat;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private static DecimalFormat dec = new DecimalFormat(".###");

    SensorManager mSensorManager;
    Sensor RVSensor;

    TextView xDisplay;
    TextView yDisplay;
    TextView zDisplay;
    ImageView bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        xDisplay = findViewById(R.id.xDisplay);
        yDisplay = findViewById(R.id.yDisplay);
        zDisplay = findViewById(R.id.zDisplay);
        bg = findViewById(R.id.bg);

        //Set up the Rotation Vector Sensor
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Check if Rotation Vector Sensor is present
        if (mSensorManager != null) {
            RVSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            Toast.makeText(this, "Sensor Active", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Sensor NOT Active", Toast.LENGTH_LONG).show();
        }
    }

// create an array of size 6 that generates a hex
    // create a memory array that hold the last 5-10 color changes
    // & displays it when person stops if they like

//every change of .2 changes the color (2 bits of it)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.details) {
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, SensorsActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

            Sensor sensor = event.sensor;
            if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                // Acquire measurement values from event
                double x = event.values[0]; // X axis
                double y = event.values[1]; // y axis
                double z = event.values[2]; // z axis

                // To String
                String X = Double.toString(x);
                String Y = Double.toString(y);
                String Z = Double.toString(z);

                // Display values
                xDisplay.setText("X: " + dec.format(X));
                yDisplay.setText("Y: " + dec.format(Y));
                zDisplay.setText("Z: " + dec.format(Z));

                if (x >= -1 && x < -.5) {

                } else if (x >= -.5 && x < 0) {

                } else if (x >= 0 && x < .5) {

                } else if (x >= .5 && x <= 1) {

                }

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        // Register mSensorListener for linear acceleration events
        mSensorManager.registerListener(mSensorListener,
                RVSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        // Unregister mSensorListener
        mSensorManager.unregisterListener(mSensorListener);

        super.onPause();
    }
*/
}
