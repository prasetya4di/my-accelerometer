package com.pras.myaccelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView txtAxisX, txtAxisY, txtAxisZ;
    private ImageView imgIllustration;
    private ListView lvNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAxisX = findViewById(R.id.txtAxisX);
        txtAxisY = findViewById(R.id.txtAxisY);
        txtAxisZ = findViewById(R.id.txtAxisZ);
        imgIllustration = findViewById(R.id.imgIllustration);
        lvNavigation = findViewById(R.id.listNavigation);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // assign directions
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            txtAxisX.setText(getString(R.string.txt_axis_x, x));
            txtAxisY.setText(getString(R.string.txt_axis_y, y));
            txtAxisZ.setText(getString(R.string.txt_axis_z, z));
            Pointing currentPoint = Pointing.parse(x, y);
            if (currentPoint != null) {
                imgIllustration.setImageResource(getImageId(currentPoint));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private int getImageId(Pointing pointing) {
        switch (pointing) {
            case DOWN:
                return R.drawable.ic_arrow_down;
            case LEFT:
                return R.drawable.ic_arrow_left;
            case RIGHT:
                return R.drawable.ic_arrow_right;
            default:
                return R.drawable.ic_arrow_up;
        }
    }
}
