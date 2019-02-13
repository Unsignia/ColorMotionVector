package com.unsignedco.unsignia.colormotionvector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        mSensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList  =
                mSensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorText = new StringBuilder();

        for (Sensor currentSensor : sensorList ) {
            sensorText.append(currentSensor.getName()).append(
                    System.getProperty("line.separator"));

            TextView sensorTextView = (TextView) findViewById(R.id.sensorsTextView);
            sensorTextView.setText(sensorText);
        }
    }

}
