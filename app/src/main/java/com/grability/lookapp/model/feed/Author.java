package com.grability.lookapp.model.feed;

import com.grability.lookapp.model.common.Member;

import java.io.Serializable;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Author implements Serializable {

    /** Author Name **/
    private Member name;

    /** Author Uri **/
    private Member uri;

    /**
     * @return the name
     */
    public Member getName() {
        return name;
    }

    /**
     * @return name the name to set
     */
    public void setName(Member name) {
        this.name = name;
    }

    /**
     * @return the uri
     */
    public Member getUri() {
        return uri;
    }

    /**
     * @return uri the uri to set
     */
    public void setUri(Member uri) {
        this.uri = uri;
    }
}
