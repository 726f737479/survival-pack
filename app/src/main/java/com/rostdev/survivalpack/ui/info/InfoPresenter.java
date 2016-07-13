package com.rostdev.survivalpack.ui.info;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.model.SensorInfo;
import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.ui.MvpViewContext;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/5/2016.
 */
public class InfoPresenter extends BasePresenter<InfoContract.View> implements
        InfoContract.Presenter,
        SensorEventListener {

    private SensorManager sensorManager;

    private Sensor sensorPressure;
    private Sensor sensorMagneticField;

    private SensorInfo pressure;
    private SensorInfo altitude;
    private SensorInfo magnetic;

    @Inject
    public InfoPresenter(@MvpViewContext Context context) {

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }


    @Override
    protected void onViewAttached(InfoContract.View View) {

        sensorManager.registerListener(this,
                sensorPressure,
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorMagneticField,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onViewDetached(InfoContract.View View) {

        sensorManager.unregisterListener(this,
                sensorPressure);

        sensorManager.unregisterListener(this,
                sensorMagneticField);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {

            case Sensor.TYPE_PRESSURE:

                long press = Math.round(event.values[0]);
                long alt = Math.round(getAltitude(press));

                pressure = new SensorInfo(R.drawable.ic_pressure,
                        "Pressure", press + " hPa");

                altitude = new SensorInfo(R.drawable.ic_altimeter,
                        "Altitude", alt + " meters");
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:

                long magnet = Math.round(
                        Math.sqrt((Math.pow((double) event.values[0], 2.0d) +
                        Math.pow((double) event.values[1], 2.0d)) +
                        Math.pow((double) event.values[2], 2.0d)));

                magnetic = new SensorInfo(R.drawable.ic_magnet,
                        "Magnetic field", magnet + " µT");
                break;
        }

        if (pressure != null && altitude != null && magnetic != null) {

            view.updateSensorInfoData(new SensorInfo[]{
                   magnetic, pressure, altitude
            });
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private float getAltitude(float pressure){

        return SensorManager.getAltitude(
                SensorManager.PRESSURE_STANDARD_ATMOSPHERE, pressure);
    }
}
