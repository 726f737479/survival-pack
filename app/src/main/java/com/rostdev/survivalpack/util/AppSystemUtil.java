package com.rostdev.survivalpack.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.rostdev.survivalpack.Constants;
import com.rostdev.survivalpack.R;

/**
 * Created by Rosty on 7/5/2016.
 */
public class AppSystemUtil {

    public static boolean isFlashSupported(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public static Intent getEmailAppIntent(Context context, String subject, String message) {

        Intent email = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", Constants.DEVELOPER_EMAIL, null));

        email.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.DEVELOPER_EMAIL});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        if (email.resolveActivity(context.getPackageManager()) != null) {

            return Intent.createChooser(email, context.getString(R.string.title_chooser));

        } else return null;
    }
}
