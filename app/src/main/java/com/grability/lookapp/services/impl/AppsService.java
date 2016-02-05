package com.grability.lookapp.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.grability.lookapp.clients.ILookAppClient;
import com.grability.lookapp.model.Feed;
import com.grability.lookapp.model.rest.FeedResponse;
import com.grability.lookapp.services.api.IAppsService;

/**
 * This class implements {@link IAppsService} interface that offers Apps services
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class AppsService implements IAppsService {

    /** LookApp server Client **/
    @Inject
    private ILookAppClient lookAppClient;

    /**
     * This method requests feed. The resulting feed is stored in the DB and then is returned while
     * the online feed is not modified
     *
     * @return Resulting feed. If an error occurs, then null is returned
     */
    @Override
    public Feed getFeed() {
        FeedResponse response = lookAppClient.requestFeed();
        return response == null ? null : response.getFeed();
    }

}
