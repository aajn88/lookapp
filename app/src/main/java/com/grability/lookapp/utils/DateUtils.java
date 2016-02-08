package com.grability.lookapp.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class DateUtils {

    /** Default Format **/
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    /** Tag for Logs **/
    private static final String TAG_LOG = DateUtils.class.getName();

    /** Private constructor to avoid instances **/
    private DateUtils() {}

    /**
     * This method parsers a given string and a given format to a Date
     *
     * @param strDate
     *         Date in String
     * @param format
     *         Date format
     *
     * @return The Date. If an error occurs, then null is returned
     */
    public static Date parseDate(String strDate, String format) {
        SimpleDateFormat fomatter = new SimpleDateFormat(format);
        try {
            return fomatter.parse(strDate);
        } catch (ParseException e) {
            Log.e(TAG_LOG, "An error occurred while parsing the Date", e);
        }
        return null;
    }

}
