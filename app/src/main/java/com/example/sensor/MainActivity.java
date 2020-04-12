package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SensorManager mSensorManager;
    TextView tv;
    Button light_btn, accelerometer_btn, proximity_btn,gyroscope_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        light_btn = findViewById(R.id.light_btn);
        accelerometer_btn = findViewById(R.id.accelerometer_btn);
        proximity_btn = findViewById(R.id.proximity_btn);
        gyroscope_btn = findViewById(R.id.gyroscope_btn);
//lets have some conflicts
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
// added code by vinay
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(int i=0; i<deviceSensors.size(); i++){
            tv.append("\n"+deviceSensors.get(i).getName()+"\n"+deviceSensors.get(i).getVendor());
        }


        light_btn.setOnClickListener(this);
        accelerometer_btn.setOnClickListener(this);
        proximity_btn.setOnClickListener(this);
        gyroscope_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.light_btn:
                intent = new Intent(this, LightSensorMainActivity.class);
                startActivity(intent);
                break;
            case R.id.accelerometer_btn:
                intent = new Intent(this, AccelerometerSensorMainActivity.class);
                startActivity(intent);
                break;
            case R.id.proximity_btn:
                intent = new Intent(this, ProximitySensorMainActivity.class);
                startActivity(intent);
                break;
            case R.id.gyroscope_btn:
//                intent = new Intent(this, ProximitySensorMainActivity.class);
//                startActivity(intent);
                intent = new Intent(this, MainActivity1.class);
                startActivity(intent);
                break;
        }
    }
}
