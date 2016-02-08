package com.grability.lookapp.model.rest;

import com.grability.lookapp.model.feed.Feed;

/**
 * This is a wrapper class where the requested feed will be located
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class FeedResponse {

    /** Expected feed response **/
    private Feed feed;

    /**
     * @return the feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * @return feed the feed to set
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
