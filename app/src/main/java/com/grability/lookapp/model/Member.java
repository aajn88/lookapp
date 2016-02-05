package com.grability.lookapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Member {

    /** Member Label **/
    private String label;

    /** Member Attributes **/
    @SerializedName("attributes")
    private Map<String, String> attrs;

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the attrs
     */
    public Map<String, String> getAttrs() {
        return attrs;
    }

    /**
     * @return attrs the attrs to set
     */
    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }
}
