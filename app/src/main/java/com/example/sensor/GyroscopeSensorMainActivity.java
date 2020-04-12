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

public class GyroscopeSensorMainActivity extends AppCompatActivity implements SensorEventListener {

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

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);

        for (int i = 0; i < deviceSensors.size(); i++) {
            tv.append("\n" + deviceSensors.get(i).getName() + "\n" + deviceSensors.get(i).getVendor());
        }

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]>0.5){ //Anti-Clockwise along x-axis
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        else if(event.values[0]<-0.5){ //Clockwise along x-axis
            getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
        }
        if(event.values[1]>0.5){ //Anti-Clockwise along y-axis
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
        else if(event.values[1]<-0.5){ //Clockwise along y-axis
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
        }
        if(event.values[2]>0.5){ //Anti-Clockwise along z-axis
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }
        else if(event.values[2]<-0.5){ //Clockwise along z-axis
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
