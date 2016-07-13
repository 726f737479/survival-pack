package com.rostdev.survivalpack.ui.level;

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
public class LevelPresenter extends BasePresenter<LevelContract.View> implements
        LevelContract.Presenter,
        SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorGravity;

    @Inject
    public  LevelPresenter (@MvpViewContext Context context) {

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    @Override
    protected void onViewAttached(LevelContract.View View) {

        sensorManager.registerListener(this,
                sensorGravity,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onViewDetached(LevelContract.View View) {

        sensorManager.unregisterListener(this,
                sensorGravity);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        view.updateLevelData(event.values[0], event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
