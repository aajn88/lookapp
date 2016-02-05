package com.grability.lookapp.clients;

import com.grability.lookapp.model.rest.FeedResponse;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface ILookAppClient {

    /**
     * This method requests to the server the updated feed
     *
     * @return Updated feed. If an error occurred, then null is returned
     */
    FeedResponse requestFeed();

}
