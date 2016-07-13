package com.rostdev.survivalpack.ui.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.ui.MvpViewContext;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/5/2016.
 */
public class CompassPresenter extends BasePresenter<CompassContract.View> implements
        CompassContract.Presenter,
        SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorOrientation;

//    private Sensor sensorAccelerometer;
//    private Sensor sensorMagneticField;
//    private Sensor sensorGravity;

//    private float[] valuesA;
//    private float[] valuesMG;
//
//    private float[] matrixR;
//    private float[] matrixI;
//    private float[] matrixValues;

    @Inject
    public CompassPresenter(@MvpViewContext Context context) {

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

//        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//
//        valuesA = new float[3];
//        valuesMG = new float[3];
//
//        matrixR = new float[9];
//        matrixI = new float[9];
//        matrixValues = new float[3];
    }

    @Override
    protected void onViewAttached(CompassContract.View View) {

        sensorManager.registerListener(this,
                sensorOrientation,
                SensorManager.SENSOR_DELAY_UI);

//        sensorManager.registerListener(this,
//                sensorAccelerometer,
//                SensorManager.SENSOR_DELAY_UI);
//
//        sensorManager.registerListener(this,
//                sensorMagneticField,
//                SensorManager.SENSOR_DELAY_UI);
//
//        sensorManager.registerListener(this,
//                sensorGravity,
//                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onViewDetached(CompassContract.View View) {

        sensorManager.unregisterListener(this,
                sensorOrientation);

//        sensorManager.unregisterListener(this,
//                sensorAccelerometer);
//
//        sensorManager.unregisterListener(this,
//                sensorMagneticField);
//
//        sensorManager.unregisterListener(this,
//                sensorGravity);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch(event.sensor.getType()){

            case Sensor.TYPE_ORIENTATION:
                view.updateCompassData(event.values[0]);
                break;

//            case Sensor.TYPE_ACCELEROMETER:
//                for(int i =0; i < 3; i++) valuesA[i] = event.values[i];
//                break;
//
//            case Sensor.TYPE_MAGNETIC_FIELD:
//                for(int i =0; i < 3; i++) valuesMG[i] = event.values[i];
//                break;
//
//            case Sensor.TYPE_GRAVITY:
//                for(int i =0; i < 3; i++) valuesA[i] = event.values[i];
//                break;
        }

//        if (SensorManager.getRotationMatrix(matrixR, matrixI, valuesA, valuesMG)) {
//
//            SensorManager.getOrientation(matrixR, matrixValues);
//            view.updateCompassData((float) Math.toDegrees(matrixValues[0]));
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
