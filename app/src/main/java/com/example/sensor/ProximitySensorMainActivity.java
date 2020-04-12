package com.example.sensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProximitySensorMainActivity extends AppCompatActivity implements SensorEventListener {

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

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        for (int i = 0; i < deviceSensors.size(); i++) {
            tv.append("\n" + deviceSensors.get(i).getName() + "\n" + deviceSensors.get(i).getVendor());
        }

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] < mLight.getMaximumRange()) {
            //Detected Something nearby
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }


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
