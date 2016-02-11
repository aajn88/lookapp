package com.grability.lookapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;

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
    public static boolean isTablet(Context context) {
        return (getScreenSize(context)) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     *
     * @param context
     *         Current context
     *
     * @return This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     */
    public static int getScreenSize(Context context) {
        return context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    /**
     * This method adjust the screen orientation based on: Smartphones (from small size to normal
     * size) is set to Landscape orientation and Tablets (large size) is set to Portrait
     * orientation
     *
     * @param activity
     *         The activity to be adjusted
     */
    public static void adjustScreenOrientation(Activity activity) {
        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        if (isTablet(activity)) {
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }

        activity.setRequestedOrientation(orientation);
    }

    /**
     * This method checks if the current OS is grater or equals to Lollipop
     *
     * @return True if the current OS is grater or equal to Lollipop. False otherwise
     */
    public static boolean isGraterLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
