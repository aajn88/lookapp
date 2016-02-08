package com.grability.lookapp.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.grability.lookapp.managers.api.IAppsManager;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.persistence.DatabaseHelper;

import java.sql.SQLException;

/**
 * The implementation of custom queries of the {@link IAppsManager}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class AppsManager extends CrudManager<App, Integer> implements IAppsManager {

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
    public AppsManager(DatabaseHelper helper) throws SQLException {
        super(helper, App.class);
    }
}
