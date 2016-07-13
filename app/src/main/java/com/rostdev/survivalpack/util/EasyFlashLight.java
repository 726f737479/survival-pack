package com.rostdev.survivalpack.util;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by Rosty on 7/5/2016.
 */
public class EasyFlashLight {

    public static String TAG = EasyFlashLight.class.getSimpleName();

    private Camera camera;
    private Camera.Parameters parameters;

    private CameraManager cameraManager;
    private String cameraId;

    public EasyFlashLight(Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            camera = Camera.open();
            parameters = camera.getParameters();

        } else cameraManager = (CameraManager)
                context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void toggle(final boolean on) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

                    parameters.setFlashMode(on ? Parameters.FLASH_MODE_TORCH : Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);

                    if (on)camera.startPreview();
                    else camera.stopPreview();

                } else {

                    try {
                        if (cameraId == null) cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId, on);

                    } catch (CameraAccessException e) { Log.e(TAG, e.getMessage()); }
                }
            }
        }).start();
    }

    public void ternOff() {
        toggle(false);
    }

    public void ternOn(){
        toggle(true);
    }

    public void release(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            camera.stopPreview();
            camera.release();
        }
    }
}
