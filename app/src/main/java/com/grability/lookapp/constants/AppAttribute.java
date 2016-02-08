package com.grability.lookapp.constants;

import com.grability.lookapp.model.app.App;

/**
 * This enum shows all different attributes contained by {@link App}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public enum AppAttribute {

    /** The Label Attribute **/
    LABEL("label"),

    /** The Term Attribute **/
    TERM("term"),

    /** The Rel Attribute **/
    REL("rel"),

    /** The Type Attribute **/
    TYPE("type"),

    /** The HREF Attribute **/
    HREF("href"),

    /** The Id Attribute **/
    ID("im:id"),

    /** The BundleId Attribute **/
    BUNDLE_ID("bundleId"),

    /** The Scheme Attribute **/
    SCHEME("scheme"),

    /** The Amount Attribute **/
    AMOUNT("amount"),

    /** The Currency Attribute **/
    CURRENCY("currency");

    /** The attribute real name **/
    private final String attribute;

    /**
     * Constructor that defines the attribute real name
     *
     * @param attribute
     *         Attribute real name
     */
    AppAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }
}
