package com.grability.lookapp.model.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Member implements Serializable {

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("label='").append(label).append('\'');
        sb.append(", attrs=").append(attrs);
        sb.append('}');
        return sb.toString();
    }
}
