package com.grability.lookapp.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.grability.lookapp.managers.api.IFeedsManager;
import com.grability.lookapp.model.feed.Feed;
import com.grability.lookapp.persistence.DatabaseHelper;

import java.sql.SQLException;

/**
 * The Feed Manager where all custom queries are implemented
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class FeedsManager extends CrudManager<Feed, Integer> implements IFeedsManager {

    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    @Inject
    public FeedsManager(DatabaseHelper helper) throws SQLException {
        super(helper, Feed.class);
    }
}
