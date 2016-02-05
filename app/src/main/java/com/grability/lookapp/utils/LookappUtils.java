package com.grability.lookapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

/**
 * This Utils class is for all generic methods that are not necessarily categorized
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class LookappUtils {

    /** Private constructor to avoid instances **/
    private LookappUtils() {}

    /**
     * Checks if the current device is a tablet or not
     *
     * @param context
     *         Application context
     *
     * @return True if the device is a tablet. False otherwise
     */
    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void adjustScreenOrientation(Activity activity) {
        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        if (isTablet(activity)) {
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }

        activity.setRequestedOrientation(orientation);
    }
}
