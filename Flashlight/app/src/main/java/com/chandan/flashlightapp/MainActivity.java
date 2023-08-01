package com.chandan.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //toggle button
    ToggleButton toggleButton;
    //camera manager
    CameraManager cameraManager;
    //camera id
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flashLight(isChecked);
            }
        });
    }

    private void flashLight(boolean isChecked) {
        try {
            cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, isChecked);
            }

        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        try {
            //pause app flight light is OFF
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        super.finish();
        try {
            //close app flight light is OFF
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}