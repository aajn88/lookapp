package com.grability.lookapp.utils;

import com.grability.lookapp.constants.AppAttribute;
import com.grability.lookapp.model.common.Member;

import java.util.Date;

/**
 * The main goal of this class is to offer an easy way to read the attributes from
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class AttributesManager {

    /** Private constructor to avoid instances **/
    private AttributesManager() {}

    /**
     * This method returns the label field of {@link Member}
     *
     * @return The label. Returns null if it does not exist
     */
    public static String getLabel(Member member) {
        return validateNotNull(member) ? member.getLabel() : null;
    }

    /**
     * This method returns the String related to the given attribute
     *
     * @param member
     *         The parent of the attribute
     * @param attr
     *         The parameter to be extracted
     *
     * @return The requested attribute. Null if there is no the requested attribute
     */
    public static String getString(Member member, AppAttribute attr) {
        return validateNotNull(member) && validateNotNull(member.getAttrs()) ?
                member.getAttrs().get(attr.getAttribute()) : null;
    }

    /**
     * This method gets the int value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0 is returned
     */
    public static int getInt(Member member, AppAttribute attr) {
        return getInt(member, attr, 0);
    }

    /**
     * This method gets the int value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static int getInt(Member member, AppAttribute attr, int defaultValue) {
        String value = getString(member, attr);
        return validateNotNull(value) ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * This method gets the float value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0 is returned
     */
    public static float getFloat(Member member, AppAttribute attr) {
        return getFloat(member, attr, 0.0F);
    }

    /**
     * This method gets the float value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static float getFloat(Member member, AppAttribute attr, float defaultValue) {
        String value = getString(member, attr);
        return validateNotNull(value) ? Float.parseFloat(value) : defaultValue;
    }

    /**
     * This method gets the double value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0.0 is returned
     */
    public static double getDouble(Member member, AppAttribute attr) {
        return getDouble(member, attr, 0.0D);
    }

    /**
     * This method gets the double value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static double getDouble(Member member, AppAttribute attr, double defaultValue) {
        String value = getString(member, attr);
        return validateNotNull(value) ? Double.parseDouble(value) : defaultValue;
    }

    /**
     * This method gets the {@link Date} value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, null is returned
     */
    public static Date getDate(Member member, AppAttribute attr, String dateFormat) {
        return getDate(member, attr, dateFormat, null);
    }

    /**
     * This method gets the {@link Date} value of a given attribute
     *
     * @param member
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static Date getDate(Member member, AppAttribute attr, String dateFormat,
                               Date defaultValue) {
        String value = getString(member, attr);
        return validateNotNull(value) ? DateUtils.parseDate(value, dateFormat) : defaultValue;
    }

    /**
     * This method checks if is the given object is not null
     *
     * @param obj
     *         Object to be validated
     *
     * @return True if it is valid. Otherwise returns false
     */
    private static boolean validateNotNull(Object obj) {
        return obj != null;
    }

}
