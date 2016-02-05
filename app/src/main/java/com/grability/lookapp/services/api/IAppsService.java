package com.grability.lookapp.services.api;

import com.grability.lookapp.model.Feed;

/**
 * Note: This is not an Android Service. This is an internal service for Business Logic purposes
 * <p/>
 * This Apps Service offers a set of services to manage the Apps
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IAppsService {

    /**
     * This method requests feed. The resulting feed is stored in the DB and then is returned while
     * the online feed is not modified
     *
     * @return Resulting feed. If an error occurs, then null is returned
     */
    Feed getFeed();

}
