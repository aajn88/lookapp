package com.grability.lookapp.clients;

import android.util.Log;

import com.google.inject.Singleton;
import com.grability.lookapp.model.rest.FeedResponse;
import com.grability.lookapp.utils.RestUtils;

/**
 * This client implementation requests basic operations with the server
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class LookAppClient implements ILookAppClient {

    /** Tag Logs **/
    private static final String TAG_LOG = LookAppClient.class.getName();

    /**
     * This method requests to the server the updated feed
     *
     * @return Updated feed. If an error occurred, then null is returned
     */
    @Override
    public FeedResponse requestFeed() {
        FeedResponse response = null;

        try {
            response = RestUtils
                    .get("https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json",
                            FeedResponse.class);
        } catch (Exception e) {
            Log.e(TAG_LOG, "An error has occurred while requesting the feed", e);
        }

        return response;
    }

}
