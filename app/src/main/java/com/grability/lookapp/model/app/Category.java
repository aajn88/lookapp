package com.grability.lookapp.model.app;

/**
 * This class represents an App Category such as Games, Social Networks, etc.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Category {

    /** Category Id **/
    private int id;

    /** Category Label **/
    private String label;

    /** Default constructor **/
    public Category() {}

    /**
     * Especific constructor
     *
     * @param id
     *         Category Id
     * @param label
     *         Category label
     */
    public Category(int id, String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        return id == category.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
