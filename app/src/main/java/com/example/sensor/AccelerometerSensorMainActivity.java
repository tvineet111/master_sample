package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AccelerometerSensorMainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    TextView tv, tv1;
    Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv = findViewById(R.id.tv);
        tv1 = findViewById(R.id.tv1);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        for (int i = 0; i < deviceSensors.size(); i++) {
            tv.append("\n" + deviceSensors.get(i).getName() + "\n" + deviceSensors.get(i).getVendor());
        }

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Do something if sensor data changes
        // The accelerometer sensor returns 3 values for each axis.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        // Do something with this sensor value.
        tv1.setText("Current Acceleration\n"+x+" : "+y+" : "+z);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
